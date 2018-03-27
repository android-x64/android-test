package com.test.admin.testproj.tests.libs.dagger2.with_dagger2.qualifiers;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by sergey on 2/21/18.
 */
@Qualifier
@Retention(RUNTIME)
public @interface WaterQualifier {
}
