package com.test.admin.testproj.tests.libs.dagger2.with_dagger2;

import com.test.admin.testproj.tests.libs.dagger2.ElectricHeater;
import com.test.admin.testproj.tests.libs.dagger2.Heater;
import com.test.admin.testproj.tests.libs.dagger2.Pump;
import com.test.admin.testproj.tests.libs.dagger2.Thermosiphon;
import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.qualifiers.HotPlateQualifier;
import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.qualifiers.WaterQualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 13.08.2015.
 */
@Module
public class CoffeeModule {
    @Provides
    @MyApplicationScope
    Pump providePump(Thermosiphon pump) {
        return pump;
    }


    // Sometimes the type alone is insufficient to identify a dependency.
    // For example, a sophisticated coffee maker app may want separate heaters for the water
    // and the hot plate. In this case, we add a qualifier annotation.
    // This is any annotation that itself has a @Qualifier annotation
    // (in our case it is @HotPlateQualifier and @WaterQualifier annotations).
    // Also we can use @Named.
    @Provides
    @HotPlateQualifier
    Heater provideHotPlateHeater() {
       return new ElectricHeater(70);
    }

    @Provides
    @WaterQualifier
    Heater provideWaterHeater() {
        return new ElectricHeater(93);
    }
}
