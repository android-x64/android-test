package com.test.admin.testproj.tests.libs.dagger2.with_dagger2;

import com.test.admin.testproj.tests.libs.dagger2.Dagger2Test;

import dagger.Component;

/**
 * Created on 13.08.2015.
 */
@Component(modules = CoffeeModule.class)
public interface CoffeeMakerComponent {
    CoffeeMaker maker();
    void inject(Dagger2Test act);
}
