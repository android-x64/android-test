package com.test.admin.testproj.tests.kotlin.operator_overloading

class OperOverloadingClass(var a: Int) {

    // "plus" by default also overrides "+=" operator creating new instance
    operator fun plus(other: OperOverloadingClass): OperOverloadingClass {
        return OperOverloadingClass(a + other.a)
    }

    // Comparison Operators Overloading

    //"==" and "!=" operators are translated into a call of "equals()" method

    // Overloading "<", ">", "<=" and ">=" operators
    operator fun compareTo(other: OperOverloadingClass): Int {
        return compareValuesBy(this, other, OperOverloadingClass::a)
    }

    // Compound Assignment Operators (+=, -=, /=, *=)
    // If possible we need to avoid overriding "plus" and "plusAssign" at the same time
    operator fun plusAssign(other: OperOverloadingClass): Unit {
        this.a += other.a
    }

    // Unary Operators
    operator fun inc(): OperOverloadingClass {
        a++
        return this
    }

    operator fun unaryMinus(): OperOverloadingClass {
        return OperOverloadingClass(-a)
    }

}