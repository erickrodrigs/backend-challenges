package com.erickrodrigues.picpay.application.controllers

import com.erickrodrigues.picpay.application.factories.RegisterUserFactory
import com.erickrodrigues.picpay.domain.dto.NewPicPayUserPayload
import com.erickrodrigues.picpay.domain.entities.PicPayUser
import com.erickrodrigues.picpay.domain.ports.PicPayUserRepository
import com.erickrodrigues.picpay.domain.services.RegisterNewUser
import com.erickrodrigues.picpay.domain.types.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class PicPayUserControllerTest {
    private val repo by lazy { mock(PicPayUserRepository::class.java) }
    private val registerUser by lazy { RegisterNewUser(repo) }
    private val factory by lazy {
        val mocked = mock(RegisterUserFactory::class.java)
        `when`(mocked.create()).thenReturn(registerUser)
        mocked
    }
    private val controller by lazy { PicPayUserController(factory) }

    @Nested
    @DisplayName("when user is successfully created")
    inner class SuccessfulCreation {
        private lateinit var response: ResponseEntity<Map<String, String?>>

        @BeforeEach
        fun init() {
            response = controller.registerNewUser(
                NewPicPayUserPayload(
                    name = "Erick",
                    document = "123.123.123-12",
                    email = "erick@erick.com",
                    password = "123456",
                    type = "common",
                    balance = 232.46
                )
            )
        }

        @Test
        fun `returns correct response status`() {
            assertEquals(HttpStatus.CREATED, response.statusCode)
        }

        @Test
        fun `new user is saved in database`() {
            verify(repo, times(1)).save(any())
        }
    }

    @Nested
    @DisplayName("when user creation fails")
    inner class CreationFails {

        private fun mockPicPayUser() = PicPayUser(
            name = Name.fromString("Erick"),
            document = UserDocument.fromString("123.123.123-12"),
            email = Email.fromString("erick@erick.com"),
            password = Password.fromString("123456"),
            type = UserType.fromString("common")
        )

        @Test
        fun `fails when user with given email already exists`() {
            `when`(repo.findByEmail("erick@erick.com")).thenReturn(mockPicPayUser())
            val response = controller.registerNewUser(
                NewPicPayUserPayload(
                    name = "Erick",
                    document = "123.123.123-12",
                    email = "erick@erick.com",
                    password = "123456",
                    type = "common",
                    balance = 232.46
                )
            )

            assertEquals(HttpStatus.CONFLICT, response.statusCode)
            verify(repo, times(0)).save(any())
        }

        @Test
        fun `fails when user with given document value already exists`() {
            `when`(repo.findByDocumentValue("123.123.123-12")).thenReturn(mockPicPayUser())
            val response = controller.registerNewUser(
                NewPicPayUserPayload(
                    name = "Erick",
                    document = "123.123.123-12",
                    email = "erick@erick.com",
                    password = "123456",
                    type = "common",
                    balance = 232.46
                )
            )

            assertEquals(HttpStatus.CONFLICT, response.statusCode)
            verify(repo, times(0)).save(any())
        }
    }
}
