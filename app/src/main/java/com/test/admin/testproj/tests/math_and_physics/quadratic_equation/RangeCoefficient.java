package com.test.admin.testproj.tests.math_and_physics.quadratic_equation;

import java.util.Iterator;

/**
 * Created by sergey on 2/15/18.
 */

public class RangeCoefficient implements Coefficient{
    public final double lower;
    public final double upper;

    private final double factor;


    public RangeCoefficient(double lower, double upper, double factor) {
        if (lower > upper) {
            throw new IllegalStateException("lower > upper");
        }
        this.lower = lower;
        this.upper = upper;
        this.factor = factor;
    }

    @Override
    public Iterator<Double> iterator() {
        return new InnerIterator(lower, upper, factor);
    }

    private static class InnerIterator implements Iterator<Double> {

        public final double lower;
        public final double upper;

        private final double factor;
        private double currentValue;

        private InnerIterator(double lower, double upper, double factor) {
            this.lower = lower;
            this.upper = upper;
            this.factor = factor;
            this.currentValue = lower;
        }

        @Override
        public boolean hasNext() {
            return currentValue <= upper;
        }

        @Override
        public Double next() {
            final double next = currentValue;
            currentValue += factor;
            return next;
        }
    }
}
