package com.erickrodrigues.picpay.domain.ports

import com.erickrodrigues.picpay.domain.entities.PicPayUser

interface NotifierService {
    fun notify(payee: PicPayUser)
}
