package com.test.admin.testproj.tests.libs.rx_android.examples;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 13.08.2015.
 */
@Module
public class RxExampleModule {
    @Provides
    RxExampleExecutor provideExampleExecutor() {
        return new RxExample_1();
    }
}
