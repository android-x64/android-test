package com.test.admin.testproj.tests.kotlin.metaprogramming

import android.util.Log

@Target(AnnotationTarget.CLASS) // annotation 'Table' can be applicable only to classes
@Retention // to retain information at Runtime (in Kotlin it is by default - no need to use this annotation)
//@Repeatable // annotation can be repeated more than once
//@MustBeDocumented // annotation should be shown in generated documentation
annotation class Table(val name: String)

@Target(AnnotationTarget.PROPERTY) // annotation 'Field' can be applicable only to properties
annotation class Field(val name: String)

@Table("ContactTable")
data class Contact(val id: Int,
                   @Field("NAME") val name: String,
                   val email: String)


fun testAnnotations() {
    val tableAnnotation = Contact::class.annotations.find { it.annotationClass.simpleName == "Table" }

    Log.e("WWW", "WWW Annotations tableAnnotation: $tableAnnotation")
}

// TARGETING JAVA BYTECODE

// - Kotlin can generate multiple Java Elements
// - With annotations we can select what to target (what to generate)
//      @field:     Java Field
//      @get, @set: Java Getter/Setter
//      @property:  Java Property
//      @file, @receiver, @param, @setparam, @delegate
