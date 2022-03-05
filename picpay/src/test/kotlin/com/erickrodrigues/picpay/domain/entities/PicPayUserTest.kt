package com.erickrodrigues.picpay.domain.entities

import com.erickrodrigues.picpay.domain.exceptions.ActionNotAllowedException
import com.erickrodrigues.picpay.domain.exceptions.InsufficientBalanceException
import com.erickrodrigues.picpay.domain.types.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PicPayUserTest {

    private fun createCommonUser() =
        PicPayUser(
            name = Name.fromString("Erick"),
            document = UserDocument.fromString("123.123.123-12"),
            email = Email.fromString("erick@erick.com"),
            password = Password.fromString("123456"),
            type = UserType.fromString("common"),
        )

    private fun createStorekeeperUser() =
        PicPayUser(
            name = Name.fromString("Erick"),
            document = UserDocument.fromString("18.236.120/0001-58"),
            email = Email.fromString("erick@erick.com"),
            password = Password.fromString("123456"),
            type = UserType.fromString("storekeeper"),
        )

    @Test
    fun `increasing balance should work`() {
        val user = createCommonUser()
        user.increaseBalance(5.5)
        user.increaseBalance(10.5)
        assertEquals(16.0, user.currentBalance())
    }

    @Test
    fun `decreasing balance should work`() {
        val user = createCommonUser()
        user.increaseBalance(25.8)
        user.decreaseBalance(1.7)
        assertEquals(24.1, user.currentBalance())
    }

    @Test
    fun `decreasing balance should thrown an exception when user has not enough balance`() {
        val user = createCommonUser()
        user.increaseBalance(10.5)
        assertThrows<InsufficientBalanceException> { user.decreaseBalance(11.5) }
    }

    @Test
    fun `decreasing balance should throw an exception for storekeepers`() {
        val user = createStorekeeperUser()
        user.increaseBalance(5.5)
        assertThrows<ActionNotAllowedException> { user.decreaseBalance(4.0) }
    }
}
