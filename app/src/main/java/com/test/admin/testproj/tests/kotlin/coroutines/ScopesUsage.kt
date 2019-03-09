package com.test.admin.testproj.tests.kotlin.coroutines

import com.test.admin.testproj.tests.kotlin.LearnKotlinActivity
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

// example of creating CoroutineScope instance:
val customScope = CoroutineScope(Job() + Dispatchers.Main) // CoroutineDispatcher - runs and schedules coroutines


fun testScopes() {
    createCoroutineScope1()

    LocalScopePresenter().doIOOperation()
}

fun createCoroutineScope1() {
    CoroutineScope(Dispatchers.Main).launch {

    }
}

// 'coroutineScope' is used for handling exception. When one of the children (coroutines) fails
// all the other of the children (coroutines) are canceled;
// and if the parent coroutine has been canceled, anything below it (child coroutines) will also be (canceled).
// Using `coroutineScope` we will never have a leak.
suspend fun createCoroutineScope2() = coroutineScope {
    // next coroutines are running in parallel
    val local = async { /*some long running operation*/ } // if this coroutine fails, the next (`remote`) is canceled
    val remote = async { /*some long running operation*/ }

    local.await()
    remote.await()
}

// Whenever we want to launch concurrent things from inside suspending function we should use `coroutineScope` primitive.
// Function won't complete until all the coroutines we launched are complete. It makes sure we never
// leak any of the background tasks
suspend fun createCoroutineScope3() = coroutineScope {
    (0..10).forEach {
        launch { /* do something*/ }
    }
}

// If one of the children fails all other children continue to execute;
// We are responsible to check which child failed and decide whether to cancel other children or not
suspend fun createSupervisorScope2() = supervisorScope {

}


// Examples

suspend fun processUrls(urls: List<String>) = coroutineScope {

    // if at some point Exception is thrown, this Exception goes through the `coroutineScope`,
    // coroutineScope will cancel all child coroutines launched using 'launch' builder
    // and will wait until they all complete.
    for (url in urls) {
        val realUrl = url.trim()

        // launching content downloading in parallel for all urls
        launch {
            val content = downloadContent(realUrl)

        }
    }
}

suspend fun downloadContent(url: String) = withContext(Dispatchers.IO){
    delay(500)
    "Content for url: $url"
}


class LocalScopePresenter : CoroutineScope {
    private var job: Job = Job()

    // To use Dispatchers.Main (CoroutineDispatcher - runs and schedules coroutines) in Android add
    // implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$KOTLIN_COROUTINES_VERSION"
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun detachView() {
        job.cancel()
    }

    fun doIOOperation() = asyncIO(::ioOperation, ::uiOperation)

    private fun ioOperation(): String {
        // long running operation
        LearnKotlinActivity.print("ScopesUsage ioOperation(); t: ${Thread.currentThread()}")
        TimeUnit.SECONDS.sleep(2)
        return "ioOperation RESULT"
    }

    private fun uiOperation(result: String) {
        // do UI stuff
        LearnKotlinActivity.print("ScopesUsage uiOperation(); result: $result ,  t: ${Thread.currentThread()}")
    }
}


