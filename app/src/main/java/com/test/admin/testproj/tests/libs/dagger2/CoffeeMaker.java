package com.test.admin.testproj.tests.libs.dagger2;

import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.qualifiers.HotPlateQualifier;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * With Dagger DI
 */
public class CoffeeMaker {
    private final Lazy<Heater> heater; // Create a possibly costly heater only when we use it.
    private final Pump pump;

    @Inject
    CoffeeMaker(@HotPlateQualifier Lazy<Heater> heater, Pump pump) {
        this.heater = heater;
        this.pump = pump;
    }

    private <T> T checkNotNull(T obj) {
        if(null == obj)
            throw new NullPointerException("obj can't be null");
        return obj;
    }

    public Coffee makeCoffee() {
        //...
        return new Coffee();
    }
}
