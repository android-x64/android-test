package com.test.admin.testproj.tests.kotlin.classes

class Outer {
    private val bar = 1

    // In Kotlin nested classes don't have access to the outer class instance
    // It is the same as nested static class in Java
    // Example of usage: Outer.Nested().foo()
    class Nested {
        fun foo() = 2
    }

    // Inner classes have access to the outer class instance
    // Example of usage: Outer().Inner().foo()
    inner class Inner {
        fun foo() = bar // also we can refer to the outer class property as: "this@Outer.bar"
    }
}