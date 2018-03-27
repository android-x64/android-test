package com.test.admin.testproj.tests.math_and_physics.transcendental_equation.method;

import com.test.admin.testproj.tests.math_and_physics.Function;
import com.test.admin.testproj.tests.math_and_physics.transcendental_equation.Result;

/**
 * The bisection method in mathematics is a root-finding method that repeatedly bisects an interval
 * and then selects a subinterval in which a root must lie for further processing.
 * It is a very simple and robust method, but it is also relatively slow. Because of this,
 * it is often used to obtain a rough approximation to a solution which is then used
 * as a starting point for more rapidly converging methods. The method is also called the interval halving method,
 * the binary search method, or the dichotomy method.
 */
public class BisectionMethod implements Method {

    public Result solve(double a, double b, int maxSteps, double tolerance, Function f) {

        double c = (a + b)/2; // new midpoint
        double f_c = f.f(c);
        double f_a = f.f(a);

        for (int steps = 0; steps < maxSteps; steps++) {
            if (f_c == 0 || (b - a)/2 < tolerance) {
                return new Result(c);
            }

            // new interval
            if (f_a * f_c > 0) {
                a = c;
                f_a = f_c;
            } else {
                b = c;
            }

            c = (a + b)/2;
            f_c = f.f(c);
        }
        return new Result();
    }


}
