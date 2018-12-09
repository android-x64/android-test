package com.test.admin.testproj.tests.kotlin.generics

// "T" has type "Any?"
interface Repository<T> {
    fun getById(id: Int): T
    fun getAll(): List<T>
}