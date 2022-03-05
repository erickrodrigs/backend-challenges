package com.erickrodrigues.picpay.domain.types

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PasswordTest {

    @Test
    fun `throws an exception when password has less than 6 characters`() {
        assertThrows<IllegalArgumentException> { Password.fromString("12345") }
    }

    @Test
    fun `should create an encrypted password`() {
        val password = Password.fromString("abc1234")
        println(password)
    }
}
