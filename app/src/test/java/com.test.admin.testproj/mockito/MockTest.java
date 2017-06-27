package com.test.admin.testproj.mockito;


import com.test.admin.testproj.tests.junit_learn.SimpleMath;

import org.junit.Test;
import org.mockito.Mockito;

public class MockTest {

    @Test
    public void testMock() {
        SimpleMath math = Mockito.mock(SimpleMath.class);
        System.out.println(math.getClass());
        int sum = math.add(9, 5);
        System.out.println(sum); //prints 0
        Mockito.when(math.add(9, 5)).thenReturn(14); //we dictate the behavior of mocked object
        sum = math.add(9, 5);
        System.out.println(sum); //prints 14
    }

    @Test
    public void testBadDiff() {
        SimpleMath math = Mockito.mock(SimpleMath.class);
        Mockito.when(math.diff(0, 0)).thenThrow(new Exception("don't test with zeros"));
        int sum = math.diff(0, 0);
    }
}
