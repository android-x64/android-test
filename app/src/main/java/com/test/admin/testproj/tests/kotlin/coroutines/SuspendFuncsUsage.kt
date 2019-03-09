package com.test.admin.testproj.tests.kotlin.coroutines

import com.test.admin.testproj.tests.kotlin.LearnKotlinActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun testSuspendFunctions() {
    SuspendFunctionsPresenter().doIOOperationUsingLaunchBuilder()
}


class SuspendFunctionsPresenter : CoroutineScope {
    private var job: Job = Job()

    // To use Dispatchers.Main (CoroutineDispatcher - runs and schedules coroutines) in Android add
    // implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$KOTLIN_COROUTINES_VERSION"
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    fun detachView() {
        job.cancel()
    }

    fun doIOOperationUsingLaunchBuilder() = launch {
        // runs in the Main Thread

        // ioOperation() isn't blocking the Main Thread, bcz it is marked as 'suspend'
        // and using withContext(Dispatchers.IO)
        val result = ioOperation()
        uiOperation(result)
    }

    fun doIOOperationUsingLaunchBuilderWithAsyncInside(credentials: String) = launch {
        // runs in the Main Thread
        // lines 1 and 2 run in parallel

        val userID = login(credentials)
        val data = loadUserData(userID)
        val image = async { loadImage(data) } // line 1

        showData(data) // line 2
        showImage(image.await())
    }

    private suspend fun login(credentials: String): String {
        delay(2000) // simulate login
        return "User ID for credential: $credentials"
    }

    private suspend fun loadUserData(userID: String): String {
        delay(2000) // simulate loading of user data
        return "User Data for user with id: $userID"
    }

    private suspend fun loadImage(data: String): String {
        delay(2000) // simulate loading of image
        return "Image loaded for data: $data"
    }

    private fun showData(data: String) {
        // simulate showing of data
        LearnKotlinActivity.print("Show data: $data")
    }

    private fun showImage(image: String) {
        // simulate showing of image
        LearnKotlinActivity.print("Show image: $image")
    }

    // without withContext(Dispatchers.IO) this function will block the Main Thread
    private suspend fun ioOperation(): String = withContext(Dispatchers.IO) {
        // long running operation
        LearnKotlinActivity.print("SuspendFuncsUsage ioOperation(); t: ${Thread.currentThread()}")
        delay(3000)

        var sum = 0.0
        for (i in 0..100000000) {
            sum += i
        }

        "ioOperation RESULT sum: $sum"
    }

    private fun uiOperation(result: String) {
        // do UI stuff
        LearnKotlinActivity.print("SuspendFuncsUsage uiOperation(); result: $result ,  t: ${Thread.currentThread()}")
    }
}
