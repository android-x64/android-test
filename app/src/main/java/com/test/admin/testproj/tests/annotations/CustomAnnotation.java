package com.test.admin.testproj.tests.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // defines whether the annotation is available at runtime
public @interface CustomAnnotation {
    String userRole() default "GUEST";
}
