package com.github.jntakpe.springdataauditingbugreport

import org.bson.types.ObjectId

/**
 *
 */
interface Identifiable {

    val id: ObjectId?
}