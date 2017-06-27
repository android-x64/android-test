package com.test.admin.testproj;

import android.app.Application;

import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.CoffeeMakerComponent;
import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.DaggerCoffeeMakerComponent;
import com.test.admin.testproj.tests.libs.rx_android.examples.DaggerRxExampleComponent;
import com.test.admin.testproj.tests.libs.rx_android.examples.RxExampleComponent;

/**
 * Created on 13.08.2015.
 */
public class App extends Application {

    private CoffeeMakerComponent coffeeMakerComponent;
    private RxExampleComponent rxExampleExecutorComponent;

    public CoffeeMakerComponent getCoffeeMakerComponent() {
        if(null == coffeeMakerComponent) {
            coffeeMakerComponent = DaggerCoffeeMakerComponent.create();
        }
        return coffeeMakerComponent;
    }

    public RxExampleComponent getRxExampleExecutor() {
        if(null == rxExampleExecutorComponent) {
            rxExampleExecutorComponent = DaggerRxExampleComponent.create();
        }
        return rxExampleExecutorComponent;
    }
}
