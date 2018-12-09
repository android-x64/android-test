package com.test.admin.testproj.tests.kotlin.generics

import com.test.admin.testproj.tests.kotlin.classes.Animal
import com.test.admin.testproj.tests.kotlin.classes.Dog


// In Java all classes are Invariant - we can't pass List<Dog> to function 'operate'
// (which takes 'List<Animal>' as a param);
// But in Kotlin we can, using Immutable collections;
// List<T> is covariant when: Dog is subtype of Animal & List<Dog> is subtype of List<Animal>;

fun testCovariance() {
    val dogList: List<Dog> = listOf(Dog("Bilya", 4.0, 2.5, "Me"))
    operate(dogList)

    val ro = ReadOnlyRepoImpl<Dog>()
    // we can assign List<Dog> to List<Animal>
    val animals: List<Animal> = ro.getAll()
}


// We say: "Values are passed out", that means we are not modifying anything, they are just passed out
fun operate(list: List<Animal>) {}


// use 'out' modifier to indicate covariance (similar to "? extends T" in Java)
interface ReadOnlyRepo<out T> {
    fun getEntity(id: Int): T
    fun getAll(): List<T>
}

class ReadOnlyRepoImpl<out T> : ReadOnlyRepo<T> {
    override fun getEntity(id: Int): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

//     CONTRAVARIANCE

// Structure<T> is contravariant when:
// Dog is subtype of Animal & Structure<Animal> is subtype of Structure<Dog>

// values are passed in;
// use (Declaration-Site) 'in' modifier to indicate contravariance (similar to "? super T" in Java)
interface WriteRepo<in T> {
    fun save(obj: T)
    fun saveAll(list: List<T>)
}

//==================================================================

// Kotlin uses Declaration-Site variance ('in' and 'out' modifiers on the declaration);
// Java uses Use-Site variance (modifiers on the call);
// Kotlin offers Use-Site variance when it's impossible to have variance on the type;
// Type Projection - define variance on the call, creates a restricted form of the type;
fun copy(list: MutableList<out Animal>) {

    // Star Projections are a safe way to indicate a subtype of a projection;
    // Used to indicate we have no information about a generic argument
    if (list is List<*>) {

    }
}

// For 'Foo<out T>', where 'T' is a covariant type parameter with the upper bound 'TUpper' -
// 'Foo<*>' is equivalent to 'Foo<out TUpper>';
// For 'Foo<in T>', where 'T' is a contravariant type parameter - 'Foo<*>' is equivalent to 'Foo<in Nothing>';
// For 'Foo<T>', where 'T' is an invariant type parameter with the upper bound 'TUpper' -
// 'Foo<*>' is equivalent to 'Foo<out TUpper>' - for reading values and 'Foo<in Nothing>' - for writing values





