package com.erickrodrigues.picpay.domain.types

class Email private constructor(private val value: String) {
    companion object {
        fun fromString(value: String): Email {
            if (value.isEmpty()) throw IllegalArgumentException("email must be not empty")

            val emailRgx = "^(.+)@(.+)\$".toRegex()
            val match = emailRgx.matches(value)

            if (!match) throw IllegalArgumentException("value is not a email")

            return Email(value)
        }
    }

    override fun toString() = this.value
}
