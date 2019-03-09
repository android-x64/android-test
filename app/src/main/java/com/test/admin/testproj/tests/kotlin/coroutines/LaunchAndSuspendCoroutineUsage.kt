package com.test.admin.testproj.tests.kotlin.coroutines
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

// If we want to launch something and forget about it use `CoroutineScope.launch` coroutine builder.

fun testLaunch() {
    //postItem("Hello Launch")
    launchWithContextInside()
}

fun launchWithContextInside() = GlobalScope.launch {
    // line 1. thread -> Thread[DefaultDispatcher-worker-1,5,main]

    delay(1000) // simulate IO operation

    // `withContext` suspends the Coroutine. It means lines of code will be executed consistently:
    // line 1 -> line 2 -> line 3
    withContext(Dispatchers.Main) {

        // line 2. main thread -> Thread[main,5,main]
        delay(5000) // doesn't block the Main thread

        // we can update UI here

    }

    // line 3. thread -> Thread[DefaultDispatcher-worker-1,5,main]
}



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

// 'suspendCoroutine' suspends coroutine in which it executed until we decide to continue by
// calling appropriate methods - 'Continuation.resume...'
// 'suspendCoroutine' mainly used when we have some legacy code with callbacks, e.g.:
//
// suspend fun getUser(id: String): User {
//    return suspendCoroutine { continuation ->
//          Api.getUser(id) { user ->
//              continuation.resume(user)
//          }
//    }
//}

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

// There is also 'suspendCancellableCoroutine' function, it behaves similar to 'suspendCoroutine'
// with additional feature - provides an implementation of 'CancellableContinuation' to the block , e.g.:
//
// suspend fun getUser(id: String): User {
//    return suspendCancellableCoroutine { continuation ->
//          Api.getUser(id) { user ->
//              continuation.resume(user)
//          }
//          continuation.invokeOnCancellation {
//              // clear some resources, cancel tasks, close streams etc.
//          }
//    }
//}