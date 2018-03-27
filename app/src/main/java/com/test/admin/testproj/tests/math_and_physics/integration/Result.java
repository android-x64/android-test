package com.test.admin.testproj.tests.math_and_physics.integration;


/**
 * Created by sergey on 2/15/18.
 */

public class Result {
    private final Double I;

    public Result() {
        this.I = null;
    }

    public Result(double I) {
        this.I = I;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("******************* DefiniteIntegral ****************\n");
        if (I == null) {
            return builder.append("Method failed: max number of regions exceeded").toString();
        }
        return builder.append("Area:  I = ").append(I).toString();
    }
}
