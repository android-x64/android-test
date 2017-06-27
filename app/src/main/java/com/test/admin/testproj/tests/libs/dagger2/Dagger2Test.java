package com.test.admin.testproj.tests.libs.dagger2;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.test.admin.testproj.App;
import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.libs.dagger2.with_dagger2.CoffeeMaker;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by Admin on 04.04.2015.
 */
public class Dagger2Test extends Activity {

    @Inject
    CoffeeMaker coffeeMaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getApplication()).getCoffeeMakerComponent().inject(this);
        Coffee coffee = coffeeMaker.makeCoffee();//coffeeMaker is not null!
        Log.e("WWW", "WWW coffee=" + coffee); //coffee is not null!
    }
}
