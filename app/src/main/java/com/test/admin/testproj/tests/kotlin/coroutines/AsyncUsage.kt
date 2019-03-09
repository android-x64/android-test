package com.test.admin.testproj.tests.kotlin.coroutines

import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture

// async - coroutine builder function, intended for concurrent async operations.
// Usage - when need to launch two or more async operations in parallel

private fun startLongAsyncOperation1(v: Int) = GlobalScope.async {

    // line 3 (v=10); here is another background thread is created -> Thread[DefaultDispatcher-worker-3,5,main]
    // this line may (and may not) be executed before line 2

    // line 4 (v=5);  here is another background thread is created -> Thread[DefaultDispatcher-worker-2,5,main]
    // this line also may (and may not) be executed before line 2

    Thread.sleep(1000)
    "Result $v"

}

private fun startLongAsyncOperation2(v: Int) =
        CompletableFuture.supplyAsync {
            // line 5, 7, 9 - executed fifth, seventh and ninth respectively
            // here is new background thread is created ->Thread[ForkJoinPool.commonPool-worker-1,5,main]
            Log.e("WWW", "WWW supplyAsync v:$v t: ${Thread.currentThread()}")
            Thread.sleep(1000)
            "Result $v"
        }

fun testAsync() {
    //test1()

    test2()
}

private fun test2() {
    // here is main thread (if 'test2()' is called from 'main' thread) -> Thread[main,5,main]

    // line 1 - executed first

    val future: Deferred<String> = GlobalScope.async<String> {
        // line 2 - executed third
        // here is new background thread is created -> Thread[DefaultDispatcher-worker-2,5,main]

        val result = (1..3).map {
            // line 4 (thread -> Thread[DefaultDispatcher-worker-2,5,main]) - executed fourth,
            // line 6 (thread -> Thread[DefaultDispatcher-worker-2,5,main]) - executed sixth,
            // line 8 (thread -> Thread[DefaultDispatcher-worker-4,5,main]) - executed eighth

            startLongAsyncOperation2(it).await()
        }.joinToString("; ")

        // line 10 (thread -> Thread[DefaultDispatcher-worker-3,5,main]) - executed tenth

        result
    }
    // line 3 (Thread[main,5,main]) - executed third

    val r = future.asCompletableFuture().get() // this call blocks main thread (in our case for 3 secs)

    // line 11 (Thread[main,5,main]) - executed eleventh
}

private fun test1() {
    GlobalScope.launch {

        // line 1; here new background thread is created -> Thread[DefaultDispatcher-worker-1,5,main]

        // start and running in parallel
        val request1 = startLongAsyncOperation1(10)
        val request2 = startLongAsyncOperation1(5)

        // if we uncomment next line, "line 3" and "line 4" will be executed before "line 2"
        // Thread.sleep(100)

        // line 2; here is still thread -> Thread[DefaultDispatcher-worker-1,5,main]


        // wait for a result
        val result1 = request1.await()
        // here is another thread -> Thread[DefaultDispatcher-worker-3,5,main]

        val result2 = request2.await()

        // here may be the same thread as previous(Thread[DefaultDispatcher-worker-3,5,main])
        // or maybe another thread (Thread[DefaultDispatcher-worker-2,5,main])
    }
}