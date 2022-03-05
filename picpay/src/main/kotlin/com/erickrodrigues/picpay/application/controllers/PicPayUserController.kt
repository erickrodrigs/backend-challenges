package com.erickrodrigues.picpay.application.controllers

import com.erickrodrigues.picpay.application.factories.RegisterUserFactory
import com.erickrodrigues.picpay.domain.dto.NewPicPayUserPayload
import com.erickrodrigues.picpay.domain.exceptions.DocumentValueAlreadyExistsException
import com.erickrodrigues.picpay.domain.exceptions.EmailAlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class PicPayUserController(private val factory: RegisterUserFactory) {

    @PostMapping
    fun registerNewUser(@RequestBody payload: NewPicPayUserPayload): ResponseEntity<Map<String, String?>> {
        val registerNewUser = factory.create()
        return try {
            registerNewUser.execute(payload)
            val message = mapOf("message" to "success")
            ResponseEntity(message, HttpStatus.CREATED)
        } catch (ex: Exception) {
            val message = mapOf("error" to ex.message)
            val status = when (ex) {
                is EmailAlreadyExistsException -> HttpStatus.CONFLICT
                is DocumentValueAlreadyExistsException -> HttpStatus.CONFLICT
                else -> HttpStatus.INTERNAL_SERVER_ERROR
            }
            ResponseEntity(message, status)
        }
    }
}
