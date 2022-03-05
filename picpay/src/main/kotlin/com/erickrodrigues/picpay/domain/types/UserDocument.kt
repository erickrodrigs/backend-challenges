package com.erickrodrigues.picpay.domain.types

class UserDocument private constructor(private val value: String) {
    companion object {
        fun fromString(value: String): UserDocument {
            if (value.isEmpty()) throw IllegalArgumentException("document cannot be empty")

            val cpfRegex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\$".toRegex()
            val matchCpf = cpfRegex.matches(value)

            if (matchCpf) return UserDocument(value)

            val cnpjRegex = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}\$".toRegex()
            val matchCnpj = cnpjRegex.matches(value)

            if (matchCnpj) return UserDocument(value)

            throw IllegalArgumentException("document value is neither a cpf nor a cnpj")
        }
    }

    override fun toString() = this.value
}
