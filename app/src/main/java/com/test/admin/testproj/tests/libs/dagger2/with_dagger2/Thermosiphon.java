package com.test.admin.testproj.tests.libs.dagger2.with_dagger2;

import com.test.admin.testproj.tests.libs.dagger2.Heater;
import com.test.admin.testproj.tests.libs.dagger2.Pump;

import javax.inject.Inject;

/**
 * Created on 13.08.2015.
 */
public class Thermosiphon implements Pump {
    private Heater heater;

    @Inject
    public Thermosiphon(Heater heater) {
        this.heater = heater;
    }
}
