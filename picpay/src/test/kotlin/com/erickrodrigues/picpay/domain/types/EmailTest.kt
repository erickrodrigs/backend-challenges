package com.erickrodrigues.picpay.domain.types

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EmailTest {

    @Test
    fun `should create an email value object when value is correct`() {
        assertDoesNotThrow { Email.fromString("erick@erick.com") }
        assertDoesNotThrow { Email.fromString("a@uci.br") }
        assertDoesNotThrow { Email.fromString("a.b.c@d") }
    }

    @Test
    fun `throws an exception when email value is not valid`() {
        assertThrows<IllegalArgumentException> { Email.fromString("erick") }
        assertThrows<IllegalArgumentException> { Email.fromString("erick.com") }
    }
}
