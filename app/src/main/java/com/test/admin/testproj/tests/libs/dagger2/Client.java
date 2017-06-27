package com.test.admin.testproj.tests.libs.dagger2;

import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.CoffeeMakerComponent;
import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.DaggerCoffeeMakerComponent;
import com.test.admin.testproj.tests.libs.dagger2.without_di.CoffeeMaker;

import javax.inject.Inject;

/**
 * Created on 13.08.2015.
 */
public class Client {

    public void drinkCoffeeWithoutDI() {
        Coffee coffee = new CoffeeMaker().makeCoffee();
    }

    public void drinkCoffeeWithManualDI() {
        Heater heater = new ElectricHeater();
        Pump pump = new Thermosiphon(heater);

        Coffee coffee = new com.test.admin.testproj.tests.libs.dagger2.with_manual_di.CoffeeMaker(heater, pump).makeCoffee();
    }

    public void drinkCoffeeWithDagger2DI() {
        CoffeeMakerComponent component = DaggerCoffeeMakerComponent.create();
        Coffee coffee = component.maker().makeCoffee();
    }
}
