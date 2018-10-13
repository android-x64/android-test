package com.test.admin.testproj.tests.kotlin.interfaces

import com.test.admin.testproj.tests.kotlin.classes.Animal

interface AnimalFactory {
    fun create(name: String): Animal
}