package com.test.admin.testproj.tests.transitions;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.transitions.SecondActivity;

public class ActivityTransitions extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        findViewById(R.id.btnSecondActivity).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) {
            Bundle animationBundle = ActivityOptionsCompat
                    .makeCustomAnimation(this, R.anim.activity_open_translate, R.anim.activity_close_scale)
                    .toBundle();
            startActivity(new Intent(this, SecondActivity.class), animationBundle);

            return;
        }

        startActivity(new Intent(this, SecondActivity.class));

        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);

    }
}
