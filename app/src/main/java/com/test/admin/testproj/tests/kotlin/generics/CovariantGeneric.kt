package com.test.admin.testproj.tests.kotlin.generics

// Covariant - is the ability to change the generic type argument from a class
// to one of its parents, i.e. assign List<String> to List<Any>.
// To indicate covariant generic type parameters use 'out' keyword.
class CovariantGeneric<out T> {
}