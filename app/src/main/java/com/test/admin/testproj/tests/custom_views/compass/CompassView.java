package com.test.admin.testproj.tests.custom_views.compass;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.utils.UiUtils;
import com.test.admin.testproj.tests.utils.Utils;

/**
 * Created by sergey on 6/28/17.
 */

public class CompassView extends View {

    private static final int DEFAULT_TEXT_SIZE_SP = 14;
    private static final int DEFAULT_MARKER_LENGTH_DP = 10;

    private String mNorth;
    private String mSouth;
    private String mEast;
    private String mWest;
    private Paint mMarkerPaint;
    private Paint mTextPaint;
    private Paint mCirclePaint;
    private int mTextHeight;
    private int mDefaultDiameter;
    private float mMarkerLength;
    private float mBearing = 90f;
    private float mPitch = 10;
    private float mRoll = 45;

    public CompassView(Context context) {
        super(context);
        init();
    }

    public CompassView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setFocusable(true);

        int[] screenSize = UiUtils.getScreenSize(getContext());
        mDefaultDiameter = Math.min(screenSize[0], screenSize[1]);
        mMarkerLength = Utils.convertDpToPixels(DEFAULT_MARKER_LENGTH_DP, getContext());

        Resources r = this.getResources();
        mNorth = r.getString(R.string.cardinal_north);
        mEast = r.getString(R.string.cardinal_east);
        mSouth = r.getString(R.string.cardinal_south);
        mWest = r.getString(R.string.cardinal_west);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(ContextCompat.getColor(getContext(), R.color.compass_background_color));
        mCirclePaint.setStrokeWidth(1);
        mCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.compass_text_color));
        mTextPaint.setTextSize(Utils.convertSpToPixels(DEFAULT_TEXT_SIZE_SP, getContext()));

        mTextHeight = (int)mTextPaint.measureText("Yy");

        mMarkerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMarkerPaint.setColor(ContextCompat.getColor(getContext(), R.color.compass_marker_color));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Called first
        // Highly recommend to implement onMeasure() for Reusability/Flexibility, but we don't have to

        // The compass is a circle that fills as much space as possible.
        // Set the measured dimensions by figuring out the shortest boundary, height or width.
        int measuredWidth = measure(widthMeasureSpec);
        int measuredHeight = measure(heightMeasureSpec);

        //resolveSize()

        int smallestSize = Math.min(measuredWidth, measuredHeight);
        setMeasuredDimension(smallestSize, smallestSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // Called second
        // No need to implement bcz this View has no children
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Called third
        // We have to implement onDraw() for View to appear

        // Find the center of the control, and store the length of the smallest side as the compass’s radius.
        int px = getMeasuredWidth() / 2;
        int py = getMeasuredHeight() / 2;
        int radius = Math.min(px, py);

        // Draw the outer boundary, and color the background of the compass face using the drawCircle method.
        canvas.drawCircle(px, py, radius, mCirclePaint);

        // Draw arrow
        int arrowY = (int) (mMarkerLength + (mTextHeight * 1.5));
        canvas.drawLine(px, arrowY, px - mMarkerLength / 2, arrowY + mTextHeight, mMarkerPaint);
        canvas.drawLine(px, arrowY, px + mMarkerLength / 2, arrowY + mTextHeight, mMarkerPaint);

        // This compass displays the current heading by rotating the face, so that the current direction is always
        // at the top of the device. To do this, rotate the canvas in the opposite direction to the current heading.
        // Rotate our perspective so that the ‘top’ is facing the current bearing.
        canvas.save();
        canvas.rotate(-mBearing, px, py);

        // Draw the markings. Rotate the canvas through a full rotation, drawing markings every 15 degrees
        // and the abbreviated direction string every 45 degrees.
        int cardinalY = py - radius + mTextHeight;

        //Draw the marker every 15 degrees and text every 45.
        for (int i = 0; i < 24; i++) {
            // Draw a marker.
            canvas.drawLine(px, py-radius, px, py - radius + mMarkerLength, mMarkerPaint);

            canvas.save();
            canvas.translate(0, mMarkerLength);

            // Draw the cardinal points
            if (i % 6 == 0) {
                String dirString = "";
                switch (i) {
                    case 0:
                        dirString = mNorth;
                        break;
                    case 6:
                        dirString = mEast;
                        break;
                    case 12:
                        dirString = mSouth;
                        break;
                    case 18:
                        dirString = mWest;
                        break;
                }
                int textWidth = (int) mTextPaint.measureText(dirString);
                int cardinalX = px - textWidth / 2;
                canvas.drawText(dirString, cardinalX, cardinalY, mTextPaint);
            } else if (i % 3 == 0) {
                // Draw the text every alternate 45deg
                String angle = String.valueOf(i * 15);
                float angleTextWidth = mTextPaint.measureText(angle);
                int angleTextX = (int) (px - angleTextWidth / 2);
                int angleTextY = py - radius + mTextHeight;
                canvas.drawText(angle, angleTextX, angleTextY, mTextPaint);
            }
            canvas.restore();

            canvas.rotate(15, px, py);
        }
        canvas.restore();

        RectF rollOval = new RectF(getMeasuredWidth() / 3 - getMeasuredWidth() / 7,
                                    getMeasuredHeight() / 2 - getMeasuredWidth() / 7,
                                    getMeasuredWidth() / 3 + getMeasuredWidth() / 7,
                                    getMeasuredHeight() / 2 + getMeasuredWidth() / 7);
        mMarkerPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rollOval, mMarkerPaint);
        mMarkerPaint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.rotate(mRoll, getMeasuredWidth() / 3, getMeasuredHeight() / 2);
        canvas.drawArc(rollOval, 0, 180, false, mMarkerPaint);
        canvas.restore();

        RectF pitchOval = new RectF(2 * getMeasuredWidth() / 3 - getMeasuredWidth() / 7,
                                    getMeasuredHeight() / 2 - getMeasuredWidth() / 7,
                                    2 * getMeasuredWidth() / 3 + getMeasuredWidth() / 7,
                                    getMeasuredHeight() / 2 + getMeasuredWidth() / 7);
        mMarkerPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(pitchOval, mMarkerPaint);
        mMarkerPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(pitchOval, 0 - mPitch / 2, 180 + mPitch, false, mMarkerPaint);
        mMarkerPaint.setStyle(Paint.Style.STROKE);
    }

    private int measure(int measureSpec) {
        int result;

        // Decode the measurement specifications.
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.UNSPECIFIED) {
            // Return a default size of 200 if no bounds are specified.
            result = mDefaultDiameter;
        } else {
            // As you want to fill the available space always return the full available bounds.
            result = specSize;
        }
        return result;
    }

    public float getBearing() {
        return mBearing;
    }

    public void setBearing(float bearing) {
        this.mBearing = bearing;
    }

    public float getPitch() {
        return mPitch;
    }

    public void setPitch(float pitch) {
        mPitch = pitch;
    }

    public float getRoll() {
        return mRoll;
    }

    public void setRoll(float roll) {
        mRoll = roll;
    }
}
