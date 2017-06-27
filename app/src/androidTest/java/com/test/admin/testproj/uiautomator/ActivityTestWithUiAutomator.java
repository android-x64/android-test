package com.test.admin.testproj.uiautomator;

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.junit_learn.InstrumentedActivity;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

/**
 * UI tests that span multiple apps: This type of test verifies the correct behavior of
 * interactions between different user apps or between user apps and system apps.
 * For example, you might want to test that your camera app shares images correctly
 * with a 3rd-party social media app, or with the default Android Photos app.
 * UI testing frameworks that support cross-app interactions, such as UI Automator,
 * allow you to create tests for such scenarios.
 */
@RunWith(AndroidJUnit4.class)
public class ActivityTestWithUiAutomator {

    @Rule
    public ActivityTestRule<InstrumentedActivity> mActivityTestRule =
            new ActivityTestRule<>(InstrumentedActivity.class);
    @Test
    @Ignore
    public void testPressBackButton() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack();
    }

    @Test
    @Ignore
    public void testUiDevice() throws RemoteException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        if (device.isScreenOn()) {
            device.setOrientationLeft();
            device.openNotification();
        }
    }

    @Test
    public void testButtonClick() throws RemoteException, InterruptedException, UiObjectNotFoundException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        UiSelector editTextSelector = new UiSelector()
                .className("android.widget.EditText")
                .text(InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.some_text))
                .focusable(true);
        UiObject editTextWidget = device.findObject(editTextSelector);
        editTextWidget.setText("new Test");

        TimeUnit.SECONDS.sleep(2);

        UiSelector buttonSelector = new UiSelector()
                .className("android.widget.Button")
                .text(InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.press_me))
                .clickable(true);
        UiObject buttonWidget = device.findObject(buttonSelector);
        buttonWidget.click();

        TimeUnit.SECONDS.sleep(2);

    }
}
