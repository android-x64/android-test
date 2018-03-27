package com.test.admin.testproj.tests.math_and_physics.quadratic_equation;


/**
 * Created by sergey on 2/15/18.
 */

public class CoefficientValues {
    public final double firstCoefficient;
    public final double secondCoefficient;
    public final double thirdCoefficient;

    public CoefficientValues(double firstCoefficient, double secondCoefficient, double thirdCoefficient) {
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.thirdCoefficient = thirdCoefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoefficientValues that = (CoefficientValues) o;

        if (Double.compare(that.firstCoefficient, firstCoefficient) != 0) return false;
        if (Double.compare(that.secondCoefficient, secondCoefficient) != 0) return false;
        return Double.compare(that.thirdCoefficient, thirdCoefficient) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(firstCoefficient);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(secondCoefficient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(thirdCoefficient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Coefficients: a=" + firstCoefficient + ", b=" + secondCoefficient + ", c=" + thirdCoefficient;
    }
}
