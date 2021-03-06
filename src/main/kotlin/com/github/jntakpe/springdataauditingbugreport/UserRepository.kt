package com.github.jntakpe.springdataauditingbugreport

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository: ReactiveMongoRepository<User, ObjectId>