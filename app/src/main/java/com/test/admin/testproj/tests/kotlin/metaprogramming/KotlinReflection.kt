package com.test.admin.testproj.tests.kotlin.metaprogramming

import android.util.Log
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties


fun testKotlinReflection1() {
    Log.e("WWW", "WWW KReflection class: ${Transaction::class}")

    val kClass = Transaction::class
    Log.e("WWW", "WWW KReflection Member Properties:")
    kClass.memberProperties.forEach {
        Log.e("WWW", "          ${it.name} with return type ${it.returnType} ")
    }
    Log.e("WWW", "WWW KReflection Constructors:")
    kClass.constructors.forEach {
        Log.e("WWW", "          ${it.name} with params ${it.parameters} ")
    }

    printKotlinType(kClass)

    // Creating an Instance
    val constructor = ::Transaction
    var transaction = constructor(20, 12.0, "Simple transaction") // or invoke 'call' or 'callBy' methods

    // use 'callBy' function to call constructor with default parameters
    // using positions  of parameters
    transaction = constructor.callBy(mapOf(
            constructor.parameters[0] to 20,
            constructor.parameters[1] to 12.0
    ))

    // using names of parameters
    val idParam = constructor.parameters.first { it.name == "id" }
    val amountParam = constructor.parameters.first { it.name == "amount" }
    transaction = constructor.callBy(mapOf(
            idParam to 20,
            amountParam to 12.0
    ))

    // Reference to 'validate' function
    val validateFunc = Transaction::validate


    // Read value of the property
    val trans = Transaction(12, 40.0, "Some value")
    val descriptionProperty = Transaction::class.memberProperties.find { it.name == "description" }
    Log.e("WWW", "WWW KReflection property value: ${descriptionProperty?.get(trans)}")

}

fun printKotlinType(obj: KClass<*>) {
    Log.e("WWW", "WWW KReflection type: ${obj.qualifiedName}")
}
