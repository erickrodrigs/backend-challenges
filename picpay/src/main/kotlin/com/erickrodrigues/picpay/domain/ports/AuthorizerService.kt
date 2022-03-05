package com.erickrodrigues.picpay.domain.ports

import com.erickrodrigues.picpay.domain.entities.PicPayUser

interface AuthorizerService {
    fun authorize(payer: PicPayUser): Boolean
}
