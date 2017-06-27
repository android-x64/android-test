package com.test.admin.testproj.instrumented_tests;

import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.test.admin.testproj.tests.junit_learn.InstrumentedService;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ServiceTest {

    @Rule
    public InstrumentedServiceTestRule mServiceTestRule = new InstrumentedServiceTestRule();

    @Test
    public void testUnboundService() throws TimeoutException {
        mServiceTestRule.startService(new Intent(InstrumentationRegistry.getTargetContext(), InstrumentedService.class));
    }

    @Test
    public void testBoundService() throws TimeoutException {
        IBinder binder = mServiceTestRule.bindService(new Intent(InstrumentationRegistry.getTargetContext(), InstrumentedService.class));
        InstrumentedService service = ((InstrumentedService.LocalBinder) binder).getService();

        assertNotNull(service);
    }
}
