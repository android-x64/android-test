package com.test.admin.testproj;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test.admin.testproj.tests.kotlin.interfaces.Eating;

import kotlin.collections.CollectionsKt;


public class MainActivity extends ListActivity {

    private String[] menu = new String[] {
            "Kotlin", "SvgAnimation", "AnimatingToolbarTabLayoutFabStatusBarBgColorsActivity",
            "AudioProgressiveDownload", "ActivityTransitions", "Dagger2", "RxAndroid", "RxExamples",
            "WebApp", "UnitTests", "CountUniqueCardsAlgorithm", "Compass", "Math and Physics"
    };
    private String[] path = new String[] {
            "kotlin.LearnKotlinActivity",
            "animation.SvgActivity", "animation.AnimatingToolbarTabLayoutFabStatusBarBgColorsActivity",
            "audio.AudioProgressiveDownload", "transitions.ActivityTransitions",
            "libs.dagger2.Dagger2TestActivity", "libs.rx_android.RxAndroidTestActivity",
            "libs.rx_android_examples.RxExamplesTestActivity", "webapp.WebActivity",
            "junit_learn.InstrumentedActivity", "algorithms.quick_algorithm_count_unique_cards.TestActivity",
            "custom_views.compass.CompassActivity", "math_and_physics.MathAndPhysicsActivity"
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    static void diagonalDifference(String s) {
//        Collections.sort(numbers, (Comparator<Student>) (s1, s2) -> {
//            if (s1.getCgpa() != s2.getCgpa())
//                return s1.getCgpa() < s2.getCgpa() ? -1 : 1;
//
//            if (!s1.getFname().equals(s2.getFname()))
//                return s1.getFname().compareTo(s2.getFname());
//
//            return (s1.getId() < s2.getId() ? -1 :
//                    (s1.getId() == s2.getId() ? 0 : 1));
//        });

        int k = 0;
        String smallest = s.substring(0, k);
        String largest = smallest;

        for(int i = 1; i + k <= s.length(); i++) {
            String sub = s.substring(i, i + k);
            if(smallest.compareTo(sub) > 0) {
                smallest = sub;
            }
            if(largest.compareTo(sub) < 0) {
                largest = sub;
            }
        }

    }
}
