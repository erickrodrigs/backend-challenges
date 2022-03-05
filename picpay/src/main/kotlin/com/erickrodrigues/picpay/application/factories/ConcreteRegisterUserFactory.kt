package com.erickrodrigues.picpay.application.factories

import com.erickrodrigues.picpay.domain.ports.PicPayUserRepository
import com.erickrodrigues.picpay.domain.services.RegisterNewUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ConcreteRegisterUserFactory: RegisterUserFactory {

    @Autowired
    private lateinit var repo: PicPayUserRepository

    override fun create() = RegisterNewUser(repo)
}
