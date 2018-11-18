package com.github.jntakpe.springdataauditingbugreport

import org.bson.types.ObjectId

data class User(val username: String, override val id: ObjectId? = null) : MongoAuditable()