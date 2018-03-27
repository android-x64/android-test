package com.test.admin.testproj.tests.math_and_physics.quadratic_equation;

import android.support.annotation.NonNull;

import java.util.Iterator;

/**
 * Created by sergey on 2/15/18.
 */

class SimpleCoefficient implements Coefficient {

    private final double mValue;

    SimpleCoefficient(double value) {
        mValue = value;
    }

    @NonNull
    @Override
    public Iterator<Double> iterator() {
        return new InnerIterator(mValue);
    }


    private static class InnerIterator implements Iterator<Double> {

        private final double mValue;
        private boolean mValueUsed;

        private InnerIterator(double value) {
            mValue = value;
        }

        @Override
        public boolean hasNext() {
            return !mValueUsed;
        }

        @Override
        public Double next() {
            mValueUsed = true;
            return mValue;
        }
    }
}
