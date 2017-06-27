package com.test.admin.testproj.instrumented_tests;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.test.rule.ServiceTestRule;
import android.util.Log;

import java.util.concurrent.TimeoutException;

public class InstrumentedServiceTestRule extends ServiceTestRule {

    @Override
    protected void beforeService() {
        Log.e("WWW", "WWW InstrumentedServiceTestRule  beforeService(); work before the service starts");
        super.beforeService();
    }

    @Override
    protected void afterService() {
        Log.e("WWW", "WWW InstrumentedServiceTestRule  afterService(); work after the service starts");
        super.afterService();
    }

    @Override
    public void startService(@NonNull Intent intent) throws TimeoutException {
        Log.e("WWW", "WWW InstrumentedServiceTestRule  startService();");
        super.startService(intent);
    }

    @Override
    public IBinder bindService(@NonNull Intent intent) throws TimeoutException {
        Log.e("WWW", "WWW InstrumentedServiceTestRule  bindService();");
        return super.bindService(intent);
    }
}
