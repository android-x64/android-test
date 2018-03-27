package com.test.admin.testproj.tests.math_and_physics.extremum;

import com.test.admin.testproj.tests.math_and_physics.Function;

/**
 * Created by sergey on 2/25/18.
 */

public class Extremum {
    private static final double TOLERANCE = 0.001;


    private double a; // left interval
    private double b; // right interval
    private double accuracy = TOLERANCE;
    private Function f;

    public Extremum(double a, double b, double accuracy, Function f) {
        this(a, b, f);
        this.accuracy = accuracy;
    }

    public Extremum(double a, double b, Function f) {
        this.a = a;
        this.b = b;
        this.f = f;
    }

    public Result solveByGoldenSectionMethod() {
        final double  goldenRatio = (1 + Math.sqrt(5)) / 2; // "Золотое" число
        double a = this.a;
        double b = this.b;
        double x1, x2; // Точки, делящие текущий отрезок в отношении золотого сечения

        while (Math.abs(b - a) > accuracy) {
            x1 = b - (b - a) / goldenRatio;
            x2 = a + (b - a) / goldenRatio;
            if (f.f(x1) <= f.f(x2)) // Условие для поиска максимума
                a = x1;
            else
                b = x2;
        } // Выполняем, пока не достигнем заданной точности

        return new Result((a + b) / 2, f.f((a + b) / 2));
    }

    public static Object example() {

        // x^3 / (e^x + 1)
        //return new Extremum(1, 100, x -> x * x * x / (Math.pow(Math.E, x) + 1)).solveByGoldenSectionMethod();

        // x^2 e^(-x^2)
        return new Extremum(1, 100, x -> x * x * Math.pow(Math.E, -(x * x))).solveByGoldenSectionMethod();

        // sin(x)
        //return new Extremum(1, 100, Math::sin).solveByGoldenSectionMethod();
    }
}
