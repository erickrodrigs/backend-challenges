package com.erickrodrigues.picpay.application.factories

import com.erickrodrigues.picpay.domain.services.MakeTransaction

interface MakeTransactionFactory {
    fun create(): MakeTransaction
}
