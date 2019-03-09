package com.test.admin.testproj.tests.kotlin.extensions

// merges two maps in one:
//          val mapA = mapOf("Emergency" to "112", "Fire department" to "101", "Police" to "102")
//          val mapB = mapOf("Emergency" to "911", "Police" to "102")
//          val result = mapA mergeWith mapB
//    result = {Emergency=112, 911, Fire department=101, Police=102}
infix fun Map<String, String>.mergeWith(anotherMap: Map<String, String>): Map<String, String> {
    return (keys + anotherMap.keys).associateWith {
        setOf(this[it], anotherMap[it]).filterNotNull().joinToString()
    }
}

