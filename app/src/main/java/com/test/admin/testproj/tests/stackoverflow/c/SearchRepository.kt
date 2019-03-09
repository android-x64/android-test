package com.test.admin.testproj.tests.stackoverflow.c

import java.io.IOException

class SearchRepository(private val searchInterface: SearchInterface) {
//    suspend fun search(user: String): RTResponse<Result> {
//        val response = searchInterface.searchAsync(query = user, page = 1, perPage = 10).await()
//        return try {
//            if (response.isSuccessful) RTResponse.Success(response.body()!!) else RTResponse.Error(IOException(""))
//        } catch (e: java.lang.Exception) {
//            RTResponse.Error(IOException("Unable to fetch users"))
//        }
//    }
}