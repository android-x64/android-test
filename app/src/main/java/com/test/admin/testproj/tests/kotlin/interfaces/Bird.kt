package com.test.admin.testproj.tests.kotlin.interfaces

class Bird constructor(val name: String, override var flies: Boolean) : Flyable {

    override fun fly(distMiles: Double) {
        if (flies) {
            print("$name flies $distMiles miles")
        }
    }
}