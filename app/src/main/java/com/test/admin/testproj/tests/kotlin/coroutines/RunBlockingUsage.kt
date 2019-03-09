package com.test.admin.testproj.tests.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun startRunBlocking() = runBlocking {
    // here is main thread -> Thread[main,5,main]

    // line 1 - executed first

    val jobs = List(10) {

        // line 2 - executed second

        GlobalScope.launch {
            // (line 4) until (4+listSize) - may be executed after or before line 3;
            // launching new coroutines
            // here is new background threads are created (threads can be reused) -> e.g. Thread[DefaultDispatcher-worker-1,5,main]
            delay(1000L) // doesn't block the thread; it makes coroutine sleep for 1 sec not blocking the thread
            print(".")
        }
    }

    // if we uncomment next line, "line 4" and others will be executed before "line 3"
    // Thread.sleep(100)

    // line 3 - may be executed before or after "line 4"; here is main thread -> Thread[main,5,main]

    // we wait until all coroutines are finished (about 1 sec in our case)
    jobs.forEach {
        it.join() // doesn't block the thread;
    }

    // last line - executes after 1 sec; called after all coroutines are executed;
    // here is main thread -> Thread[main,5,main]

}