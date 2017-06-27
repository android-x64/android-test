package com.test.admin.testproj.espresso;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.junit_learn.InstrumentedActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class ActivityTestWithEspresso {

    @Rule
    public ActivityTestRule<InstrumentedActivity> mActivityTestRule =
            new ActivityTestRule<>(InstrumentedActivity.class);

    @Test
    public void testEspresso() {
        onView(allOf(
                withId(R.id.edit_text), withText(R.string.some_text), hasFocus()))
            .perform(ViewActions.replaceText("Test Textttttt"));

        onView(allOf(
                withId(R.id.edit_text), withText("Test Textttttt")))
            .check(ViewAssertions.matches(hasFocus()));
    }


}
