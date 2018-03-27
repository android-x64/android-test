package com.test.admin.testproj.tests.math_and_physics.integration.method;


import com.test.admin.testproj.tests.math_and_physics.Function;
import com.test.admin.testproj.tests.math_and_physics.integration.Result;

/**
 *  Алгебраический порядок точности равен 1
 */

public class TrapezoidalMethod implements Method {
    @Override
    public Result solve(double a, double b, int segments, Function f) {
        double widthOfRegion = (b - a) / segments;
        double result = (f.f(a) + f.f(b)) / 2;

        for (double i = 1; i < segments; i++) {
            result += f.f(a + widthOfRegion * i);
        }

        result *= widthOfRegion;

        return new Result(result);
    }
}
