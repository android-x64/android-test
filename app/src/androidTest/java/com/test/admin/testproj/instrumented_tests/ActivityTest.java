package com.test.admin.testproj.instrumented_tests;

import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.RequiresDevice;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.junit_learn.InstrumentedActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ActivityTest {

    /*@Rule
    public ActivityTestRule<InstrumentedActivity> mActivityTestRule =
            new ActivityTestRule<>(InstrumentedActivity.class);*/
    @Rule
    public InstrumentedActivityTestRule<InstrumentedActivity> mActivityTestRule =
            new InstrumentedActivityTestRule<>(InstrumentedActivity.class);

    @Test
    @SmallTest
    public void testUi() {
        InstrumentedActivity activity = mActivityTestRule.getActivity();
        TextView helloText = (TextView) activity.findViewById(R.id.txt_hello);
        assertNotNull(helloText);
        assertTrue(helloText.isShown());
        assertEquals(InstrumentationRegistry.getTargetContext().getString(R.string.hello_world), helloText.getText());
        assertNull(activity.findViewById(android.R.id.button1));
    }

    @Test
    @RequiresDevice
    @LargeTest
    public void testBluetoothConnectivity()  {
        //...
    }

    @Test
    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.M)
    @RequiresDevice
    @MediumTest
    public void testFingerprintScan()  {
        //...
    }
}
