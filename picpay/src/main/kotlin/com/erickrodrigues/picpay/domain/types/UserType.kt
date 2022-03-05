package com.erickrodrigues.picpay.domain.types

import java.util.*

class UserType private constructor(private val value: String) {
    companion object {
        fun fromString(value: String): UserType {
            if (value.isEmpty()) throw IllegalArgumentException("user type must not be empty")

            val possibleTypes = listOf("common", "storekeeper")
            val isFromAValidType = possibleTypes.any { it.equals(value, ignoreCase = true) }

            if (!isFromAValidType) throw IllegalArgumentException("user type is neither a common nor a storekeeper")

            return UserType(value.lowercase(Locale.getDefault()))
        }
    }

    override fun toString() = this.value
}
