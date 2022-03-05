package com.erickrodrigues.picpay.application.controllers

import com.erickrodrigues.picpay.application.factories.MakeTransactionFactory
import com.erickrodrigues.picpay.domain.dto.TransactionPayload
import com.erickrodrigues.picpay.domain.exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController(private val factory: MakeTransactionFactory) {

    @PostMapping
    fun makeTransaction(@RequestBody payload: TransactionPayload): ResponseEntity<Map<String, String?>> {
        val makeTransaction = factory.create()
        return try {
            makeTransaction.execute(payload)
            val message = mapOf("message" to "success")
            ResponseEntity(message, HttpStatus.OK)
        } catch (ex: Exception) {
            val error = mapOf("error" to ex.message)
            val status = when (ex) {
                is InsufficientBalanceException -> HttpStatus.CONFLICT
                is EmailAlreadyExistsException -> HttpStatus.CONFLICT
                is DocumentValueAlreadyExistsException -> HttpStatus.CONFLICT
                is UserNotFoundException -> HttpStatus.NOT_FOUND
                is ActionNotAllowedException -> HttpStatus.CONFLICT
                else -> HttpStatus.INTERNAL_SERVER_ERROR
            }

            ResponseEntity(error, status)
        }
    }
}
