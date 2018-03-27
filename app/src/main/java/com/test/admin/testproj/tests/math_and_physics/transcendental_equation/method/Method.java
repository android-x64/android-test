package com.test.admin.testproj.tests.math_and_physics.transcendental_equation.method;

import com.test.admin.testproj.tests.math_and_physics.Function;
import com.test.admin.testproj.tests.math_and_physics.transcendental_equation.Result;

/**
 * Created by sergey on 2/17/18.
 */

public interface Method {
    Result solve(double a, double b, int maxSteps, double tolerance, Function f);
}
