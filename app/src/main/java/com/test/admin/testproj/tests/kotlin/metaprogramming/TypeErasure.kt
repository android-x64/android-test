package com.test.admin.testproj.tests.kotlin.metaprogramming

import android.util.Log

// Type Erasure on the JVM - the JVM doesn't maintain information about the actual type parameter,
// used in a Generic class;
// This is applicable only to Generics
// (Type Erasure feature saves memory and keeps backwards compatibility)

fun testTypeErasure() {
    val listStrings = listOf("a", "b", "c")
    val listInts = listOf(1, 2, 3)
    printList(listStrings)
    printList(listInts)

    avoidTypeErasure<List<Int>>(listInts)

    typeInfo<Transaction>()


}

fun <T> printList(list: List<T>) {
    // We can't check type of T
    //when (list) {
        //is List<String> -> print("This is a list of Strings")
        //is List<Int> -> print("This is a list of Ints")
    //}

    // Only thing that we can do is to check if this is a list of something
    if (list is List<*>) {
        // This is a list
    }
}

// use 'reified' to check for a specific type;
// applicable to 'inline' only;
// can't create instances of 'T'
inline fun <reified T> avoidTypeErasure(input: List<Any>) {
    if (input is T) {

    }
}

inline fun <reified T> typeInfo() {
    Log.e("WWW", "WWW TypeErasure type: ${T::class}")
}