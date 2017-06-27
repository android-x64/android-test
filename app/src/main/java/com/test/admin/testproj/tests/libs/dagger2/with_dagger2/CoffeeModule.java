package com.test.admin.testproj.tests.libs.dagger2.with_dagger2;

import com.test.admin.testproj.tests.libs.dagger2.ElectricHeater;
import com.test.admin.testproj.tests.libs.dagger2.Heater;
import com.test.admin.testproj.tests.libs.dagger2.Pump;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 13.08.2015.
 */
@Module
public class CoffeeModule {
    @Provides
    Pump providePump(Thermosiphon pump) {
        return pump;
    }

    @Provides
    Heater provideHeater() {
       return new ElectricHeater();
    }
}
