package com.erickrodrigues.picpay.domain.types

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserDocumentTest {

    @Test
    fun `should create document for a valid cpf`() {
        assertDoesNotThrow { UserDocument.fromString("123.123.123-12") }
    }

    @Test
    fun `should create document for a valid cnpj`() {
        assertDoesNotThrow { UserDocument.fromString("12.123.123/1234-12") }
    }

    @Test
    fun `throws an exception when cpf is invalid`() {
        assertThrows<IllegalArgumentException> { UserDocument.fromString("12312312312") }
    }

    @Test
    fun `throws an exception when cnpj is invalid`() {
        assertThrows<IllegalArgumentException> { UserDocument.fromString("12123123123412") }
    }
}
