package com.test.admin.testproj.tests.kotlin.classes

import com.test.admin.testproj.tests.kotlin.interfaces.Eating

class Cat(name: String, height: Double, weight: Double, var color: String)
    : Animal(name, height, weight), Eating {

    override var name: String = "John Dow" // overriding property

    // in secondary constructor we need to call primary constructor or super if primary is empty
    constructor(name: String, height: Double, weight: Double, color: String, canPurr: Boolean)
            : this(name, height, weight, color) {

    }

    override fun eat() {
        // we use supertype qualification to tell that we invoke super method from Animal class,
        // not from Eating interface
        super<Animal>.eat()
        // but if we want also to call method from Eating interface
        super<Eating>.eat()

        print("Cat is eating")
    }

    fun myau() {

    }
}