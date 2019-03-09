package com.test.admin.testproj.tests.kotlin.classes

import android.util.Log
import com.test.admin.testproj.tests.kotlin.interfaces.Eating
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// Class delegation
// Delegating methods of Eating interface to "cat" object (it also implements Eating interface)
data class Delegating(val cat: Cat) : Eating by cat {

    // Delegating properties
    var someProperty: String by ExternalFunctionality()

    // Local Delegates
    val lazyProperty by lazy { initLate() }


    // BuiltIn Delegates

    // observable - fires off every time value of the property changes
    var property1: String by Delegates.observable("") { property, oldValue, newValue ->
        Log.e("WWW", "WWW BuiltIn Delegates.observable; anotherProperty: " +
                "property.name=${property.name} changed from $oldValue to $newValue")
    }

    // vetoable - fires off every time value of the property changes and restricts values to be saved
    var property2: String by Delegates.vetoable("Initial") { property, oldValue, newValue ->
        Log.e("WWW", "WWW BuiltIn Delegates.vetoable; anotherProperty: " +
                "property.name=${property.name} changed from $oldValue to $newValue")
        !newValue.isEmpty()
    }


    private fun initLate(): Int {
        var count = 10
        // maybe do some calculations ...
        return count
    }
}

class ExternalFunctionality {

    var backingField = "Default"
    operator fun getValue(thisRef: Delegating, property: KProperty<*>): String {
        Log.e("WWW", "WWW Delegating properties; getValue(): property.name=${property.name}")
        return backingField
    }

    operator fun setValue(thisRef: Delegating, property: KProperty<*>, value: String) {
        backingField = value
    }
}
