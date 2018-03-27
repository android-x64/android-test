package com.test.admin.testproj.tests.math_and_physics.extremum;


/**
 * Created by sergey on 2/15/18.
 */

public class Result {
    private final Double x;
    private final Double f;

    public Result(double x, double f) {
        this.x = x;
        this.f = f;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("******************* Extremum ****************\n");
//        if (x == null) {
//            return builder.append("Method failed: no extremum").toString();
//        }
        return builder.append("Extremum:  x = ").append(x).append(", f = ").append(f).toString();
    }
}
