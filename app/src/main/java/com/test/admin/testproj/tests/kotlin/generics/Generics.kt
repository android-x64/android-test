package com.test.admin.testproj.tests.kotlin.generics

import com.test.admin.testproj.tests.kotlin.LearnKotlinActivity
import java.io.Serializable
import java.util.*

// "T: Entity" - single upper-bound restriction on the type parameter
class SingleUpperBoundRestrictedRepository<T: Entity> {
    fun save(entity: T) {
        if(entity.id != 0) {
            // ...
        }
    }
}

// multiple upper-bound restrictions on the type parameter
// when using class must extend Entity and implement Serializable
class MultipleUpperBoundRestrictedRepository<T> where T: Entity, T: Serializable {
    fun save(entity: T) {
        if(entity.id != 0) {
            // ...
        }
    }
}

// restrictions on functions
fun <T: Serializable> streamObject(obj: T) {

}

fun genericsTest() {
    val repo = MultipleUpperBoundRestrictedRepository<EntityInheritted>()

}



open class Entity(val id: Int)
open class EntityInheritted(id: Int) : Entity(id), Serializable


