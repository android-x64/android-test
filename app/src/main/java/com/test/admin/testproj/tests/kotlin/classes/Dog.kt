package com.test.admin.testproj.tests.kotlin.classes

// if class is not "open" we can't inherit it
open class Dog(name: String, //this is a primary constructor with parameters, except "owner" - it is a property
          height: Double,
          weight: Double,
          var owner: String) : Animal(name, height, weight) {

    init {
        // here we can initialize members of the class

        print("Dog init block")
    }

    // if we add "final" - that means function can't be overridden in subclasses of class Dog
    final override fun printInfo() {
        print("$name is $height tall and weights $weight and is owned by $owner")
    }
}