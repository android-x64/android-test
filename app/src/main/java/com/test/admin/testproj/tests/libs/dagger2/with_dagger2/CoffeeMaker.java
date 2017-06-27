package com.test.admin.testproj.tests.libs.dagger2.with_dagger2;

import com.test.admin.testproj.tests.libs.dagger2.Coffee;
import com.test.admin.testproj.tests.libs.dagger2.Heater;
import com.test.admin.testproj.tests.libs.dagger2.Pump;

import javax.inject.Inject;

/**
 * With Manual DI
 */
public class CoffeeMaker {
    private final Heater heater;
    private final Pump pump;

    @Inject
    public CoffeeMaker(Heater heater, Pump pump) {
        this.heater = checkNotNull(heater);
        this.pump = checkNotNull(pump);
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
