package com.test.admin.testproj.tests.math_and_physics.integration;

import com.test.admin.testproj.tests.math_and_physics.Function;
import com.test.admin.testproj.tests.math_and_physics.integration.method.Method;
import com.test.admin.testproj.tests.math_and_physics.integration.method.MethodOfSimpson;

/**
 * Created by sergey on 2/17/18.
 */

public class DefiniteIntegral {
    private static final int REGIONS = 1000;

    private double a; // left interval
    private double b; // right interval
    private int regions = REGIONS;
    private Function f;
    private Method method = new MethodOfSimpson();

    private DefiniteIntegral(double a, double b, Function f) {
        this.a = a;
        this.b = b;
        this.f = f;
    }

    public void setRegions(int regions) {
        this.regions = regions;
    }

    public void setMethod(Method method) {
        this.method = method;
    }


    public Result solve() {
        return method.solve(a, b, regions, f);
    }

    /**
     * @param a left interval
     * @param b right interval
     * @param f function
     */
    public static Result solve(double a, double b, Function f) {
        return new DefiniteIntegral(a, b, f).solve();
    }

    /**
     * @param a left interval
     * @param b right interval
     * @param f function
     */
    public static DefiniteIntegral integral(double a, double b, Function f) {
        return new DefiniteIntegral(a, b, f);
    }


    /**
     * Examples
     */
    public static Result example() {
        //     5
        // I = ∫ dx/lnx
        //     2
//        DefiniteIntegral integral = integral(2, 5, x -> 1 / Math.log(x));
//        integral.setRegions(10);
//        return integral.solve();

        //     2
        // I = ∫ sqrt(1 + 2x^2 - x^3)dx
        //    1.2
//        DefiniteIntegral integral = integral(1.2, 2, x -> Math.sqrt(1 + 2 * x * x - x * x * x));
//        integral.setRegions(8);
//        return integral.solve();

        //     6
        // I = ∫ sqrt(x^2 + 3)dx
        //    -4
//        DefiniteIntegral integral = integral(-4, 6, x -> Math.sqrt(x * x +3));
//        integral.setRegions(10);
//        return integral.solve();

        //     2
        // I = ∫ x^1.5 sin^5 x dx
        //     1
        DefiniteIntegral integral = integral(1, 2, x -> Math.pow(x, 1.5) * Math.pow(Math.sin(x), 5));
        integral.setRegions(10);
        return integral.solve();

        //     5
        // I = ∫ e^-x^2 dx
        //     3
//        DefiniteIntegral integral = integral(3, 5, x -> Math.pow(Math.E, -Math.pow(x, 2)));
//        integral.setRegions(1000);
//        return integral.solve();

        //     ∞
        // I = ∫ e^-x^2 dx
        //     0
        // unsolved
//        new Thread(() -> {
//            Log.e("WWW", "WWW started thread");
//            DefiniteIntegral integral = integral(0, Double.MAX_VALUE, x -> Math.pow(Math.E, -Math.pow(x, 2)));
//            integral.setRegions(10);
//
//            Log.e("WWW", "WWW " + integral.solve().toString());
//        }).start();
//
//        return null;
    }


}
