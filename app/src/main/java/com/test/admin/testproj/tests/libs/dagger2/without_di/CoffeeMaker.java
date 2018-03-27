package com.test.admin.testproj.tests.libs.dagger2.without_di;

import com.test.admin.testproj.tests.libs.dagger2.Coffee;
import com.test.admin.testproj.tests.libs.dagger2.ElectricHeater;
import com.test.admin.testproj.tests.libs.dagger2.Heater;
import com.test.admin.testproj.tests.libs.dagger2.Pump;
import com.test.admin.testproj.tests.libs.dagger2.Thermosiphon;

/**
 * Without DI
 */
public class CoffeeMaker {
    private final Heater heater;
    private final Pump pump;

    public CoffeeMaker() {
        heater = new ElectricHeater(70);
        pump = new Thermosiphon(heater);
    }

    public Coffee makeCoffee() {
        //...
        return new Coffee();
    }
}
