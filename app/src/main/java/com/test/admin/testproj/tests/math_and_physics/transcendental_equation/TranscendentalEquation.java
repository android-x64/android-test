package com.test.admin.testproj.tests.math_and_physics.transcendental_equation;

import com.test.admin.testproj.tests.math_and_physics.Function;
import com.test.admin.testproj.tests.math_and_physics.transcendental_equation.method.BisectionMethod;
import com.test.admin.testproj.tests.math_and_physics.transcendental_equation.method.Method;

/**
 * A transcendental equation is an equation containing a transcendental function of the variable(s) being solved for. Such equations often do not have closed-form solutions. Examples include:
 * <br\>   x = e^-x
 * <br\>   x = cos⁡x
 * <br\>   lgx = x-5
 * <br\>   2^x = lgx + x^5 + 40
 */
public class TranscendentalEquation {

    private static final double TOLERANCE = 0.001;
    private static final int MAX_ITERATIONS = 1000000;

    private double a; // left interval
    private double b; // right interval
    private double tolerance = TOLERANCE;
    private int maxSteps = MAX_ITERATIONS;
    private Function f;
    private Method method = new BisectionMethod();

    private TranscendentalEquation(double a, double b, Function f) {
        this.a = a;
        this.b = b;
        this.f = f;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public void setMaxStepS(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    public void setMethod(Method method) {
        this.method = method;
    }


    public Result solve() {
        return method.solve(a, b, maxSteps, tolerance, f);
    }

    /**
     * @param a left interval
     * @param b right interval
     * @param f function
     */
    public static Result solve(double a, double b, Function f) {
        return new TranscendentalEquation(a, b, f).solve();
    }

    /**
     * @param a left interval
     * @param b right interval
     * @param f function
     */
    public static TranscendentalEquation equation(double a, double b, Function f) {
        return new TranscendentalEquation(a, b, f);
    }


    /**
     * Examples
     */
    public static Result example() {
        // 2 thx = x
//        printResult(TranscendentalEquation.solve(0.1, 10, x -> 2 * Math.tanh(x) - x));

        // 3 sinx = x
//        printResult(TranscendentalEquation.solve(0.1, 100, x -> 3 * Math.sin(x) - x));

        // f(x)=x^3-x-2
//        printResult(TranscendentalEquation.solve(0.1, 100, x -> Math.pow(x, 3) - x - 2));

        // 1.1x^2 -10x + 0.9 = 0
        return solve(0.1, 100, x -> 1.1 * Math.pow(x, 2) - 10 * x + 0.9); // seems it also works for Quadratic Equation
    }


}
