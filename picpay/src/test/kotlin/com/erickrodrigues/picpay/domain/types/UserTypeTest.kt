package com.erickrodrigues.picpay.domain.types

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserTypeTest {

    @Test
    fun `should work for common and storekeeper types`() {
        val validTypes = listOf("common", "COMMON", "storekeeper", "STOREKEEPER")
        validTypes.forEach { assertDoesNotThrow { UserType.fromString(it) } }
    }

    @Test
    fun `throws an exception when type is neither common nor storekeeper`() {
        val invalidTypes = listOf("commons", "storekeepers")
        invalidTypes.forEach { assertThrows<IllegalArgumentException> { UserType.fromString(it) } }
    }
}
