package com.erickrodrigues.picpay.domain.services

import com.erickrodrigues.picpay.domain.dto.TransactionPayload
import com.erickrodrigues.picpay.domain.entities.PicPayUser
import com.erickrodrigues.picpay.domain.exceptions.ActionNotAllowedException
import com.erickrodrigues.picpay.domain.exceptions.InsufficientBalanceException
import com.erickrodrigues.picpay.domain.exceptions.UserNotFoundException
import com.erickrodrigues.picpay.domain.ports.AuthorizerService
import com.erickrodrigues.picpay.domain.ports.NotifierService
import com.erickrodrigues.picpay.domain.ports.PicPayUserRepository
import com.erickrodrigues.picpay.domain.types.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*

class MakeTransactionTest {
    private val repo by lazy { mock(PicPayUserRepository::class.java) }
    private val authorizer by lazy { mock(AuthorizerService::class.java) }
    private val notifier by lazy { mock(NotifierService::class.java) }
    private val makeTransaction by lazy { MakeTransaction(repo, authorizer, notifier) }

    private fun createCommonUser(id: String) =
        PicPayUser(
            id = id,
            name = Name.fromString("Erick"),
            document = UserDocument.fromString("123.123.123-12"),
            email = Email.fromString("erick@erick.com"),
            password = Password.fromString("123456"),
            type = UserType.fromString("common"),
        )

    private fun createStorekeeperUser(id: String) =
        PicPayUser(
            id = id,
            name = Name.fromString("Erick"),
            document = UserDocument.fromString("18.236.120/0001-58"),
            email = Email.fromString("erick@erick.com"),
            password = Password.fromString("123456"),
            type = UserType.fromString("storekeeper"),
        )

    @Test
    fun `should throw an exception when payer is not found`() {
        `when`(repo.findById("2")).thenReturn(null)
        assertThrows<UserNotFoundException> {
            makeTransaction.execute(TransactionPayload(100.0, "2", "3"))
        }
    }

    @Test
    fun `should throw an exception when payee is not found`() {
        `when`(repo.findById("2")).thenReturn(createCommonUser("2"))
        `when`(repo.findById("3")).thenReturn(null)
        assertThrows<UserNotFoundException> {
            makeTransaction.execute(TransactionPayload(100.0, "2", "3"))
        }
    }

    @Test
    fun `should throw an exception when payer is not authorized`() {
        `when`(repo.findById("2")).thenReturn(createCommonUser("2"))
        `when`(repo.findById("3")).thenReturn(createCommonUser("3"))
        `when`(authorizer.authorize(createCommonUser("2"))).thenReturn(false)
        assertThrows<ActionNotAllowedException> {
            makeTransaction.execute(TransactionPayload(100.0, "2", "3"))
        }
    }

    @Test
    fun `should throw an exception when payer has not enough balance`() {
        val payer = createCommonUser("2")
        payer.increaseBalance(25.0)

        `when`(repo.findById("2")).thenReturn(payer)
        `when`(repo.findById("3")).thenReturn(createCommonUser("3"))
        `when`(authorizer.authorize(payer)).thenReturn(true)

        assertThrows<InsufficientBalanceException> {
            makeTransaction.execute(TransactionPayload(100.0, "2", "3"))
        }
    }

    @Test
    fun `should throw an exception when payer is a storekeeper`() {
        val payer = createStorekeeperUser("2")
        payer.increaseBalance(110.0)

        `when`(repo.findById("2")).thenReturn(payer)
        `when`(repo.findById("3")).thenReturn(createCommonUser("3"))
        `when`(authorizer.authorize(payer)).thenReturn(true)

        assertThrows<ActionNotAllowedException> {
            makeTransaction.execute(TransactionPayload(100.0, "2", "3"))
        }
    }

    @Test
    fun `transaction should work`() {
        val payer = createCommonUser("2")
        val payee = createStorekeeperUser("3")
        payer.increaseBalance(110.0)

        `when`(repo.findById("2")).thenReturn(payer)
        `when`(repo.findById("3")).thenReturn(payee)
        `when`(authorizer.authorize(payer)).thenReturn(true)

        makeTransaction.execute(TransactionPayload(100.0, "2", "3"))

        verify(repo, times(1)).makeTransaction(payer, payee)
        verify(notifier, times(1)).notify(payee)
    }
}
