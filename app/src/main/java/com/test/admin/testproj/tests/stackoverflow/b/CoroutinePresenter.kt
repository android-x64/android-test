package com.test.admin.testproj.tests.stackoverflow.b

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutinePresenter(private val uiContext: CoroutineContext = Dispatchers.Main) : CoroutineScope {
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = uiContext + job

    fun onCreate() {
        job = Job()
    }

    fun onDestroy() {
        job.cancel()
    }
}