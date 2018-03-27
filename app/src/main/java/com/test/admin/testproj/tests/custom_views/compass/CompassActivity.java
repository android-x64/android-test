package com.test.admin.testproj.tests.custom_views.compass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.admin.testproj.R;

public class CompassActivity extends AppCompatActivity {

    private AdvancedCompassView mCompassView;
    private SensorManager mSensorManager;

    private final float[] mAccelerometerReading = new float[3];
    private final float[] mMagnetometerReading = new float[3];
    private final float[] mRotationMatrix = new float[9];
    private final float[] mOrientationAngles = new float[3];

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                System.arraycopy(event.values, 0, mAccelerometerReading,
                        0, mAccelerometerReading.length);
            }
            else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                System.arraycopy(event.values, 0, mMagnetometerReading,
                        0, mMagnetometerReading.length);
            }

            updateOrientationAngles();
            updateOrientation((float) Math.toDegrees(mOrientationAngles[0]),
                    (float) Math.toDegrees(mOrientationAngles[1]),
                    (float) Math.toDegrees(mOrientationAngles[2]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        mCompassView = (AdvancedCompassView) this.findViewById(R.id.compassView);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        updateOrientation(0, 0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorListener);
    }

    private void updateOrientationAngles() {
        // Update rotation matrix, which is needed to update orientation angles.
        SensorManager.getRotationMatrix(mRotationMatrix, null,
                mAccelerometerReading, mMagnetometerReading);

        // "mRotationMatrix" now has up-to-date information.

        SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);

        // "mOrientationAngles" now has up-to-date information.
    }

    private void updateOrientation(float heading, float pitch, float roll) {
        if (mCompassView != null) {
            mCompassView.setBearing(heading);
            mCompassView.setPitch(pitch);
            mCompassView.setRoll(roll);
            mCompassView.invalidate();
        }
    }
}
