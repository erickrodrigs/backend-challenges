package com.erickrodrigues.picpay.domain.entities

import com.erickrodrigues.picpay.domain.exceptions.ActionNotAllowedException
import com.erickrodrigues.picpay.domain.exceptions.InsufficientBalanceException
import com.erickrodrigues.picpay.domain.types.*

data class PicPayUser(
    val name: Name,
    val document: UserDocument,
    val email: Email,
    val password: Password,
    val type: UserType,
    private var balance: Double = 0.0
): Entity() {
    constructor(
        id: String,
        name: Name,
        document: UserDocument,
        email: Email,
        password: Password,
        type: UserType,
        balance: Double = 0.0
    ): this(name, document, email, password, type, balance) {
        this.id = id
    }

    fun currentBalance() = balance

    fun increaseBalance(value: Double) {
        balance += value
    }

    fun decreaseBalance(value: Double) {
        if (type.toString() == "storekeeper") {
            throw ActionNotAllowedException("storekeeper can't transfer value")
        }

        if (!hasEnoughBalance(value)) {
            throw InsufficientBalanceException("insufficient balance for this user")
        }

        balance -= value
    }

    private fun hasEnoughBalance(value: Double) = balance >= value
}
