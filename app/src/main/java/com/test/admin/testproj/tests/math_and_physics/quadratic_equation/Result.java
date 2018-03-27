package com.test.admin.testproj.tests.math_and_physics.quadratic_equation;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sergey on 2/15/18.
 */

public class Result {
    private final Map<CoefficientValues, SubResult> mSubResults;

    public Result() {
        mSubResults = new LinkedHashMap<>();
    }

    public void addSubResult(CoefficientValues values, SubResult subResult) {
        mSubResults.put(values, subResult);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("******************* QuadraticEquation ****************\n");
        for (CoefficientValues key : mSubResults.keySet()) {
            builder.append(key)
                    .append("\n")
                    .append("    ")
                    .append(mSubResults.get(key))
                    .append("\n");
        }

        return builder.toString();
    }

    static class SubResult {

        private final Double x1;
        private final Double x2;

        SubResult(double x1, double x2) {
            this.x1 = x1;
            this.x2 = x2;
        }

        SubResult() {
            this.x1 = null;
            this.x2 = null;
        }

        @Override
        public String toString() {
            if (x1 == null || x2 == null) {
                return "No Real Roots";
            }
            return "Roots:  x1 = " + x1 + "  x2 = " + x2;
        }
    }
}
