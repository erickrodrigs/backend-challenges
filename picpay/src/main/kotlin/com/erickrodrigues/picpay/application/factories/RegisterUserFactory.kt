package com.erickrodrigues.picpay.application.factories

import com.erickrodrigues.picpay.domain.services.RegisterNewUser

interface RegisterUserFactory {
    fun create(): RegisterNewUser
}
