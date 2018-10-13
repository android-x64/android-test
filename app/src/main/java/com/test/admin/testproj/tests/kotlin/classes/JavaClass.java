package com.test.admin.testproj.tests.kotlin.classes;

import com.test.admin.testproj.tests.kotlin.MyUtils;

public class JavaClass {
    public JavaClass() {

        // call Kotlin function
        int result = MyUtils.multiply(5, 6);
        //int result = UtilsKt.multiply(5, 6);

        // call Kotlin function with default parameter
        int result1 = MyUtils.multiply(5);
    }
}
