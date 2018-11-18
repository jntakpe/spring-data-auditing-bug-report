package com.github.jntakpe.springdataauditingbugreport

import org.bson.types.ObjectId
import java.time.LocalDateTime

data class User(val username: String, override val id: ObjectId? = null) : MongoAuditable()

abstract class MongoAuditable: Identifiable {

    companion object {
        private const val serialVersionUID: Long = 1
    }

    var createdBy: String? = null
    var createdAt: LocalDateTime? = null
    var lastModifiedBy: String? = null
    var lastModifiedAt: LocalDateTime? = null
}

interface Identifiable {

    val id: ObjectId?
}