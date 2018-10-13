package com.test.admin.testproj.tests.kotlin.generics

interface Repository<T> {
    fun getById(id: Int): T
    fun getAll(): List<T>
}