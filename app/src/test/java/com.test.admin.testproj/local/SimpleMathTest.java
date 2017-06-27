package com.test.admin.testproj.local;

import com.test.admin.testproj.local.categories.FastTests;
import com.test.admin.testproj.local.categories.InAppPurchaseTests;
import com.test.admin.testproj.local.categories.UserSignUpTests;
import com.test.admin.testproj.tests.junit_learn.SimpleMath;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@Category(FastTests.class)
public class SimpleMathTest {

    private SimpleMath mSimpleMath;

    @Rule
    public ReportTestExecution report = new ReportTestExecution();

    /*@BeforeClass
    public static void testClassSetup() {
        System.out.print("getting test class ready");
    }

    @AfterClass
    public static void testClassCleanup() {
        System.out.print("done with tests");
    }*/

    @Before
    public void setup() {
        mSimpleMath = new SimpleMath();
    }

    @After
    public void cleanup() {

    }

    @Test
    //@Category(FastTests.class)
    public void testAdd() {
        int total = mSimpleMath.add(2, 3);
        assertEquals("SimpleMath is not adding correctly", 5, total);
    }

    @Test
    @Category({UserSignUpTests.class, InAppPurchaseTests.class})
    public void testDiff() {
        int total = mSimpleMath.diff(2, 3);
        assertEquals("SimpleMath is not subtracting correctly", -1, total);
    }

    @Test
    public void testDiv() {
        double total = mSimpleMath.div(15, 3);
        assertThat(total, Matchers.equalTo(5d));
        //assertEquals("SimpleMath is not dividing correctly", 5, total, 0.0);
    }


    @Test(expected = ArithmeticException.class)
    public void testDivWithZeroDivisor() {
        double total = mSimpleMath.div(15, 0);
        assertEquals("SimpleMath is not handling division by zero correctly", 0, total, 0.0);
    }
}
