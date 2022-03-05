package com.erickrodrigues.picpay.domain.services

import com.erickrodrigues.picpay.domain.dto.NewPicPayUserPayload
import com.erickrodrigues.picpay.domain.entities.PicPayUser
import com.erickrodrigues.picpay.domain.exceptions.DocumentValueAlreadyExistsException
import com.erickrodrigues.picpay.domain.exceptions.EmailAlreadyExistsException
import com.erickrodrigues.picpay.domain.ports.PicPayUserRepository
import com.erickrodrigues.picpay.domain.types.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.mockito.kotlin.any

class RegisterNewUserTest {

    private val repo by lazy { mock(PicPayUserRepository::class.java) }
    private val useCase by lazy { RegisterNewUser(repo) }

    private fun createPicPayUser(document: String, email: String) =
        PicPayUser(
            name = Name.fromString("Erick"),
            document = UserDocument.fromString(document),
            email = Email.fromString(email),
            password = Password.fromString("123456"),
            type = UserType.fromString("common")
        )

    private fun createPayload(document: String, email: String) =
        NewPicPayUserPayload(
            name = "Erick",
            document = document,
            email = email,
            password = "123456",
            type = "common"
        )

    @Test
    fun `throws an exception when user with given email already exists`() {
        `when`(repo.findByEmail("erick@erick.com")).thenReturn(
            createPicPayUser("111.222.111-11", "erick@erick.com")
        )
        assertThrows<EmailAlreadyExistsException> {
            useCase.execute(createPayload(
                document = "111.222.111-11",
                email = "erick@erick.com"
            ))
        }
    }

    @Test
    fun `throws an exception when user with given document already exists`() {
        `when`(repo.findByDocumentValue("111.111.111-11"))
            .thenReturn(
                createPicPayUser("111.111.111-11", "ersanta@something.fake")
            )
        assertThrows<DocumentValueAlreadyExistsException> {
            useCase.execute(createPayload(
                document = "111.111.111-11",
                email = "ersanta@something.fake"
            ))
        }
    }

    @Test
    fun `should save new user`() {
        useCase.execute(createPayload(
            document = "111.111.111-11",
            email = "erick@erick.com"
        ))
        verify(repo, times(1)).save(any())
    }
}
