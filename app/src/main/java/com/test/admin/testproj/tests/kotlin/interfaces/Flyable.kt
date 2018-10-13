package com.test.admin.testproj.tests.kotlin.interfaces

import android.util.Log

interface Flyable {
    // All properties in an interface are abstract, they can't be initialized.
    // But we can have custom getters and setters.
    var flies: Boolean

    fun fly(distMiles: Double): Unit

    fun print(text: String) {
        Log.e(javaClass.simpleName, text)
    }
}