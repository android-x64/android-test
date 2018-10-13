package com.test.admin.testproj.tests.kotlin.abstracts

abstract class Person {

    abstract var name: String

    abstract fun haveSex(vararg persons: Person)
}