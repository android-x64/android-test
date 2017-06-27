package com.test.admin.testproj.tests.libs.dagger2;

/**
 * Created on 13.08.2015.
 */
public class Thermosiphon implements Pump {
    private Heater heater;

    public Thermosiphon(Heater heater) {
        this.heater = heater;
    }
}
