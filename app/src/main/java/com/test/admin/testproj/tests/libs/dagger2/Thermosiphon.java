package com.test.admin.testproj.tests.libs.dagger2;

import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.qualifiers.WaterQualifier;

import javax.inject.Inject;

/**
 * Created on 13.08.2015.
 */
public class Thermosiphon implements Pump {
    private Heater heater;

    @Inject
    public Thermosiphon(@WaterQualifier Heater heater) {
        this.heater = heater;
    }
}
