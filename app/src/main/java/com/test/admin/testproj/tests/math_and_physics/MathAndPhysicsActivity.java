package com.test.admin.testproj.tests.math_and_physics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.math_and_physics.extremum.Extremum;
import com.test.admin.testproj.tests.math_and_physics.integration.DefiniteIntegral;

public class MathAndPhysicsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_and_physics);

        solveExtremum();
    }

    private void solveExtremum() {



        printResult(Extremum.example());
    }

    private void solveIntegration() {

        printResult(DefiniteIntegral.example());
    }




    private void printResult(Object o) {
        if (o != null) {
            Log.e("WWW", "WWW " + o.toString());
        }
    }
}
