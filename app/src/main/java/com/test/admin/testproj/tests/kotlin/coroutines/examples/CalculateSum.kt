package com.test.admin.testproj.tests.kotlin.coroutines.examples

import android.annotation.TargetApi
import android.os.Build
import com.test.admin.testproj.tests.kotlin.LearnKotlinActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask
import kotlin.system.measureTimeMillis

const val SEQUENTIAL_THRESHOLD = 5000


fun computeSync(array: IntArray, low: Int, high: Int): Long {
    return if (high - low <= SEQUENTIAL_THRESHOLD) {
        (low until high)
                .map { array[it].toLong() }
                .sum()
    } else {
        val mid = low + (high - low) / 2
        val left = computeSync(array, low, mid)
        val right = computeSync(array, mid, high)
        left + right
    }
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
internal class Sum(private var array: IntArray, private var low: Int, private var high: Int)
    : RecursiveTask<Long>() {

    override fun compute(): Long {
        return if (high - low <= SEQUENTIAL_THRESHOLD) {
            (low until high)
                    .map { array[it].toLong() }
                    .sum()
        } else {
            val mid = low + (high - low) / 2
            val left = Sum(array, low, mid)
            val right = Sum(array, mid, high)
            left.fork()
            val rightAns = right.compute()
            val leftAns = left.join()
            leftAns + rightAns
        }
    }

    companion object {
        fun sumArray(array: IntArray): Long {
            return ForkJoinPool.commonPool()(Sum(array, 0, array.size))
        }
    }
}

suspend fun computeUsingCoroutines(array: IntArray, low: Int, high: Int): Long {
    return if (high - low <= SEQUENTIAL_THRESHOLD) {
        (low until high)
                .map { array[it].toLong() }
                .sum()
    } else {
        val mid = low + (high - low) / 2
        val left = GlobalScope.async { computeUsingCoroutines(array, low, mid) }
        val right = GlobalScope.async { computeUsingCoroutines(array, mid, high) }
        left.await() + right.await()
    }
}

fun testComputeSum() {
    var limit = 7_000_000
    val list = mutableListOf<Int>()
    while (limit > 0) {
        list.add(limit--)
    }

    // call 'computeSync' first time just to warm up a processor

    var result = 0L
    var time = measureTimeMillis {
        result = computeSync(list.toIntArray(), 0, list.size)
    }

    // Sync

    result = 0L
    time = measureTimeMillis {
        result = computeSync(list.toIntArray(), 0, list.size)
    }

    LearnKotlinActivity.print("CalculateSum Sync: $result in ${time}ms")

    // Fork/Join Pool

    result = 0L
    time = measureTimeMillis {
        result = Sum.sumArray(list.toIntArray())
    }

    LearnKotlinActivity.print("CalculateSum ForkJoinPool: $result in ${time}ms")

    // Coroutines

    result = 0L
    time = measureTimeMillis {
        result = runBlocking { computeUsingCoroutines(list.toIntArray(), 0, list.size) }
    }

    LearnKotlinActivity.print("CalculateSum Coroutines: $result in ${time}ms")
}

