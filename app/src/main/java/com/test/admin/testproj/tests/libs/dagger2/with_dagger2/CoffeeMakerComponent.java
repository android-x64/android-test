package com.test.admin.testproj.tests.libs.dagger2.with_dagger2;

import com.test.admin.testproj.tests.libs.dagger2.CoffeeMaker;
import com.test.admin.testproj.tests.libs.dagger2.Dagger2TestActivity;

import dagger.Component;

/**
 * Created on 13.08.2015.
 */
@MyApplicationScope
@Component(modules = CoffeeModule.class)
public interface CoffeeMakerComponent {
    CoffeeMaker maker();

    void inject(Dagger2TestActivity act);
}
