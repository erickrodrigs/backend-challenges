package com.erickrodrigues.picpay.domain.types

import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class Password private constructor(private val encrypted: ByteArray, private val salt: ByteArray) {
    companion object {
        fun fromString(value: String): Password {
            if (value.isEmpty()) throw IllegalArgumentException("password must be not empty")

            if (value.length < 6) throw IllegalArgumentException("password must have at least six characters")

            val salt = generateSalt()
            val encrypted = getEncryptedPassword(value, salt)
            return Password(encrypted, salt)
        }

        fun fromEncryption(encrypted: ByteArray, salt: ByteArray) = Password(encrypted, salt)

        @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
        private fun getEncryptedPassword(password: String, salt: ByteArray): ByteArray {
            val algorithm = "PBKDF2WithHmacSHA1"
            val derivedKeyLength = 160
            val iterations = 20000
            val spec = PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength)
            val f = SecretKeyFactory.getInstance(algorithm)
            return f.generateSecret(spec).encoded
        }

        @Throws(NoSuchAlgorithmException::class)
        private fun generateSalt(): ByteArray {
            val random = SecureRandom.getInstance("SHA1PRNG")
            val salt = ByteArray(8)
            random.nextBytes(salt)
            return salt
        }
    }

    fun toPair() = Pair(encrypted, salt)
}
