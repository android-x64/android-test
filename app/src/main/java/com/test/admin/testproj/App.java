package com.test.admin.testproj;

import android.app.Activity;
import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.CoffeeMakerComponent;
import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.DaggerCoffeeMakerComponent;
import com.test.admin.testproj.tests.libs.dagger2.with_dagger2_android.DaggerAppComponent;
import com.test.admin.testproj.tests.libs.rx_android.examples.RxExampleComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created on 13.08.2015.
 */
public class App extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    private CoffeeMakerComponent coffeeMakerComponent; // we don't need this anymore since we use activityInjector
    private RxExampleComponent rxExampleExecutorComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        injectApp();
    }

    private void injectApp() {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    public CoffeeMakerComponent getCoffeeMakerComponent() {
        if(null == coffeeMakerComponent) {
            coffeeMakerComponent = DaggerCoffeeMakerComponent.create();
        }
        return coffeeMakerComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

//    public RxExampleComponent getRxExampleExecutor() {
//        if(null == rxExampleExecutorComponent) {
//            rxExampleExecutorComponent = DaggerRxExampleComponent.create();
//        }
//        return rxExampleExecutorComponent;
//    }
}
