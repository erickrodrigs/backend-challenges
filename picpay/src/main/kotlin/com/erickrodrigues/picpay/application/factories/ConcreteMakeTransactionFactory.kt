package com.erickrodrigues.picpay.application.factories

import com.erickrodrigues.picpay.domain.ports.AuthorizerService
import com.erickrodrigues.picpay.domain.ports.NotifierService
import com.erickrodrigues.picpay.domain.ports.PicPayUserRepository
import com.erickrodrigues.picpay.domain.services.MakeTransaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ConcreteMakeTransactionFactory : MakeTransactionFactory {

    @Autowired
    private lateinit var repo: PicPayUserRepository

    @Autowired
    private lateinit var authorizer: AuthorizerService

    @Autowired
    private lateinit var notifier: NotifierService

    override fun create() = MakeTransaction(repo, authorizer, notifier)
}
