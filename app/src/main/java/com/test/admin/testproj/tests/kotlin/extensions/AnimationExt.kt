package com.test.admin.testproj.tests.kotlin.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewPropertyAnimator

inline fun ViewPropertyAnimator.onAnimationStart(crossinline continuation: (Animator) -> Unit) {
    setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) {
            continuation(animation)
        }
    })
}

inline fun ViewPropertyAnimator.onAnimationEnd(crossinline continuation: (Animator) -> Unit) {
    setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            continuation(animation)
        }
    })
}

inline fun ViewPropertyAnimator.setListener(
        crossinline animationStart: (Animator) -> Unit = {},
        crossinline animationRepeat: (Animator) -> Unit = {},
        crossinline animationCancel: (Animator) -> Unit = {},
        crossinline animationEnd: (Animator) -> Unit = {}) {

    setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) {
            animationStart(animation)
        }

        override fun onAnimationRepeat(animation: Animator) {
            animationRepeat(animation)
        }

        override fun onAnimationCancel(animation: Animator) {
            animationCancel(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            animationEnd(animation)
        }
    })
}