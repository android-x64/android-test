package com.test.admin.testproj.tests.libs.dagger2.with_dagger2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface MyApplicationScope {
}