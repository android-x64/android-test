package com.test.admin.testproj.tests.stackoverflow.a

class ProductLogic(private val product: Product?) {
    fun shouldShow(): Boolean {
        if (product == null) {
            return false
        }
        val version = product.version
        return !isAtLeastVersionX(version.minor, version.major)
    }

    private fun isAtLeastVersionX(minor: Int, major: Int): Boolean {
        val v = 5
        return v in minor..major
    }
}