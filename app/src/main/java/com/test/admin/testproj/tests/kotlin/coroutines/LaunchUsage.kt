package com.test.admin.testproj.tests.kotlin.coroutines
import android.media.Image
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun postItem(item: Any) {
    // here is main thread (if 'postItem(Any)' is called from 'main' thread) -> Thread[main,5,main]

    // launch() - returns immediately, coroutine works in background thread
    val job = GlobalScope.launch {
        // here is new background thread is created -> Thread[DefaultDispatcher-worker-1,5,main]

        val token = preparePost()
        // here is background thread -> Thread[DefaultDispatcher-worker-1,5,main]

        val post = submitPost(token, item)
        // here is background thread -> Thread[DefaultDispatcher-worker-1,5,main]

        processPost(post)
    }

    // We can cancel the job (e.g. when Activity is destroyed)
    //job.cancel()
}

suspend fun preparePost(): String {
    // makes request & suspends coroutine

    val request = composeTokenRequest()
    val result = makeRequest(request)
    return "Some Token" // result.parseToken()
}


suspend fun submitPost(token: String, item: Any): Any {
    return suspendCoroutine {
        // here is background thread -> Thread[DefaultDispatcher-worker-1,5,main]

        /* ... */

        it.resume(Any())
    }
}

fun processPost(post: Any) {
    // here is background thread -> Thread[DefaultDispatcher-worker-1,5,main]
}

fun composeTokenRequest(): Any {
    return Any()
}

suspend fun makeRequest(request: Any): Any {
    return suspendCoroutine {
        // here is background thread -> Thread[DefaultDispatcher-worker-1,5,main]

        /* ... */

        it.resume(Any())
    }
}