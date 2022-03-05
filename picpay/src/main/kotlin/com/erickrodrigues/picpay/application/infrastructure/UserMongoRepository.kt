package com.erickrodrigues.picpay.application.infrastructure

import org.springframework.data.mongodb.repository.MongoRepository

interface UserMongoRepository : MongoRepository<UserSchema, String> {
    fun findByEmail(email: String): UserSchema?
    fun findByDocument(document: String): UserSchema?
}
