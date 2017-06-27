package com.test.admin.testproj;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ListActivity {


    private String[] menu = new String[] {"SvgAnimation", "AnimatingToolbarTabLayoutFabStatusBarBgColorsActivity",
            "AudioProgressiveDownload", "ActivityTransitions", "Dagger2", "RxAndroid", "WebApp", "UnitTests", "For testing"};
    private String[] path = new String[] {"animation.SvgActivity", "animation.AnimatingToolbarTabLayoutFabStatusBarBgColorsActivity",
            "audio.AudioProgressiveDownload", "transitions.ActivityTransitions",
            "libs.dagger2.Dagger2Test", "libs.rx_android.RxAndroidTest", "webapp.WebActivity",
            "junit_learn.InstrumentedActivity", "test.TestActivity"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu));
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position,long id) {
        super.onListItemClick(list, view, position, id);
        String testName = path[position];
        try {
            Class clazz = Class
                    .forName("com.test.admin.testproj.tests." + testName);
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
