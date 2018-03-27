package com.test.admin.testproj.tests.math_and_physics.integration.method;


import com.test.admin.testproj.tests.math_and_physics.Function;
import com.test.admin.testproj.tests.math_and_physics.integration.Result;

/**
 * Also known as Riemann sum. The result is very rough, therefore this method is not really popular.
 *  Алгебраический порядок точности равен 0
 */

public class RectangleMethod implements Method {
    @Override
    public Result solve(double a, double b, int segments, Function f) {

        return solveByMethodOfRightRectangles(a, b, segments, f);
    }

    private static Result solveByMethodOfLeftRectangles(double a, double b, int regions, Function f) {
        double widthOfRegion = (b - a) / regions;
        double sum = 0;

        for (double i = 0; i < regions; i++) {
            sum += f.f(a + widthOfRegion * i);
        }

        double Ileft = widthOfRegion * sum;
        return new Result(Ileft);
    }

    private static Result solveByMethodOfRightRectangles(double a, double b, int regions, Function f) {
        double widthOfRegion = (b - a) / regions;
        double sum = 0;

//        for (double x = a + widthOfRegion; x <= b; x += widthOfRegion) {
//            sum += f.f(x);
//        }

        for (double i = 1; i <= regions; i++) {
            sum += f.f(a + widthOfRegion * i);
        }

        double Iright = widthOfRegion * sum;
        return new Result(Iright);
    }

    /**
     * Most precise approximation
     */
    private static Result solveByMethodOfMediumRectangles(double a, double b, int regions, Function f) {
        double widthOfRegion = (b - a) / regions;
        double sum = 0;

        for (double i = 0; i < regions; i++) {
            sum += f.f(a + widthOfRegion * (i + 0.5));
        }


        double Imedium = widthOfRegion * sum;
        return new Result(Imedium);
    }
}
