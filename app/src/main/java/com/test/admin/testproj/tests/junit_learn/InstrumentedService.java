package com.test.admin.testproj.tests.junit_learn;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class InstrumentedService extends Service {
    public InstrumentedService() {
    }

    @Override
    public void onDestroy() {
        Log.e("WWW", "WWW InstrumentedService  onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        Log.e("WWW", "WWW InstrumentedService  onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("WWW", "WWW InstrumentedService  onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("WWW", "WWW InstrumentedService  onBind()");
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {
        public InstrumentedService getService() {
            return InstrumentedService.this;
        }
    }
}
