package com.test.admin.testproj.tests.stackoverflow.c

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchInterface {
    //http request
    @GET("search/users")
    fun searchAsync(@Query("q") query: String,
                    @Query("page") page: Int,
                    @Query("per_page") perPage: Int): Deferred<String>

    //singleton instance of Retrofit
    companion object Factory { /* build retrofit instance */ }
}