package com.erickrodrigues.picpay.domain.services

import com.erickrodrigues.picpay.domain.dto.NewPicPayUserPayload
import com.erickrodrigues.picpay.domain.entities.PicPayUser
import com.erickrodrigues.picpay.domain.exceptions.DocumentValueAlreadyExistsException
import com.erickrodrigues.picpay.domain.exceptions.EmailAlreadyExistsException
import com.erickrodrigues.picpay.domain.ports.PicPayUserRepository
import com.erickrodrigues.picpay.domain.types.*

class RegisterNewUser(private val repo: PicPayUserRepository) {

    fun execute(payload: NewPicPayUserPayload) {
        val (name, document, email, password, type, balance) = payload
        var user = repo.findByEmail(email)

        if (user != null) throw EmailAlreadyExistsException("that email is already being used")

        user = repo.findByDocumentValue(document)

        if (user != null) throw DocumentValueAlreadyExistsException("that document value already exists")

        repo.save(
            PicPayUser(
                name = Name.fromString(name),
                email = Email.fromString(email),
                document = UserDocument.fromString(document),
                password = Password.fromString(password),
                type = UserType.fromString(type),
                balance = balance
            )
        )
    }
}
