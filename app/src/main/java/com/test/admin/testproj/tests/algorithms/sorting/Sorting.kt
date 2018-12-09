package com.test.admin.testproj.tests.algorithms.sorting

fun insertionSort(arr: Array<Int>) {
    for (i in 1 until arr.size) {
        for (j in 0 until i) {
            if(arr[i] < arr[j]) {
                val temp = arr[j]
                arr[j] = arr[i]
                arr[i] = temp
            }
        }
        arr.forEach{ print("$it ") }
        println()
    }
}