package com.test.admin.testproj.tests.kotlin.coroutines

import kotlinx.coroutines.*

fun testTimeouts() = runBlocking {

    // doesn't throw an Exception
    val job = withTimeoutOrNull(100) {

        repeat(100) {
            print(".")
            delay(10)
        }
    }
    // 'job' may be null


    // throws an Exception
    try {
        withTimeout(100) {

            repeat(100) {
                print(".")
                delay(10)
            }
        }
    } catch (ex: TimeoutCancellationException) {}
}