package com.test.admin.testproj.tests.math_and_physics.quadratic_equation;


/**
 * Created by sergey on 2/15/18.
 */

public class QuadraticEquation {
    private final Coefficient mFirstCoefficient;
    private final Coefficient mSecondCoefficient;
    private final Coefficient mThirdCoefficient;

    public QuadraticEquation(double firstCoefficient, double secondCoefficient, double thirdCoefficient) {
        mFirstCoefficient = new SimpleCoefficient(firstCoefficient);
        mSecondCoefficient = new SimpleCoefficient(secondCoefficient);
        mThirdCoefficient = new SimpleCoefficient(thirdCoefficient);
    }

    public QuadraticEquation(Coefficient firstCoefficient, double secondCoefficient, double thirdCoefficient) {
        mFirstCoefficient = firstCoefficient;
        mSecondCoefficient = new SimpleCoefficient(secondCoefficient);
        mThirdCoefficient = new SimpleCoefficient(thirdCoefficient);
    }

    public QuadraticEquation(double firstCoefficient, Coefficient secondCoefficient, double thirdCoefficient) {
        mFirstCoefficient = new SimpleCoefficient(firstCoefficient);
        mSecondCoefficient = secondCoefficient;
        mThirdCoefficient = new SimpleCoefficient(thirdCoefficient);
    }

    public QuadraticEquation(double firstCoefficient, double secondCoefficient, Coefficient thirdCoefficient) {
        mFirstCoefficient = new SimpleCoefficient(firstCoefficient);
        mSecondCoefficient = new SimpleCoefficient(secondCoefficient);
        mThirdCoefficient = thirdCoefficient;
    }

    public QuadraticEquation(Coefficient firstCoefficient, Coefficient secondCoefficient, Coefficient thirdCoefficient) {
        mFirstCoefficient = firstCoefficient;
        mSecondCoefficient = secondCoefficient;
        mThirdCoefficient = thirdCoefficient;
    }

    public Result solve() {

        Result result = new Result();

        for (Double a : mFirstCoefficient) {
            for (Double b : mSecondCoefficient) {

                for (Double c : mThirdCoefficient) {

                    CoefficientValues values = new CoefficientValues(a, b, c);

                    double discriminant = b * b - 4 * a * c;  //D=b^2-4ac

                    if (discriminant > 0) {

                        //The number of real roots - 2
                        double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                        double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);

                        result.addSubResult(values, new Result.SubResult(x1, x2));
                    } else if (discriminant == 0) {

                        //The number of real roots - 1
                        double x1, x2;
                        x1 = x2 = -b / (2 * a);

                        result.addSubResult(values, new Result.SubResult(x1, x2));
                    } else {

                        // no real roots
                        result.addSubResult(values, new Result.SubResult());
                    }

                }
            }
        }

        return result;
    }

    public static Result example() {

        // 1.1x^2 + bx + 0.9 = 0, b = [-10, 10]
        return new QuadraticEquation(
                1.1,
                new RangeCoefficient(-10, 10, 1),
                0.9)
                .solve();
    }
}
