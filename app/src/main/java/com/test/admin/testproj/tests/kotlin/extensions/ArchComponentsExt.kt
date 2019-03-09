package com.test.admin.testproj.tests.kotlin.extensions

import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(): T {
    return ViewModelProviders.of(this)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.getViewModel(): T {
    return ViewModelProviders.of(this)[T::class.java]
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}

// we can use it like this:
//                      withViewModel<SomeViewModel> {
//                          observe(notificationsList, ::updateUI)
//                      }
inline fun <reified T : ViewModel> FragmentActivity.withViewModel(body: T.() -> Unit): T {
    val vm = getViewModel<T>()
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.withViewModel(body: T.() -> Unit): T {
    val vm = getViewModel<T>()
    vm.body()
    return vm
}

// we can use it like this:  observe(someLiveDataList, ::updateUI)
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

// we can use it like this:
//                      withViewModel({ SomeViewModel("someData") }) {
//                          observe(someLiveDataList, ::updateUI)
//                      }
inline fun <reified T : ViewModel> FragmentActivity.withViewModel(
        crossinline factory: () -> T,
        body: T.() -> Unit
): T {
    val vm = getViewModel(factory)
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.withViewModel(
        crossinline factory: () -> T,
        body: T.() -> Unit
): T {
    val vm = getViewModel(factory)
    vm.body()
    return vm
}