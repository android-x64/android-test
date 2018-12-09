package com.test.admin.testproj.tests.kotlin.metaprogramming

import android.util.Log
import java.lang.reflect.Type

class Transaction(val id: Int, val amount: Double, var desription: String = "Default") {
    fun validate() {
        if (amount > 10000) {
            //...
        }
    }
}

fun testJavaReflection() {
    introspectInstance(Transaction(99, 400.0, "Some desc"))
    printType(Transaction::class.java)
}

fun introspectInstance(obj: Any) {
    Log.e("WWW", "WWW JReflection Class ${obj.javaClass.simpleName}")
    Log.e("WWW", "WWW JReflection Properties: ")
    obj.javaClass.declaredFields.forEach {
        Log.e("WWW", "          ${it.name} of type ${it.type} ")
    }
    Log.e("WWW", "WWW JReflection Functions: ")
    obj.javaClass.methods.forEach {
        Log.e("WWW", "          ${it.name} with return type ${it.returnType} ")
    }
}

fun printType(obj: Type) {
    //Log.e("WWW", "WWW ${obj.typeName}") // 'typeName' - is hidden for now
    Log.e("WWW", "WWW JReflection $obj")
}