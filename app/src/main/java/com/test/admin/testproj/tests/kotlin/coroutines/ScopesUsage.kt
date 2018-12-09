package com.test.admin.testproj.tests.kotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun testScopes() {
    createCoroutineScope()

}

fun createCoroutineScope() {
    CoroutineScope(Dispatchers.Main).launch {


    }
}

