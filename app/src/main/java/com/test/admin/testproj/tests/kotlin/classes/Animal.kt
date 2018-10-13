package com.test.admin.testproj.tests.kotlin.classes

import android.util.Log
import com.test.admin.testproj.tests.kotlin.interfaces.AnimalFactory

// By default all classes are inherited from "Any" class
// There are no fields in Kotlin
// "open" means not "final". By default all classes and properties are "final"
open class Animal(open val name: String,   //this is a primary constructor with properties(members of the class)
                  var height: Double,
                  var weight: Double) {

    init {
        // here we can initialize members of the class

        print("Animal init block")

        val regex = Regex(".*\\d+.*") // don't allow numbers in "name"
        require(!name.matches(regex)) { "Animal name can't contain numbers" }
        require(height > 0) { "Height must be greater than 0" }
        require(weight > 0) { "Weight must be greater than 0" }
    }

    // secondary constructor
    constructor(name: String, height: Double, weight: Double, numberOfLegs: Int)
            : this(name, height, weight) {
        // do some stuff
    }

    // Companion objects are also used as Factories
    companion object: AnimalFactory {
        override fun create(name: String): Animal {
            return when(name) {
                "Cat" -> Cat("Kitty", 12.5, 10.0, "Black")
                else -> Dog("Buddy", 9.5, 15.0, "Me")
            }
        }

    }

    open fun eat() {
        print("Animal is eating")
    }

    open fun printInfo(): Unit { // use "open" if we want our function could be overridden
        print("$name is $height tall and weights $weight")
    }

    fun print(text: String) {
        Log.e(javaClass.simpleName, text)
    }
}