package com.test.admin.testproj.instrumented_tests;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SimpleTest {
    @Test
    public void testAdd() {
        assertEquals("SimpleMath is not adding correctly", 5, 3+2);
    }

    @Test
    public void testDiff() {
        assertEquals("SimpleMath is not subtracting correctly", 2, 7-5);
    }
}
