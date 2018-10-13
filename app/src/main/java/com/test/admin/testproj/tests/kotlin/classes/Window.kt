package com.test.admin.testproj.tests.kotlin.classes

import android.util.Log
import com.test.admin.testproj.tests.kotlin.multiply as multiplyByOne

// In this case "height" and "width" are not properties, they are just value passed to the constructor.
// To make them as properties we need to add "var" or "val" keywords
class Window(height: Double,   //this is a primary constructor with parameters
              width: Double) {

    private val h: Double = height
    private val w: Double = width
    var color: Int = 0

    // Read-only property
    val area: Double
        get() = h * w           // Custom getter

    var brand: String = ""
        set(value) {            // Custom setter
            if (value == "") {
                throw IllegalArgumentException("Brand should not be empty")
            }

            field = value  // "field" - "backing" field - holds the value of the property
        }

    init {
        // here we can initialize members of the class. It is called right away after instance creation
        print("Window, init block")

    }

    // secondary constructor
    constructor(height: Double, width: Double, color: Int): this(height, width) { // we have to call the primary constructor

        print("Window, secondary constructor")

        this.color = color
    }

    fun isBroken(): Boolean {
        return false
    }

    fun someCalculations() {
        multiplyByOne(h.toInt())
    }

    private fun print(text: String) {
        Log.e(javaClass.simpleName, text)
    }
}