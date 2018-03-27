package com.test.admin.testproj.tests.math_and_physics.integration.method;

import com.test.admin.testproj.tests.math_and_physics.Function;
import com.test.admin.testproj.tests.math_and_physics.integration.Result;

/**
 *  Алгебраический порядок точности 3.
 */

public class MethodOfSimpson implements Method {

    /**
     *
     * @param a
     * @param b
     * @param segments number of segments must be even
     * @param f
     * @return
     */
    @Override
    public Result solve(double a, double b, int segments, Function f) {
        double widthOfRegion = (b - a) / segments;

        //double widthOfRegion = (b - a) / 6;
        //double result = f.f(a) + 4*f.f((a + b) / 2) + f.f(b);
        double result = f.f(a) + f.f(b);

        double evenSum = 0;
        double oddSum = 0;

        for (double i = 1; i < segments; i++) {
            if (i % 2 == 0) {
                evenSum += f.f(a + widthOfRegion * i);
            } else {
                oddSum += f.f(a + widthOfRegion * i);
            }

            //result += f.f(a + widthOfRegion * i);
        }

        result = (widthOfRegion / 3) * (result + 2 * evenSum + 4 * oddSum);

        return new Result(result);
    }
}
