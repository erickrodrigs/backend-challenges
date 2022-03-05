package com.erickrodrigues.picpay.domain.services

import com.erickrodrigues.picpay.domain.dto.TransactionPayload
import com.erickrodrigues.picpay.domain.exceptions.ActionNotAllowedException
import com.erickrodrigues.picpay.domain.exceptions.UserNotFoundException
import com.erickrodrigues.picpay.domain.ports.AuthorizerService
import com.erickrodrigues.picpay.domain.ports.NotifierService
import com.erickrodrigues.picpay.domain.ports.PicPayUserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MakeTransaction(
    private val repo: PicPayUserRepository,
    private val authorizer: AuthorizerService,
    private val notifier: NotifierService
) {
    fun execute(payload: TransactionPayload) = runBlocking {
        val payer = repo.findById(payload.payer)
            ?: throw UserNotFoundException("user with id ${payload.payer} not found")
        val payee = repo.findById(payload.payee)
            ?: throw UserNotFoundException("user with id ${payload.payee} not found")

        if (!authorizer.authorize(payer)) {
            throw ActionNotAllowedException("payer is not authorized to do this action")
        }

        payer.decreaseBalance(payload.value)
        payee.increaseBalance(payload.value)
        repo.makeTransaction(payer, payee)
        launch { notifier.notify(payee) }
        println("finish use case")
    }
}
