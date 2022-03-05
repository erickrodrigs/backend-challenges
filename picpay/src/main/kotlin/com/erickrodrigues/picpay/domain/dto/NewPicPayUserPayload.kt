package com.erickrodrigues.picpay.domain.dto

data class NewPicPayUserPayload(
    val name: String,
    val document: String,
    val email: String,
    val password: String,
    val type: String,
    val balance: Double = 0.0
)
