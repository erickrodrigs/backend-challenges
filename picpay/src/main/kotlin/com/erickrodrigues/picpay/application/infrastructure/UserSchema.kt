package com.erickrodrigues.picpay.application.infrastructure

import com.erickrodrigues.picpay.domain.entities.PicPayUser
import com.erickrodrigues.picpay.domain.types.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class UserSchema(
    @Id
    val id: String,
    val name: String,
    val document: String,
    val email: String,
    val password: Pair<ByteArray, ByteArray>,
    val type: String,
    val balance: Double
) {
    companion object {
        fun toDomain(schema: UserSchema?): PicPayUser? {
            if (schema == null) return null

            val (id, name, document, email, password, type, balance) = schema
            return PicPayUser(
                id = id,
                name = Name.fromString(name),
                document = UserDocument.fromString(document),
                email = Email.fromString(email),
                password = Password.fromEncryption(password.first, password.second),
                type = UserType.fromString(type),
                balance = balance
            )
        }

        fun toSchema(user: PicPayUser) =
            UserSchema(
                id = user.id,
                name = user.name.toString(),
                document = user.document.toString(),
                email = user.email.toString(),
                password = user.password.toPair(),
                type = user.type.toString(),
                balance = user.currentBalance()
            )
    }
}
