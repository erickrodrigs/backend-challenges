package com.erickrodrigues.picpay.domain.ports

import com.erickrodrigues.picpay.domain.entities.PicPayUser

interface PicPayUserRepository: Repository<PicPayUser> {
    fun findByEmail(email: String): PicPayUser?
    fun findByDocumentValue(document: String): PicPayUser?
    fun makeTransaction(payer: PicPayUser, payee: PicPayUser)
}
