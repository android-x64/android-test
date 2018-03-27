package com.test.admin.testproj.tests.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by sergey on 6/30/17.
 */

public class Utils {

    public static float convertDpToPixels(float dp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float convertSpToPixels(float sp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static float convertDpToSp(float dp, Context context) {
        return (convertDpToPixels(dp, context) / convertSpToPixels(dp, context));
    }
}
