package com.erickrodrigues.picpay.domain.types

class Name private constructor(private val value: String) {
    companion object {
        fun fromString(value: String): Name {
            if (value.isEmpty()) throw IllegalArgumentException("name must be not empty")

            return Name(value)
        }
    }

    override fun toString() = this.value
}
