package com.test.admin.testproj.local;

import com.test.admin.testproj.local.categories.FastTests;
import com.test.admin.testproj.local.categories.UserSignUpTests;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({SimpleMathTest.class, AnotherTest.class}) //runs only tests in SimpleMathTest and AnotherTest classes
@Categories.IncludeCategory(FastTests.class)
@Categories.ExcludeCategory(UserSignUpTests.class)
public class CustomTestSuit {
}
