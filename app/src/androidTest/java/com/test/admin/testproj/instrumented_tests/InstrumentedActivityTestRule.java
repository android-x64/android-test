package com.test.admin.testproj.instrumented_tests;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;

public class InstrumentedActivityTestRule<A extends Activity> extends ActivityTestRule<A> {
    public InstrumentedActivityTestRule(Class<A> activityClass) {
        super(activityClass);
    }

    @Override
    protected Intent getActivityIntent() {
        return super.getActivityIntent();
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
    }

    @Override
    public A launchActivity(@Nullable Intent startIntent) {
        return super.launchActivity(startIntent);
    }


}
