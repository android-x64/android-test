package com.test.admin.testproj.robotium;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.test.admin.testproj.tests.junit_learn.InstrumentedActivity;

public class ActivityTestWithRobotium extends ActivityInstrumentationTestCase2<InstrumentedActivity> {

    private Solo mSolo;

    public ActivityTestWithRobotium() {
        super(InstrumentedActivity.class);
    }

    @Override
    public void setUp() {
        mSolo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() {
        mSolo.finishOpenedActivities();
    }

    public void testPushMe() {
        mSolo.waitForActivity(InstrumentedActivity.class);
        mSolo.assertCurrentActivity("InstrumentedActivity is not displayed", InstrumentedActivity.class);
        assertTrue("Text in EditText is not displayed", mSolo.searchText("Some Text"));
        mSolo.clickOnButton("Press me");
        assertTrue("New Text is not displayed in the EditText", mSolo.searchText("You clicked me!"));
    }
}
