package com.test.admin.testproj.tests.libs.dagger2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by Admin on 04.04.2015.
 */
public class Dagger2TestActivity extends Activity {

    @Inject
    CoffeeMaker coffeeMaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this); // new way with dagger.android
        super.onCreate(savedInstanceState);
        //((App)getApplication()).getCoffeeMakerComponent().inject(this); old way
        Coffee coffee = coffeeMaker.makeCoffee();//coffeeMaker is not null!
        Log.e("WWW", "WWW coffee=" + coffee); //coffee is not null!
    }
}
