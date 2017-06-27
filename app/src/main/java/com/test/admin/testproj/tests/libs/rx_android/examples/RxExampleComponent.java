package com.test.admin.testproj.tests.libs.rx_android.examples;

import com.test.admin.testproj.tests.libs.rx_android.RxAndroidTest;

import dagger.Component;

/**
 * Created on 13.08.2015.
 */
@Component(modules = RxExampleModule.class)
public interface RxExampleComponent {
    void inject(RxAndroidTest act);
}
