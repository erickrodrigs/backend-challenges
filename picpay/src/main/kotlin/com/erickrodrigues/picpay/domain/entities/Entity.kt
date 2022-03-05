package com.erickrodrigues.picpay.domain.entities

import java.util.UUID

abstract class Entity {
    var id: String
        protected set

    init {
        this.id = UUID.randomUUID().toString()
    }

    override fun equals(other: Any?): Boolean {
        return (other is Entity) && (this.id == other.id)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
