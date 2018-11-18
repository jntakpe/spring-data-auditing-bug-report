package com.github.jntakpe.springdataauditingbugreport

import org.bson.types.ObjectId
import java.time.LocalDateTime

abstract class MongoAuditable: Identifiable {

    companion object {
        private const val serialVersionUID: Long = 1
    }

    var createdBy: String? = null
    var createdAt: LocalDateTime? = null
    var lastModifiedBy: String? = null
    var lastModifiedAt: LocalDateTime? = null
}