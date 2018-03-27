package com.test.admin.testproj.tests.libs.dagger2.with_dagger2_android;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.test.admin.testproj.tests.libs.dagger2.Dagger2TestActivity;
import com.test.admin.testproj.tests.libs.dagger2.ElectricHeater;
import com.test.admin.testproj.tests.libs.dagger2.Heater;
import com.test.admin.testproj.tests.libs.dagger2.Pump;
import com.test.admin.testproj.tests.libs.dagger2.Thermosiphon;
import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.qualifiers.HotPlateQualifier;
import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.qualifiers.WaterQualifier;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by sergey on 2/23/18.
 */
@Module
public abstract class AppModule {
    @Binds
    abstract Context provideContext(Application application);

    @Provides
    @Singleton
    static Pump providePump(Thermosiphon pump) {
        return pump;
    }

    @Provides
    @HotPlateQualifier
    static Heater provideHotPlateHeater() {
        return new ElectricHeater(70);
    }

    @Provides
    @WaterQualifier
    static Heater provideWaterHeater() {
        return new ElectricHeater(93);
    }

    @Provides
    static Intent provideIntent(Dagger2TestActivity act) {
        return act.getIntent();
    }

}
