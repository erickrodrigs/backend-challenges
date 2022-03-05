package com.erickrodrigues.picpay.domain.ports

interface Repository<T> {
    fun save(entity: T)
    fun findById(id: String): T?
}
