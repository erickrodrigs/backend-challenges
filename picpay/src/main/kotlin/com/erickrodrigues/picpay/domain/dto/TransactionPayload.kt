package com.erickrodrigues.picpay.domain.dto

data class TransactionPayload(val value: Double, val payer: String, val payee: String)
