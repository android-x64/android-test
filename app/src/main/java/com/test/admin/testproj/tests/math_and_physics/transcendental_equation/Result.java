package com.test.admin.testproj.tests.math_and_physics.transcendental_equation;


/**
 * Created by sergey on 2/15/18.
 */

public class Result {
    private final Double x;

    public Result() {
        this.x = null;
    }

    public Result(double x) {
        this.x = x;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("******************* TranscendentalEquation ****************\n");
        if (x == null) {
            return builder.append("Method failed: max number of steps exceeded").toString();
        }
        return builder.append("Root:  x = ").append(x).toString();
    }
}
