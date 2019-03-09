package com.test.admin.testproj.tests.stackoverflow.b

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay

class GetInfoUsecase {
    suspend fun getInfo(): String {
        delay(1000)
        return "getInfo RESULT"
    }

    suspend fun getHeroes(page: Int): String? {
        val d: Deferred<String>? = null
        delay(2000)
        return d?.await()
    }

}