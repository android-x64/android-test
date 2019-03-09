// by default compiled class name will be Utils.Kt
// if we want to change name add next line to the beginning of the file:
@file:JvmName("MyUtils")
package com.test.admin.testproj.tests.kotlin.utils

@JvmOverloads // to call function from Java with default parameter
fun multiply(num1: Int, num2: Int = 1) = num1 * num2

// aliases
typealias CustomerName = String

@Deprecated("Customer is now called AwesomeCustomer",
        replaceWith = ReplaceWith("AwesomeCustomer"))
data class AwesomeCustomer(val name: CustomerName, val email: String)

typealias Customer = AwesomeCustomer


typealias SomeListener1 = (Int) -> Unit