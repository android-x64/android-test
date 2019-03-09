package com.test.admin.testproj.tests.kotlin.coroutines

import com.test.admin.testproj.tests.kotlin.LearnKotlinActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

fun testCoroutineExtensions() {
    //AsyncParallelPresenter().doSixIOOperationsInParallel()
    //LaunchWithContextPresenter().
}

class AsyncParallelPresenter(private val uiContext: CoroutineContext = Dispatchers.Main) : CoroutineScope {
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = uiContext + job      // CoroutineDispatcher - runs and schedules coroutines

    fun detachView() {
        job.cancel()
    }

    fun doIOOperation() {
        // Using extensions we can call IO operation and do stuff in UI in different ways

        // 1.
        asyncIO(::ioOperation1, ::uiOperation1)

        // 2.
        asyncIO(
                { ioOperation1() },
                { uiOperation1(it) }
        )

        // 3.
        asyncIO(::ioOperation1).then(this) { uiOperation1(it) }

    }

    // Also we can do something like this
    fun doIOOperation2() = asyncIO(::ioOperation1, ::uiOperation1)

    fun doIOOperation3() = asyncIO(::uiOperationBefore, ::ioOperation1, ::uiOperation1)


    fun doTwoIOOperationsInParallel() = asyncIO(::ioOperation1, ::ioOperation2, ::uiOperation2)

    fun doSixIOOperationsInParallel() = asyncIO(::ioOperation1, ::ioOperation2,
            ::ioOperation1, ::ioOperation2, ::ioOperation1, ::ioOperation2, ::uiOperation6)

    private fun uiOperationBefore() {
        // do UI stuff before async operation

        LearnKotlinActivity.print("uiOperationBefore();  t: ${Thread.currentThread()}")
    }

    private fun ioOperation1(): String {
        // long running operation 1
        LearnKotlinActivity.print("ioOperation1(); t: ${Thread.currentThread()}")
        TimeUnit.SECONDS.sleep(2)
        return "ioOperation ONE RESULT"
    }

    private fun ioOperation2(): Int {
        // long running operation 2
        LearnKotlinActivity.print("ioOperation2(); t: ${Thread.currentThread()}")
        TimeUnit.SECONDS.sleep(2)
        return 99
    }

    private fun uiOperation1(result: String) {
        // do UI stuff
        LearnKotlinActivity.print("uiOperation1(); result: $result,  t: ${Thread.currentThread()}")
    }

    private fun uiOperation2(result1: String, result2: Int) {
        // do UI stuff
        LearnKotlinActivity.print("uiOperation2(); result1: $result1, result2: $result2,  t: ${Thread.currentThread()}")
    }

    private fun uiOperation6(result1: String, result2: Int, result3: String, result4: Int,
                             result5: String, result6: Int) {
        // do UI stuff
        LearnKotlinActivity.print("uiOperation6(); result1: $result1, result2: $result2,  " +
                "result3: $result3, result4: $result4,  " +
                "result5: $result5, result6: $result6,  t: ${Thread.currentThread()}")
    }
}


class LaunchWithContextPresenter(private val uiContext: CoroutineContext = Dispatchers.Main) : CoroutineScope {
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = uiContext + job      // CoroutineDispatcher - runs and schedules coroutines

    fun detachView() {
        job.cancel()
    }

    fun doIOOperation() {
        // TODO use 'launch' with 'withContext'
    }


    private fun ioOperation1(): String {
        // long running operation 1
        LearnKotlinActivity.print("ioOperation1(); t: ${Thread.currentThread()}")
        TimeUnit.SECONDS.sleep(2)
        return "ioOperation ONE RESULT"
    }


    private fun uiOperation1(result: String) {
        // do UI stuff
        LearnKotlinActivity.print("uiOperation1(); result: $result,  t: ${Thread.currentThread()}")
    }
}

