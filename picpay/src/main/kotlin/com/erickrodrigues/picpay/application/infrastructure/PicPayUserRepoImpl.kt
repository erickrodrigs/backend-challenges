package com.erickrodrigues.picpay.application.infrastructure

import com.erickrodrigues.picpay.domain.entities.PicPayUser
import com.erickrodrigues.picpay.domain.ports.PicPayUserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
open class PicPayUserRepoImpl(private val repo: UserMongoRepository): PicPayUserRepository {

    override fun findById(id: String) = UserSchema.toDomain(repo.findById(id).orElse(null))

    override fun findByEmail(email: String) = UserSchema.toDomain(repo.findByEmail(email))

    override fun findByDocumentValue(document: String) = UserSchema.toDomain(repo.findByDocument(document))

    override fun save(entity: PicPayUser) {
        repo.save(UserSchema.toSchema(entity))
    }

    @Transactional
    override fun makeTransaction(payer: PicPayUser, payee: PicPayUser) {
        repo.save(UserSchema.toSchema(payer))
        repo.save(UserSchema.toSchema(payee))
        println("finish transaction")
    }
}
