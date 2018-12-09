package com.test.admin.testproj.tests.kotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.test.admin.testproj.R
import com.test.admin.testproj.tests.kotlin.classes.*
import com.test.admin.testproj.tests.kotlin.coroutines.examples.testComputeSum
import com.test.admin.testproj.tests.kotlin.coroutines.postItem
import com.test.admin.testproj.tests.kotlin.coroutines.startRunBlocking
import com.test.admin.testproj.tests.kotlin.coroutines.testAsync
import com.test.admin.testproj.tests.kotlin.coroutines.testScopes
import com.test.admin.testproj.tests.kotlin.generics.GenericRepository
import com.test.admin.testproj.tests.kotlin.generics.Model
import com.test.admin.testproj.tests.kotlin.interfaces.Bird
import com.test.admin.testproj.tests.kotlin.operator_overloading.OperOverloadingClass
import kotlinx.android.synthetic.main.activity_learn_kotlin.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.concurrent.withLock
import kotlin.math.abs

class LearnKotlinActivity : AppCompatActivity() {

    //region Late Initialization
    lateinit var title: String
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_kotlin)

        // check if "lateinit" property was initialized
        // "this::title" - property reference
        if (this::title.isInitialized) {

        }

        title = "Learn Kotlin"
        textView.text = title


        //variables()
        //strings()
        //nullSafety()
        //arrays()
        //ranges()
        //conditionals()
        //looping()
        //testFunctions()
        //extensionsTest()
        //lambdaWithReceivers()
        //invokingInstances()
        //testCollections()
        //sequences()
        //exceptionHandling()
        //classesTest()
        //enumClasses()
        //objectDeclaration()
        //companionObject()
        //constants()
        //interfacesTest()
        //typeCasting()
        //tuplesAndDestructuringDeclaration()
        //generics()
        //typeAlias()
        //functionAlias()
        //bitwiseFunctions()
        //standardLibraryFunctions()
        coroutines()
        //testCoroutineExamples()
    }


    //region Visibility modifiers (4)

    // public - Default, anywhere accessible

    /* Top Level Declarations
             private - Available inside file containing declaration
             internal - Anywhere in the same module (Gradle, Maven, IntelliJ, ... module)  */

    /* Classes
             private - Only available to class members
             protected - Same as private and subclasses
             internal - Any client inside the module (Gradle, Maven, IntelliJ, ... module)  */

    //endregion

    //region Variables
    private fun variables() {

        //region Mutable, Immutable types
        val name = "Serg"   // immutable; Kotlin uses Type inference - "name" is String
        var age = 17        // mutable;                                "age" is Integer
        //endregion

        //region Defining variables
        var bigInt: Int = Int.MAX_VALUE // defined type
        var smallInt: Int = Int.MIN_VALUE // defined type
        print("Biggest Int: " + bigInt)
        print("Smallest Int: $smallInt") // string interpolation
        //endregion

        //region Loss of precision in floating numbers (Precision falls apart after 15 digits)
        val dblNum1: Double = 1.1111111111111111
        val dblNum2: Double = 1.1111111111111111
        print("Precision has been lost, Sum = " + (dblNum1 + dblNum2))
        //endregion

        //region Checking type of variable ("is" equals "instanceOf" in Java)
        if (dblNum1 is Double) {
            print("dblNum1 is Double")
        }
        //endregion

        //region Character and String interpolation
        var letterGrade: Char = 'A'
        print("letterGrade is Char: ${letterGrade is Char}") // string interpolation
        //endregion

        //region Casting
        print("3.14 to Int: " + (3.14.toInt()))
        //endregion

        //region Lazy variable
        // Initializing immutable variable in a lazy way.
        // It will be initialized when "lazyInit" variable is called
        val lazyInit: Int by lazy { 10 }
        //endregion
    }
    //endregion

    //region Strings
    private fun strings() {
        val name = "Serg"
        val longString = """This is a
                                        long string"""

        var fName: String = "Doug"
        var sName: String = "Smith"
        fName = "Sally"
        val fullName = "$fName $sName" // Concatenating
        print("Full name length: ${fullName.length}")

        // Comparing
        var str1 = "A random string"
        var str2 = "a random string"
        print("Strings equal: ${str1.equals(str2)}")
        print("Strings equal: ${str1 == str2}")
        print("Compare: ${str1.compareTo(str2)}")

        // Index
        print("Second Char of str1: ${str1[2]}")
        print("Index 2 - 7: ${str1.subSequence(2, 8)}")

        // Containing
        print("str1 contains random: ${str1.contains("random")}")

        // Sorting chars
        val str3 = "random"
        val sorted = str3.toCharArray().sortedArray()

        str3.substring(0..1)

        // Build String
        val s = buildString {
            appendln("Alphabet:")
            for (c in 'a'..'z') {
                append(c)
            }
        }


    }
    //endregion

    //region Null Safety
    private fun nullSafety() {
        //var nullValue: String = null // we can't assign null by default
        var nullValue: String? = null  // to allow nulls we need to put Question Mark("?")

        var nullValue1 = funThatCanReturnNull()
        if (nullValue1 != null) { // smart cast
            print("nullValue1.length: ${nullValue1.length}")
        }

        var length = nullValue1!!.length // Force operator("!!") - to force a null assignment

        var nullValue2 = funThatCanReturnNull() ?: "Default value" // Elvis operator("?:") - to assign a default value if the value could be null

        // With the Elvis operator("?:") we can check "nullValue1" and return method value for null at once.
        var nullValue3 = nullValue1?.singleOrNull() ?: "Default value"


        // Safe Cast
        val someInt: Int? = nullValue1 as? Int // return null if the attempt was not successful
    }

    private fun funThatCanReturnNull(): String? {
        return null
    }
    //endregion

    //region Arrays
    private fun arrays() {

        val myArray = arrayOf(1, 1.23, "Dog") //
        print(myArray[2]) // access
        myArray[0] = 2 // changing value
        print("Size of array: ${myArray.size}")
        print("Dog in array: ${myArray.contains("Dog")}")

        val partArray = myArray.copyOfRange(1, myArray.size)
        print("partArray: ${partArray.last()}")
        print("Ingex of Dog: ${partArray.indexOf("Dog")}")

        // Collections
        val squaresArray = Array(5) { indx -> indx * indx} // array of squares
        print("squaresArray[2]: ${squaresArray[4]}")

        val specificTypeArray: Array<Int> = arrayOf(1, 2, 4)

        val zeros = intArrayOf(0, 0, 0, 0)

        val squares = IntArray(5) { (it + 1) * (it + 1) }

    }
    //endregion

    //region Ranges
    private fun ranges() {
        val oneTo10 = 1..10
        val oneTo20 = 1.rangeTo(20)
        val alpha = "A".."Z"
        val isGPresent = "G" in alpha
        print("R in alpha: ${"R" in alpha}")
        print("alpha contains R: ${alpha.contains("R")}")

        val fiveTo1 = 5 downTo 1 // From 5 to 1
        val tenTo1 = 10.downTo(1) // From 10 to 1
        val twoTo20 = 2.rangeTo(20)
        val fiveToWithStep = 5 downTo 1 step 2

        val range3 = oneTo10.step(3)// steps (adding 3 each time)
        for (x in range3) {
            print("range3: $x")
        }
        //reverse the range
        for (x in range3.reversed()) {
            print("Reversed range3: $x")
        }
        //until
        for (i in 1 until 10) { // "i" in [1,10), 10 is excluded
            print("Until i: $i")
        }
    }
    //endregion

    //region Conditionals
    private fun conditionals() {
        // the same as in Java: >, <, <=, >=, !=, &&, ||

        var age = 8
        if (age < 5) {
            print("Go to Preschool")
        } else if (age == 5) {
            print("Go to Kindergarten")
        } else if (age > 5 && age <= 17) {
            val grade = age - 5
            print("Go to Grade $grade")
        } else {
            print("Go to College")
        }

        // "if" as an expression
        var a = 2
        var b = 5
        val max = if (a > b) a else b
        print("Max value: $max")

        // "when" is like "switch" in Java
        when(age) {
            0, 1, 2, 3, 4 -> print("Go to Preschool")

            5 -> print("Go to Kindergarten")

            in 6..17 -> {
                val grade = age - 5
                print("Go to Grade $grade")
            }

            is Int -> print("It is Int")

            else -> print("Go to College")
        }

        // "when" with no argument
        when {
            a < 5 -> print("Go to Preschool")
            a == 5 -> print("Go to Kindergarten")
            a in 6..17 -> {
                val grade = age - 5
                print("Go to Grade $grade")
            }
            else -> print("Go to College")
        }

        // "when" as an expression
        var x = 1
        val result = when(x) {
            1 -> "x is 1"
            !in 10..20 -> "x not in range 10..20"
            else -> "x is unknown"
        }
        print("Result: $result")
    }
    //endregion

    //region Looping
    private fun looping() {

        for (x in 1..10) {
            if (x % 2 == 0) {
                continue
            }
            print("Odd: $x")
            if (x == 8) {
                break
            }
        }

        // labeled for-loop
        outer@
        for (x in 1..4) {
            for (y in 1..3) {
                if (x == 2 && y == 2) {
                    break@outer        // terminates outer loop
                }
                if (x == 3 && y == 3) {
                    continue@outer      // continues from outer loop
                }
            }
        }

        val rand = Random() // we can use Java classes in Kotlin
        val magicNumber = rand.nextInt(50) + 1 // from 1 to 50
        var guess = 0
        while (magicNumber != guess) {
            guess += 1
        }
        print("Magic Number was $guess")

        var i: Int = 0
        do {
            // some operations
            i++
            val y = funThatCanReturnNull()
        } while (i < 10 && y == null)

        var array1: Array<Int> = arrayOf(3, 6, 9)

        for (i in (array1.size - 1) downTo 0) {
            print("DownTo Array1: ${array1[i]}")
        }

        // iterate for the indices
        for (i in array1.indices) {
            print("Array1: ${array1[i]}")
        }

        for ((index, value) in array1.withIndex()) {
            print("index: $index  value: $value")
        }
    }
    //endregion

    //region Functions and Lambdas

    // function as expression (we can omit return type)
    private fun add(num1: Int, num2: Int): Int = num1 + num2

    private fun max(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

    private fun maxWithPrint(num1: Int, num2: Int)
            =   if (num1 > num2) {
                    print("num1 is bigger")
                    num1
                } else {
                    print("num2 is bigger")
                    num2
                }

    // no need return type if it is a single-line function
    // num2 has default value 0
    private fun subtract(num1: Int, num2: Int = 0) = num1 - num2 // default value of the 2nd parameter is 0

    // function that returns nothing (Unit instead of Void in Java), but we can omit ": Unit"
    private fun sayHello(name: String): Unit = print("Hello $name!")

    // function that returns two values
    private fun nextTwo(num: Int): Pair<Int, Int> {
        return Pair(num + 1, num + 2)
    }

    // function with variable number of parameters
    private fun sum(vararg nums: Int): Int {
        // passing "vararg" to another function
        // if we already have an array and want to pass its contents to the function,
        // we use the spread operator (prefix the array with *):
        calc(*nums)
        // or
        val a = IntArray(3) { it } // initialize array with indexes (a[0]=0, a[1]=1, ...)
        val result = calc(-1, 0, *a, 4)


        var sum = 0

        // we can use "for" loop
        /*for (n in nums) {
            sum += n
        }*/

        // or we can use forEach function
        nums.forEach { sum += it } // we can use "it" to access an element in lambda expression
        //nums.forEach { n -> sum += n } // or we can use parameter in lambda expression

        return sum
    }

    private fun calc(vararg nums: Int): Int{
        var sum = 0
        nums.forEach { sum += it } // we can use "it" to access an element
        return sum
    }

    // TailRec functions. "tailrec" is used to avoid StackOverflowError exception
    // calculate factorial with tail recursion without affecting the Stack memory
    private fun factorial(x: Int): BigInteger {
        tailrec fun factTail(y: BigInteger, z: BigInteger): BigInteger {
            if (y == 0.toBigInteger()) {
                return z
            }
            return (factTail(y - 1.toBigInteger(), y * z))
        }
        return factTail(x.toBigInteger(), 1.toBigInteger())
    }

    // get Fibonacci number at specified position using with tail recursion without affecting the Stack memory
    private tailrec fun getFibonacciNumber(n: Int, a: BigInteger, b: BigInteger): BigInteger {
        return if (n == 0) b else getFibonacciNumber(n - 1, a + b, a)
    }

    // higher-order (level) functions - it is a function that either accepts or returns another function
    private fun higherOrderFunctionExample1() {
        val numList = 1..20
        val evenList = numList.filter { it % 2 == 0 } // filter - higher order function
        evenList.forEach { print("evenList: $it") }
    }

    private fun higherOrderFunctionExample2() {
        val multiply3Func = makeMathFunc(3)
        print("5 * 3 = ${multiply3Func(5)}")
    }

    private fun higherOrderFunctionExample3() {
        val multiply2Func = { num1: Int -> num1 * 2 }
        val numList = arrayOf(1, 2, 5, 6)
        mathOnList(numList, multiply2Func)

        // use "::" to reference a function by name, the same as in Java(this::multiply3Func)
        mathOnList(numList, ::multiply3Func)
    }

    private fun higherOrderFunctionExample4NullableLambda() {
        var f: (() -> Int)? = null
        // invoking nullable lambda
        // 1.check for null
        if (f != null) {
            f()
        }
        // or 2. Use safe access syntax
        f?.invoke()
    }

    private fun multiply3Func(num: Int) = num * 3

    // higher order function - returns another function
    private fun makeMathFunc(num1: Int): (Int) -> Int = { num2 -> num1 * num2 }

    // higher order function - accepts another function
    private fun mathOnList(numList: Array<Int>, myFunc: (num: Int) -> Int) {
        for (item in numList) {
            print("mathOnList: ${myFunc(item)}")
        }
    }

    // Function with Interface as Parameter
    private fun sqrt(num: Int, listener: SomeListener) {
        val result = num * num
        listener.execute(result)
    }

    // Function with Lambda as Parameter
    private fun sqrt(num: Int, listener: (Int) -> Unit) {
        val result = num * num
        listener(result)
    }

    private fun operation(num: Int, op: (Int) -> Int) {
        op(num)
    }

    //In Kotlin SAM (SingleAbstractMethod) interfaces do not allow you to use Kotlin closures.
    private fun createSomeListener(): SomeListener {
        // Anonymous inner class
        return object: SomeListener {
            override fun execute(sqrtResult: Int) {

            }
        }
    }

    // Typealiases to the rescue
    private fun createSomeListener1(): SomeListener1 {
        return { print("createSomeListener1(), it: $it") }
    }

    // Method returns the instance of Functional Java interface, we can't return a lambda directly,
    // we need to wrap it into a SAM(single abstract method) constructor
    private fun createRunnable(): Runnable {
        return Runnable {
            print("Runnable run()")
        }
    }


    private interface SomeListener {
        fun execute(sqrtResult: Int)
    }

    // Use "Nothing" to mark the function that never returns.
    // "Nothing" only makes sense to use as a function return type or as type argument.
    // Example: a function that has an infinite loop or throws an exception.

    // Function that never returns.
    fun throwingException(): Nothing {
        throw Exception("Function never returns")
    }

    // Inline functions. Similar to C language
    // When using 'inline' keyword the body of the high-order function is substituted directly
    // instead of function invocation. This is the bytecode optimization technique.
    // Can improve performance only with functions that take lambdas as parameters.

    private inline fun inlineFun(op: () -> Unit) {
        print("Inline before op()")
        op()
        print("Inline after op()")
    }

    // 'noinline' keyword says "Don't inline this lambda". In this case it doesn't make much sense
    // bcz we get a warning.
    private inline fun inlineFun2(noinline op: () -> Unit) {
        print("Noinline before op()")
        op()
        print("Noinline after op()")
    }

    // 'noinline' keyword can be used in scenarios where we have multiple lambdas
    private inline fun inlineFun3(noinline op1: () -> Unit, op2: () -> Unit) {
        print("Noinline before op1()")
        // Inlinable lambdas(op2) can only be called inside the inline functions or passed
        // as inlinable arguments, but noinline ones can be manipulated in any way we like:
        // stored in fields, passed around etc.
        val o1 = op1
        o1()
        print("Noinline after op1()")
        // but we can't save Inlinable lambda to a variable:
        // val o2 = op2
        op2()
    }

    // 'crossinline' says, that we are not allowed non-local returns.
    // Some inline functions may call the lambdas passed to them as parameters not directly
    // from the function body, but from another execution context, such as a local object or
    // a nested function. In such cases, non-local control flow is also not allowed in the lambdas.
    // To indicate that, the lambda parameter needs to be marked with the 'crossinline' modifier
    private inline fun inlineFun4(crossinline op1: () -> Unit, op2: () -> Unit) {
        print("Crossinline before op1()")
        val f = Runnable { op1() }

        print("Crossinline after op1()")
        op2()
    }

    private fun testFunctions() {
        print("5 + 6 = ${add(5, 6)}")
        print("3 - 0 = ${subtract(3)}")
        print("8 - 10 = ${subtract(num2 = 10, num1 = 8)}") // named parameters, useful in terms of functions with a big number of parameters and with default values
        sayHello("John")

        val (two, three) = nextTwo(1)
        print("1, $two, $three")

        print("sum = ${sum(50, 20, 30)}")

        val multiply = {num1: Int, num2: Int -> {num1 * num2}}// define function literals
        print("multiply = ${multiply(5, 6)}")

        print("factorial 30 = ${factorial(30)}")

        // without "tailrec" the function will throw StackOverflowError exception
        print("FibonacciNumber 10000: ${getFibonacciNumber(10_000,
                BigInteger("1"), BigInteger("0"))}")

        // Anonymous function; in those functions we can have multiple return points
        // (in lambdas we only have one return point)
        operation(10, fun(x): Int {
            print("Anonymous function, x: $x")
            if (x < 10) {
                return x * x
            } else {
                return x + x
            }
        })

        // Couple ways of using interfaces as parameter
        // 1. Using "object" keyword and implement interface, it is Anonymous inner class(object)
        sqrt(5, object : SomeListener {
            override fun execute(sqrtResult: Int) {
                print("1. sqrt calculated: $sqrtResult")
            }
        })
        // 2. Using lambda expression(function)
        val someLambda: (Int) -> Unit = { n -> print("lambda n: $n") }
        val sumLambda = { sqrtResult: Int -> print("1. sqrt: $sqrtResult")}
        // passing the lambda as variable
        sqrt(5, sumLambda)
        // passing the lambda directly to the function
        sqrt(6, { sqrtResult: Int -> print("2. sqrt: $sqrtResult") })
        // passing the lambda as Closure
        sqrt(7) { sqrtResult -> print("3. sqrt: $sqrtResult") }

        // we can modify outside variables inside Lambda expressions and Closures
        var result = 0
        sqrt(8) { sqrtResult -> result = sqrtResult }
        print("4. sqrt: $result")

        // "it" - Implicit name of Single parameter
        sqrt(9) { print("5. sqrt: $it") }

        // 3. Local return; Returning from Lambda using labels
        sqrt(10) label@{
            if(it == 100) {
                print("6. Returning from Lambda")
                return@label
            }
            print("6. Returning from Lambda sqrt: $it")
        }

        val numbers = 1..5
        numbers.forEach {
            if (it % 5 == 0) {
                return@forEach
            }
        }


        // Reference to function
        val refToF = ::multiply3Func
        refToF(5)


        higherOrderFunctionExample1()
        higherOrderFunctionExample2()
        higherOrderFunctionExample3()
        higherOrderFunctionExample4NullableLambda()
    }

    //endregion

    //region Extensions

    // method that extends class String, adding method "encode" (caesar encryption).
    // using "this" we refer to the actual instance.
    private fun String.encode(shift: Int) =
            String(this.map { it + shift }.toCharArray())

    // Extension property - can't have any 'backing' field and initialization (init) section
    private val String.lastChar: Char
        get() = get(length - 1)

    // Infix function (must operate on two parameters).
    // All Infix functions are extension functions BUT
    // not all extension functions are Infix. Infix functions just have one parameter
    private infix fun Int.greaterThan(other: Int) // Infix and Extension function
            = this > other

    private fun extensionsTest() {
        print("Extended String, encode: ${"Hello".encode(10)}")
        print("Extension Property, encode: ${"Hello".lastChar}")

        // usage of Infix function
        var a = 8
        var b = 80
        // a - is a parameter that the function is invoked on
        // b - explicit parameter to the function
        // a.greaterThan(b)
        print("Infix function, a greater than b: ${a greaterThan b}")
    }

    //endregion

    //region Lambda Extension (or Lambda With Receiver) and Invoking Instances

    // allows to create very fluent expressive DSL

    class Status(var code: Int, var description: String)
    class Request(val method: String, val query: String, val contentType: String)
    class Response(var contents: String, var status: Status) {

        fun status(status: Status.() -> Unit) {}
    }

    class ResponseImproved(var contents: String, var status: Status) {

        // to invoke instance
        operator fun invoke(statusLambda: Status.() -> Unit) {
            print("InvokingInstances, ResponseImproved")
            statusLambda(this.status) // or we can call this.status.statusLambda()

        }
    }

    class RouteHandler(val request: Request, val response: Response) {

        fun response(response: Response.() -> Unit) {}
    }

    class RouteHandlerImproved(val request: Request, val response: ResponseImproved) {}

    class InvokingInstanceClass {
        operator fun invoke() {
        }
    }

    // function takes lambda extension function on class RouteHandler as the second parameter
    private fun routeHandler(path: String, f: RouteHandler.() -> Unit) {
        f(RouteHandler(Request("POST", "r=56", "json"),
                Response("", Status(200, ""))))
    }

    private fun improvedRouteHandler(path: String, f: RouteHandlerImproved.() -> Unit) {
        f(RouteHandlerImproved(Request("POST", "q=49", "json"),
                ResponseImproved("", Status(200, ""))))
    }


    private fun lambdaWithReceivers() {

        routeHandler("/index.html") {
            // we get access to all properties in RouteHandler, bcz RouteHandler became a receiver
            if(request.query != "") {
                // process the query
            }
            print("LambdaWithReceivers, request.query: ${request.query}")
            response {
                status {
                    code = 404
                    description = "Not Found"
                }
            }


            //response.status.code = 404
            //response.contents = "Not Found"
        }

        // Store in a variable and call
        val isOdd: Int.() -> Boolean = { this % 2 == 1 }
        1.isOdd() // calling as extension function

    }

    private fun invokingInstances() {

        improvedRouteHandler("/index.html") {
            // we get access to all properties in RouteHandler, bcz RouteHandler became a receiver
            if(request.query != "") {
                // process the query
            }
            print("InvokingInstances, request.query: ${request.query}")
            // invoking method "invoke" of ResponseImproved class
            response {
                print("InvokingInstances, setup Status")
                code = 404
                description = "Not Found"
            }
        }


        val c = InvokingInstanceClass()
        c() // we can invoke some functionality on the instance, bcz we overloaded "invoke()" operator
    }

    //endregion and Invoking

    //region Collections

    // All read-only collection interfaces are covariant. Covariant - is the ability to change
    // the generic type argument from a class to one of its parents,
    // i.e. assign List<String> to List<Any>

    @SuppressLint("NewApi")
    private fun collections() {

        // Arrays; also can be created using  arrayOf, arrayOfNulls and emptyArray functions
        var myArray = Array<Int>(5) { 0 }   // Mutable, Fixed Size, all elements initialized with 0
        var myArray1 = arrayOf(10, 20, 30)       // Mutable, Fixed Size
        var myArray2 = arrayOfNulls<Int>(5) // Mutable, Fixed Size, all elements initialized with null
        var myArray3 = emptyArray<String>()      // Mutable, Fixed Size

        myArray[3] = 50
        for (num in myArray) {
            // looping
        }
        for ((index, value) in myArray.withIndex()) {
            // looping
        }

        myArray.reduce { acc, i -> i }

        myArray.fold(100) { acc, i -> i }

        // Lists
        val immutableList: List<Int> = listOf(1, 2, 3, 4, 5, 2) // Immutable, Fixed Size
        val mutableList1 = arrayListOf<String>() // Mutable, No Fixed Size
        var mutableList2 = ArrayList<Double>()   // Mutable, No Fixed Size
        var mutableList3 = arrayListOf(*myArray1) // Mutable, No Fixed Size
        val mutableList: MutableList<Int> = mutableListOf(5, 4, 3, 2, 1) // Mutable, No Fixed Size

        mutableList.add(0)
        mutableList += 6
        print("mutableList 1st item: ${mutableList.first()}")
        print("mutableList 2nd item: ${mutableList[1]}")

        mutableList.forEach { print("mutableList Before clearing SubList: $it") }

        val subList = mutableList.subList(2, mutableList.size)
        subList.forEach { print("SubList: $it") }
        subList.clear() // clearing in subList is reflected in mutableList (in mutableList items are also removed)
        mutableList.forEach { print("mutableList After clearing SubList: $it") }

        immutableList.all { it > 3 } // are all items greater than 3
        immutableList.any { it > 3 } // is there at least one item greater than 3
        immutableList.none { it == 0 }
        immutableList.count { it > 3 }
        immutableList.find { it > 3 }
        immutableList.first { it == 2 }
        immutableList.firstOrNull { it == 1 }
        immutableList.maxBy { it + 2 }
        immutableList.sumBy { it - 1 }
        mutableList2.sumByDouble { abs(it - 4) }

        // divides collection into 2 collections
        immutableList.partition { it < 10 }

        // divide a collection to several groups by given key
        mutableList1.groupBy { it.length } // key - the lengths of strings
        // associate key to one element from collection (not to list like 'groupBy' function)
        // prefer to use when the key is unique
        mutableList1.associateBy { it.length }

        // organize couple collections
        mutableList zip immutableList

        val containsThree = 3 in immutableList

        print("groupBy: ${immutableList.joinToString(";", "(", ")")}")


        mutableList1.add("abc")
        mutableList1.add("defg")

        // flatMap
        // - transforms each element to a collection according to the lambda function;
        // - combines (or flattens) several lists into one and returns it
        print("flatMap: ${mutableList1.flatMap { it.toList() }}")

        // grouping elements
        print("groupBy: ${mutableList1.groupBy { it.length }}")

        // joining collections
        mutableList1.zip(immutableList)
                .forEach {
                    val (str, num) = it
                    print("zip: $str - $num")
                }

        // Converting Collections

        immutableList.toTypedArray()
        immutableList.toIntArray()
        immutableList.toSet()
        immutableList.toMutableList()
        immutableList.toMutableSet()

        // Mutable to immutable list
        val imutList = immutableList.toList()

        // Deconstructing
        val (one, two, three, four, five) = immutableList

        mutableList3.removeAll { it == 0 }
        mutableList3.count { it > 0 }

        // Maps
        val map1 = mapOf<Int, String>()              // Immutable, Fixed Size
        val map2 = mutableMapOf(1 to "Cat", 2 to 33) // Mutable, No Fixed Size
        val map3 = hashMapOf<Int, String>()          // Mutable, No Fixed Size, Not Ordered
        val map4 = HashMap<Int, String>()            // Mutable, No Fixed Size, Not Ordered
        val map5 = linkedMapOf<Int, String>(1 to "One")        // Mutable, No Fixed Size, Ordered
        val map6 = sortedMapOf<Int, String>()        // Mutable, No Fixed Size, TreeMap based on a red-black tree implementation, by default sorted based on keys ordering
        val map = mutableMapOf<Int, Any?>()

        map[2] = "Dog"
        map[5] = 99
        map.put(3, "Bear")
        map += (6 to "Fish")
        print("Map Size: ${map.size}")

        for ((k, v) in map) {
            print("key: $k  value: $v")
        }

        for (key in map.keys) {
            // looping through keys
            print("key: $key  value: ${map[key]}")
        }

        val sortedMap: SortedMap<Int, String> = map1.toSortedMap(compareByDescending { it })

        map4.forEach {
            return@forEach
        }

        map4.count {
            it.value.length > 1
        }

        map4.maxBy {
            it.value
        }
        map4.values.max()

        // Convenient function to store default value if the value is not present for specified key
        var stringValue = map4.getOrPut(3) { "Default" }
        stringValue += " value"

        // We can use the destructuring declarations syntax for lambda parameters
        map.mapValues { (key, value) -> "$value!" }

        // We can specify the type for the whole destructured parameter
        // or for a specific component separately
        map.mapValues { (_, value): Map.Entry<Int, Any?> -> "$value!" }
        map.mapValues { (_, value: Any?) -> "$value!" }

        // Sort map by value
        val result = map5.toList().sortedBy { (_, value) -> value}.toMap()
        val key = result.keys.last()
        val value = result.values.first()


        // Sets
        val set1: Set<String> = setOf("dog", "cat", "bird") // Immutable, Fixed Size, the Sequence is ordered
        val set2: HashSet<String> = hashSetOf<String>("dog", "cat", "bird") // Mutable, No Fixed Size, the Sequence is not ordered
        val set3: MutableSet<String> = mutableSetOf<String>("dog", "cat", "bird") // Mutable, No Fixed Size, the Sequence is ordered
        val set4: TreeSet<String> = sortedSetOf<String>("dog", "cat", "bird") // Mutable, No Fixed Size, the Sequence is ordered
        val set5: LinkedHashSet<String> = linkedSetOf<String>("dog", "cat", "bird") // Mutable, No Fixed Size, the Sequence is ordered

        // add new element to the set, but don't alter the collection
        // by generating a new Immutable collection
        val set11 = set1.plus("fish")
        print("Collections  set1: $set1")


        // Sequences
        // A sequence returns values through an iterator.
        // Sequences are great for scenarios when the size of the collection
        // is not known in advance (i.e. reading a table from DB).

        // converting to Sequence
        val seq = myArray.asSequence()

        // some operations on Sequence
        myArray.asSequence()
                .map { it.toLong() }
                .filter { it > 0 }
                .toList()


    }

    private fun collectionOperators() {
        val numList = 1..5
//        val listSum = numList.reduce { acc, i -> acc + i }
        val listSum = numList.reduce { acc: Int, i: Int ->
            print("acc = $acc  i = $i")
            acc + i
        }
        print("Reduce Sum: $listSum")

        val listSum2 = numList.fold(5) { acc, i -> acc + i }
        print("Fold Sum: $listSum2")

        print("Are there Evens in the list: ${numList.any {  it % 2 == 0 }}")

        print("Are there all Evens in the list: ${numList.all {  it % 2 == 0 }}")

        val biggerThan3 = numList.filter { it > 3 } // 'filter' creates new intermediate collection
        biggerThan3.forEach { print("biggerThan3: $it") }

        val times7 = numList.map { it * 7 } // 'map' creates new intermediate collection
        times7.forEach { print("times7: $it") }


        // Predicates - condition functions, that return a boolean value
        val numbers = listOf(5, 78, 40, 2, 10, 4, 100, 9)
        val smallNumbers = numbers.filter { it < 10 }
        smallNumbers.forEach { print("Predicates, smallNumbers: $it") }

        val areAllNumsSmallerThanFive = smallNumbers.all { it < 5 }
        val areThereNumsSmallerThanFive = smallNumbers.any { it < 5 }
        val numsSmallerThanFiveCount = smallNumbers.count { it < 5 }
        val firstNumberSmallerThanFive: Int? = smallNumbers.find { it < 5 }

        // Transformation functions
        val smallNumberSquares = smallNumbers.map { it * it }
        smallNumberSquares.forEach { print("Predicates, smallNumberSquares: $it") }

        val strings = listOf("go", "to", "the", "garden")


        var arr = arrayListOf<ArrayList<Int>>()
        arr.maxBy {
            it.size
        }?.size
    }


    private fun testCollections() {
        collections()
        collectionOperators()
    }
    //endregion

    //region Sequences, Lazy Evaluation with Sequences


    // Sequences are the "equivalent" of Java Streams

    private fun sequences() {
        creatingSequences()
        lazyEvaluationWithSequences()
        collectionsVsSequences()
    }

    private fun creatingSequences() {
        // Generating Sequence of Infinite integer numbers
        val numbers = generateSequence(0) { it + 1 } // '0' is the first element of the sequence
        val sum = numbers.take(30).sum() // take first 30 integer numbers and calculate their sum
        print("CreatingSequences, sum: $sum")

        // Generate Sequence using "sequence" and "yield" functions

        val sequenceOfNumbers = sequence {
            var x = 0
            while (true) {
                yield(x++) // "yield" allows to suspend execution at some point and then return
            }
        }
        print("CreatingSequences, sequenceOfNumbers: ${sequenceOfNumbers.take(5).toList()}")

        val sequence = sequence {
            for (i in 1..5) {
                yield(i) // "yield" allows to suspend execution at some point and then return
            }
        }
        print("CreatingSequences, sequence: ${sequence.joinToString(" ")}")
    }

    private fun lazyEvaluationWithSequences() {
        // Lazy evaluation to existing collections

        val elements = 1 .. 1000000000000
        // Processing a big list of values without waiting for the result
        val output = elements.asSequence()
                .filter { it < 30 }         // no intermediate collection is created
                .map { Pair("Yes", it) }    // no intermediate collection is created
        output.forEach { print("Sequences, output: $it") }

        // the sum of first 30 elements
        val otherOutput = elements.asSequence().take(30).sum()
        print("Sequences, otherOutput: $otherOutput")
    }

    private fun collectionsVsSequences() {
        fun m(i: Int): Int {
            print("CollectionsVsSequences  m$i ")
            return i
        }

        fun f(i: Int): Boolean {
            print("CollectionsVsSequences  f$i ")
            return i % 2 == 0
        }
        // Collection, eager evaluation
        listOf(1, 2, 3, 4)
                // each operation returns the intermediate result collection;
                // computes the result in an eager fashion;
                // we apply 'map' to all elements and then 'filter' to all elements
                .map(::m)
                .filter(::f) // output: m1 m2 m3 m4 f1 f2 f3 f4

        print("CollectionsVsSequences  ============================")

        // Sequence, lazy evaluation
        listOf(1, 2, 3, 4)
                .asSequence()
                // operations don't create intermediate result until the result is asked for;
                // order of operations is important;
                // we apply 'map' and 'filter' for the 1st element, then for the 2nd and so on
                .map(::m)
                .filter(::f)
                // "toList()" - terminal operation(we explicitly ask for the result);
                // if it is not called nothing will be printed;
                // other terminal operations are "first()", "last()", "all()", "any()", "count()" etc.
                .toList() // output: m1 f1 m2 f2 m3 f3 m4 f4
    }

    //endregion

    //region Exceptions

    // No checked Exceptions in Kotlin

    private fun exceptionHandling() {
        var divisor = 0
        try {
            if (divisor == 0) {
                throw IllegalArgumentException("Can't Divide by Zero")
            } else {
                print("5 / $divisor = ${5 / divisor}")
            }
        } catch (e: IllegalArgumentException) {
            print("${e.message}")
        } finally {
            // do some required stuff
        }

        // We can use "try-catch" as expression

        var result = try {
            val i = 6 / 0
            "Success"
        } catch (e: Throwable) { // Throwable is a base class for Exeptions
            "Error"
        } finally {
            // if we place some value here to init "result", i won't be considered
            // (only values in try-catch block are considered)
        }
    }
    //endregion

    //region Classes

    // classes with "data" keyword have by default implementations for overloaded constructor,
    // getters, setters, hashCode(), equals(), toString(), copy()
    // and componentX()(used for Destructuring Declarations) methods.
    // Data class can inherit from other classes
    data class DataClass(val a: String = "", val b: Int = 0)

    private fun classesTest() {
        val billion = Animal("Billion", 15.0, 1.5)
        billion.printInfo()

        // Inheritance
        val buddy = Dog("Buddy", 25.0, 6.5, "Nat")
        buddy.printInfo()

        val data1 = DataClass("Hello", 10)
        val data2 = DataClass("Hello", 10)
        print("DataClass: $data1")
        // if we hadn't specified "data" keyword, two objected would have been compared as references (like in Java)
        if (data1 == data2) {
            print("DataClasses are equal")
        } else {
            print("DataClasses aren't equal")
        }

        val window = Window(2.5, 3.5, Color.GREEN)

        // use "with" method (part of Kotlin Standard library) to make code cleaner
        // returns Result
        with(window) {
            color = 125
            // init other properties of "w" here
        }

        // using "apply" method (part of Kotlin Standard library) to make code cleaner
        // returns Receiver
        val iBroken = window.apply {
            color = 150
            // init other properties of "w" here
        }.isBroken()

        // "let" is useful when we are creating an object that can be null
        var someString: String? = "Some value"
        someString?.let {
            // if "someString" is not null, then do some stuff
            it.length
        }

        // Constructor reference, object is not created yet
        val createDog = ::Dog
        val d = createDog("Tril", 34.5, 15.6, "Besh") // creating object

        // Non-Bound Reference to member function; refer to member of the class
        val widthPredicate = Window::isWider
        val isWider = widthPredicate(window, 4.0)

        // Bound Reference to member function; refer to specific member of the class
        val widthPredicateBound = window::isWider
        val wider = widthPredicateBound(4.0)
    }
    //endregion

    //region Sealed Classes

    // Restricts class hierarchy - used for representing restricted class hierarchies (inheritance).
    // All subclasses must be located in the same file

    sealed class Expr {
        data class Num(val number: Double) : Expr()
        data class Sum(val e1: Expr, val e2: Expr) : Expr()
        object NotANumber : Expr()
    }

    private fun eval(expr: Expr): Double = when(expr) {
        is LearnKotlinActivity.Expr.Num -> expr.number
        is LearnKotlinActivity.Expr.Sum -> eval(expr.e1) + eval(expr.e2)
        LearnKotlinActivity.Expr.NotANumber -> Double.NaN
        // the 'else' clause is not required
    }


    private fun sealedClasses() {
        val sum = eval(Expr.Sum(
                Expr.Sum(Expr.Num(1.0), Expr.Num(2.0)),
                Expr.Num(3.0)))
    }
    //endregion

    //region Enum Classes

    enum class Priority {
        MINOR, NORMAL, MAJOR, CRITICAL
    }

    enum class Priority1(val value: Int) {
        MINOR(-1) {
            override fun text(): String {
                return "Minor Priority text"
            }

            override fun toString(): String {
                return "Minor Priority"
            }
        },
        NORMAL(0) {
            override fun text(): String {
                return "Normal Priority text"
            }
        },
        MAJOR(1) {
            override fun text(): String {
                return "Major Priority text"
            }
        },
        CRITICAL(10) {
            override fun text(): String {
                return "Critical Priority text"
            }
        };       // semicolon is required here

        abstract fun text(): String
    }

    private fun enumClasses() {
        val priority = Priority.MAJOR
        print("Priority: $priority")

        print("Priority1 value: ${Priority1.CRITICAL.value}")

        print("Priority ordinal: ${priority.ordinal}")
        print("Priority name: ${priority.name}")

        for (value in Priority.values()) {
            print("Priority value: $value")

        }

        val p = Priority.valueOf("NORMAL") // access by name
        print("Priority valueOf: $p")

        print("Priority1 text: ${Priority1.CRITICAL.text()}")
    }
    //endregion

    //region Objects, Object Declaration

    // We can create an Object without it being an instance of the specific class

    // This is a way to create a Singleton - Kotlin creates an object instance as Singleton.
    // It behaves as a class, i.e. it can inherit from other classes: "object Client : OtherClass() {}"
    object Client {
        var id: Int = -1 // behaves as Static variable, but not exactly Static in terms of Java

        init {
            // we can use init block
        }

        fun pay() { // behaves as Static method, but not exactly Static in terms of Java

        }
    }

    private fun objectDeclaration() {
        print("Object Declaration, client's id: ${Client.id}")
        Client.pay()

        // local Object, object expression
        val localObject = object {
            val PI = 3.14159
        }

        print("Object Expression, PI: ${localObject.PI}")

        // using Singleton
        SigletonClass.doSomething()

    }
    //endregion

    //region Companion Object

    // Companion Object should be declared within a class. Can have extension properties.
    // Each class can have only one Companion Object
    class SomeClass {

        // this companion object is converted into the Static fields and methods
        companion object {
            var num: Int = 20 // Static variable

            @JvmStatic // if we want to call this method from Java file
            fun someMethod() { // Static method

            }
        }
    }

    // Companion object can be a receiver of extension function
    private fun SomeClass.Companion.someExtensionFun(): SomeClass {
        return SomeClass()
    }


    private fun companionObject() {
        print("Companion Object, num: ${SomeClass.num}")
        SomeClass.someMethod()

        // 'Companion' is a default name for companion object
        SomeClass.Companion

        // Companion objects are also used as Factories
        val animal = Animal.create("Cat")

        // Companion object Extension function
        val c = SomeClass.someExtensionFun()
    }
    //endregion

    //region Constants
    private fun constants() {
        // Object's Constant
        print("Object's Constant: ${Constants.GOOGLE_PLAY_LINK}")
        // Top level constant
        print("Top Level Constant: $ABOUT_APP_LINK")
    }
    //endregion

    //region Interfaces
    private fun interfacesTest() {
        val bird = Bird("Kukushka", true)
        bird.fly(36.9)
    }
    //endregion

    //region Type Casting
    private fun typeCasting() {
        val animal: Animal = Cat("Kitty", 15.0, 10.0, "Brown")
        sayMyau(animal)

        // Explicit cast in a safe way. It will try to cast and if it fails the "kitty" will be null
        val kitty = animal as? Cat
    }

    private fun sayMyau(a: Animal) {
        if (a is Cat) {
            a.myau() // this is Smart Cast
        }
    }
    //endregion

    //region Tuples and Destructuring Declarations
    private fun ddFunc(): DataClass {
        // some computations

        return DataClass("Rebeca", 30)
    }

    private fun tuplesAndDestructuringDeclaration() {
        // A destructuring declaration creates multiple variables at once.
        // We have declared two new variables: name and age, and can use them independently
        val data = DataClass("John", 15)
        val (name, age) = data
        print("Destructuring Declaration name: $name, age: $age)")

        // Destructuring declarations also work in for-loops
        val collection = listOf(DataClass("Ben", 23), DataClass("Ken", 34))
        for ((itemMame, itemAge) in collection) {
            print("Destructuring Declaration in For-Loop name: $itemMame, age: $itemAge)")
        }

        // Destructuring declarations also work when we want to return some things from a function
        val (name1, age1) = ddFunc()

        // If we don't need a variable in the destructuring declaration,
        // We can place an underscore instead of its name
        val (_, age2) = ddFunc()


        tuples()
    }

    private fun tuples() {
        val (capital, population) = capitalAndPopulation("England")
        val (continent, capital1, population1) = countryInfo("England")
        print("Tuples, England's continent: $continent")

        // Tuples in for-loop
        val listCapitalsAndCountries = listOf(Pair("Madrid", "Spain"), "Paris" to "France")
        for ((capital2, country) in listCapitalsAndCountries) {
            print("Tuples: $capital2, age: $country)")
        }
    }

    // function that returns a pair of string and long
    private fun capitalAndPopulation(country: String): Pair<String, Long> {
        return Pair("London", 6500000)
    }

    // function that returns a triple
    private fun countryInfo(country: String): Triple<String, String, Long> {
        return Triple("Europe","London", 6500000)
    }


    //endregion

    //region Annotations

    // Use existing Annotations in the same way as in Java. Annotations are compatible with Java

    //endregion

    //region Generics

    private fun generics() {
        val modelRepo = GenericRepository<Model>()
        val model = modelRepo.getById(5)

    }

    //endregion

    //region Type Alias
    private fun typeAlias() {
        val name: CustomerName = "some name"

        val customer = Customer("Joe", "joe@rrr.com")
        print("typeAlias, class: ${customer.javaClass.name}")

    }
    //endregion

    //region Function Alias
    private fun functionAlias() {
        val w = Window(3.0, 5.0)
        // inside next function we use function alias, this is useful in terms of conflict
        w.someCalculations()
    }
    //endregion

    //region Operator Overloading

    // using extension function
    operator fun StringBuilder.plus(other: StringBuilder) {
        other.forEach { this.append(it) }
    }

    fun operatorOverloading() {
        val c1 = OperOverloadingClass(1)
        val c2 = OperOverloadingClass(2)
        val c3 = c1 + c2
        if (c2 > c3) {

        }
        c3 += c1

        val sb = StringBuilder("Hello")
        sb + StringBuilder("5")
        print("OperOverloading, sb: $sb")

    }
    //endregion

    //region Bitwise functions

    private fun bitwiseFunctions() {
        val leftShift = 1 shl 2
        val rightShift = 1 shr 2
        val unsignedRightShift = 1 ushr 2
        val and = 1 and 0x00001111
        val or = 1 or 0x00001111
        val xor = 1 xor 0x00001111
        val inv = 1.inv()
    }

    //endregion

    //region stdlib

    private fun standardLibraryFunctions() {
        // apply() - declared on Any, it could be invoked on instances of all types; it makes code more readable;
        // use when need to utilize an instance of the object (modify properties),
        // express the chain of calls;
        // it differs from "run()" and "with()" in that it returns Receiver
        // ("run()" and "with()" returns some Result)
        // Takes Lambda with Receiver as an argument and returns Receiver
        val task = Runnable { print("STL apply(), Running task") }
        Thread(task).apply {  isDaemon = true }.start()

        // also() - use to represent some side effects that are going into code;
        // similar to "apply()" (returns Receiver), but it takes a regular lambda
        // (not lambda with a receiver) as an argument
        val windowOrNull: Window? = null
        windowOrNull?.apply { color = 0xff0000 }
                .also {
                    // call some function, e.g. showWindow(it)
                }?.isBroken()

        // let() - makes it easier to deal with a nullable argument that should be passed
        // to a function that expects a non-null parameter.
        // Takes Regular Lambda as an argument and returns some Result or Lambda
        // let() will be called if "n" is not null, let function turns "n" into
        // a parameter of the lambda("it").
        val n: Int? = 5
        val multiply3FuncResult = n?.let { multiply3Func(it) } // reads "if 'n' is not null call function multiply3Func(...)"

        // Another example with let() function
        var animal = Dog("Bilya", 15.0, 1.5, "Me")
        (animal as? Dog)?.let { print("STL This is a dog") }

        // with() - allows to call multiple methods on the same object without repeating
        // the reference to the object;
        fun alphabet() = with(StringBuilder()) {
            for (letter in 'A'..'Z') {
                append(letter)
            }
            append("\nEnd")
            toString()
        }

        // we can use "with()" func to work with member extension functions
        val window = Window(4.0, 4.0)

        with(window) {
            "Good.".describe()
            +"Robust." // unaryPlus operator is implemented in Window class
        }
        print("STL window description: ${window.description}")

        // run() - combines the use cases of 'with' and 'let'.
        // Use to indicate some kind of action or behavior that's kind of just getting fired off.
        // Takes Lambda with Receiver as an argument and returns some Result or Lambda
        val outputPath = Paths.get(cacheDir.absolutePath).run {
            val path = resolve("data")
            path.toFile().createNewFile()
            path
        }

        val c = windowOrNull?.run {
            color = 0x00ff00
            color
        }


        // run() - runs the block of code and returns the last expression as the result
        val res = run {
            print("STL run() called")
            "Result"
        }

        // lazy() - wraps an expensive function call to be invoked when first required
        val lazyMultiply = lazy { multiply3Func(600) }
        print("STL lazy(), lazyMultiply: ${lazyMultiply.value}") // multiply3Func function is invoked here

        // use() - is similar to the try-with-resources statement in Java;
        // replaces try-catch-finally block in simple cases;
        // object, on which use() is invoked, must implement Closeable interface;
        val input = Files.newInputStream(outputPath)
        val byte = input.use { /*it.read()*/ } // it will cleanup resources when the read is done

        // repeat() - executes function specified number of times;
        // replaces 'for' block for simple operations
        repeat(5) { print("STL repeat()") }


        // require(), assert(), check() - used to add a limited amount of formal specifications.
        // assert() - throws an AssertionException and used to ensure that our internal state is consistent.
        // assert() can be disabled at runtime.
        // check() - throws an IllegalStateException and also used for internal state consistency.
        // require() - throws an exception and used to ensure that arguments match the input conditions.
        // with require() we can do input validation
        val b: Int = 45
        require(b > 0) { "Number must be positive" }


        // takeIf() - returns the receiver object if it satisfies the given predicate,
        // otherwise returns null
        val someNumber = 42
        val takenNumber = someNumber.takeIf { it > 10 } // 42

        // takeUnless() - returns the receiver object if it doesn't satisfy the given predicate,
        // otherwise returns null
        val someNumber1 = 42
        val takenNumber1 = someNumber.takeUnless { it > 10 } // null

        // synchronized() - executes block of code holding monitor on the given object (someNumber - in this case)
        synchronized(someNumber) { print("STL synchronized") }

        // withLock() - executes action under this lock
        val l: Lock = ReentrantLock()
        l.withLock {
            // access the resources protected by this lock
        }

    }

    //endregion


    //region Coroutines
    private fun coroutines() {
        postItem(Any())

        testAsync()

        // main thread is blocked until 'startRunBlocking()' fun is executed
        //startRunBlocking()

        testScopes()
    }

    private fun testCoroutineExamples() {
        testComputeSum()
    }



    //endregion

    companion object {
        fun print(text: String) {
            Log.e("LearnKotlinActivity", text)
        }
    }
}
