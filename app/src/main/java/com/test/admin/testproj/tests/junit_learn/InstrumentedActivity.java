package com.test.admin.testproj.tests.junit_learn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.test.admin.testproj.R;

public class InstrumentedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrumented);
    }

    public void onPressMeClick(View view) {
        ((EditText) findViewById(R.id.edit_text)).setText("You clicked me!");
    }
}
