package com.test.admin.testproj.robolectric;

import android.widget.Button;

import com.test.admin.testproj.BuildConfig;
import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.junit_learn.InstrumentedActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Robolectric executes on the JVM, not on the device or Emulator.
 */
@RunWith(RobolectricTestRunner.class)
@Config(/*constants = BuildConfig.class*/)
public class ActivityTestWithRobolectric {

    private InstrumentedActivity mActivity;

    @Before
    public void setup() {
        mActivity = Robolectric.setupActivity(InstrumentedActivity.class);
    }

    @Test
    public void testButtonClick() {
        Button button = (Button) mActivity.findViewById(R.id.btn_press_me);
        assertNotNull(button);
        assertTrue("Press me".equals(button.getText()));
    }
}
