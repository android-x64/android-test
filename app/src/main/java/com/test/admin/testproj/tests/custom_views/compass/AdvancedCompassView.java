package com.test.admin.testproj.tests.custom_views.compass;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.utils.UiUtils;
import com.test.admin.testproj.tests.utils.Utils;

/**
 * Created by sergey on 6/28/17.
 */

public class AdvancedCompassView extends View {

    private static final int DEFAULT_TEXT_SIZE_SP = 14;
    private static final int DEFAULT_MARKER_LENGTH_DP = 10;

    private enum CompassDirection { N, NNE, NE, ENE, E, ESE, SE, SSE, S, SSW, SW, WSW, W, WNW, NW, NNW }

    private int[] mBorderGradientColors;
    private float[] mBorderGradientPositions;

    private int[] mGlassGradientColors;
    private float[] mGlassGradientPositions;

    private int mSkyHorizonColorFrom;
    private int mSkyHorizonColorTo;
    private int mGroundHorizonColorFrom;
    private int mGroundHorizonColorTo;

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

    public AdvancedCompassView(Context context) {
        super(context);
        init();
    }

    public AdvancedCompassView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdvancedCompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AdvancedCompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        mCirclePaint.setColor(ContextCompat.getColor(getContext(), R.color.adv_compass_background_color));
        mCirclePaint.setStrokeWidth(1);
        mCirclePaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.adv_compass_inner_border));
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setSubpixelText(true);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextSize(Utils.convertSpToPixels(DEFAULT_TEXT_SIZE_SP, getContext()));

        mTextHeight = (int)mTextPaint.measureText("Yy");

        mMarkerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMarkerPaint.setColor(ContextCompat.getColor(getContext(), R.color.adv_compass_marker_color));
        mMarkerPaint.setAlpha(200);
        mMarkerPaint.setStrokeWidth(1);
        mMarkerPaint.setStyle(Paint.Style.STROKE);
        mMarkerPaint.setShadowLayer(2, 1, 1, ContextCompat.getColor(getContext(), R.color.adv_compass_shadow_color));

        mBorderGradientColors = new int[4];
        mBorderGradientPositions = new float[4];

        mBorderGradientColors[3] = ContextCompat.getColor(getContext(), R.color.adv_compass_outer_border);
        mBorderGradientColors[2] = ContextCompat.getColor(getContext(), R.color.adv_compass_inner_border_one);
        mBorderGradientColors[1] = ContextCompat.getColor(getContext(), R.color.adv_compass_inner_border_two);
        mBorderGradientColors[0] = ContextCompat.getColor(getContext(), R.color.adv_compass_inner_border);
        mBorderGradientPositions[3] = 1.0f;
        mBorderGradientPositions[2] = 1 - 0.06f;
        mBorderGradientPositions[1] = 1 - 0.03f;
        mBorderGradientPositions[0] = 0.0f;

        mGlassGradientColors = new int[5];
        mGlassGradientPositions = new float[5];

        int glassColor = 245;
        mGlassGradientColors[4] = Color.argb(65, glassColor, glassColor, glassColor);
        mGlassGradientColors[3] = Color.argb(100, glassColor, glassColor, glassColor);
        mGlassGradientColors[2] = Color.argb(50, glassColor, glassColor, glassColor);
        mGlassGradientColors[1] = Color.argb(0, glassColor, glassColor, glassColor);
        mGlassGradientColors[0] = Color.argb(0, glassColor, glassColor, glassColor);
        mGlassGradientPositions[4] = 1 - 0.0f;
        mGlassGradientPositions[3] = 1 - 0.06f;
        mGlassGradientPositions[2] = 1 - 0.10f;
        mGlassGradientPositions[1] = 1 - 0.20f;
        mGlassGradientPositions[0] = 1 - 1.0f;

        mSkyHorizonColorFrom = ContextCompat.getColor(getContext(), R.color.horizon_sky_from);
        mSkyHorizonColorTo = ContextCompat.getColor(getContext(), R.color.horizon_sky_to);

        mGroundHorizonColorFrom = ContextCompat.getColor(getContext(), R.color.horizon_ground_from);
        mGroundHorizonColorTo = ContextCompat.getColor(getContext(), R.color.horizon_ground_to);
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

        // Calculate the width of the outer (heading) ring based on the size of the font used to draw the heading values.
        float ringWidth = mTextHeight + 4;

        // Then calculate the height and width of the View, and use those values to establish
        // the radius of the inner and outer face dials, as well as create the bounding boxes for each face.
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        int px = width/2;
        int py = height/2;
        Point center = new Point(px, py);
        int radius = Math.min(px, py) - 2;
        RectF boundingBox = new RectF(  center.x - radius,
                                        center.y - radius,
                                        center.x + radius,
                                        center.y + radius);
        RectF innerBoundingBox = new RectF( center.x - radius + ringWidth,
                                            center.y - radius + ringWidth,
                                            center.x + radius - ringWidth,
                                            center.y + radius - ringWidth);
        float innerRadius = innerBoundingBox.height() / 2;

        // Drawing the faces. Start from the bottom layer at the outside, and work your way in and up,
        // starting with the outer face (heading). Create a new RadialGradient Shader using the colors and positions
        // you defined in init(), and assign that Shader to a new Paint before using it to draw a circle.
        RadialGradient borderGradient = new RadialGradient( px, py, radius,
                                                            mBorderGradientColors,
                                                            mBorderGradientPositions,
                                                            Shader.TileMode.CLAMP );
        Paint pgb = new Paint();
        pgb.setShader(borderGradient);

        Path outerRingPath = new Path();
        outerRingPath.addOval(boundingBox, Path.Direction.CW);

        canvas.drawPath(outerRingPath, pgb);

        // Draw the artificial horizon. The horizon is created by dividing the circular face into two sections,
        // one representing the sky and the other the ground. The proportion of each section depends on the current
        // pitch. Start by creating the Shader and Paint objects that will be used to draw the sky and earth.
        LinearGradient skyShader = new LinearGradient(center.x, innerBoundingBox.top,
                center.x, innerBoundingBox.bottom, mSkyHorizonColorFrom, mSkyHorizonColorTo, Shader.TileMode.CLAMP);
        Paint skyPaint = new Paint();
        skyPaint.setShader(skyShader);

        LinearGradient groundShader = new LinearGradient(center.x, innerBoundingBox.top,
                center.x, innerBoundingBox.bottom, mGroundHorizonColorFrom, mGroundHorizonColorTo, Shader.TileMode.CLAMP);
        Paint groundPaint = new Paint();
        groundPaint.setShader(groundShader);

        // Normalize the pitch and roll values to clamp them within ±90 degrees and ±180 degrees, respectively.
        float tiltDegree = mPitch;
        while (tiltDegree > 90 || tiltDegree < -90) {
            if (tiltDegree > 90)  tiltDegree = -90 + (tiltDegree - 90);
            if (tiltDegree < -90) tiltDegree = 90 - (tiltDegree + 90);
        }

        float rollDegree = mRoll;
        while (rollDegree > 180 || rollDegree < -180) {
            if (rollDegree > 180)  rollDegree = -180 + (rollDegree - 180);
            if (rollDegree < -180) rollDegree = 180 - (rollDegree + 180);
        }

        // Create paths that will fill each segment of the circle (ground and sky).
        // The proportion of each segment should be related to the clamped pitch.
        Path skyPath = new Path();
        skyPath.addArc(innerBoundingBox, -tiltDegree, (180 + (2 * tiltDegree)));

        // Spin the canvas around the center in the opposite direction to the current roll,
        // and draw the sky and ground paths using the Paints you created earlier.
        canvas.save();
        canvas.rotate(-rollDegree, px, py);
        canvas.drawOval(innerBoundingBox, groundPaint);
        canvas.drawPath(skyPath, skyPaint);
        canvas.drawPath(skyPath, mMarkerPaint);

        // Next is the face marking. Start by calculating the start and end points for the horizontal horizon markings.
        int markWidth = radius / 3;
        int startX = center.x - markWidth;
        int endX = center.x + markWidth;

        // To make the horizon values easier to read, you should ensure that the pitch scale always starts
        // at the current value. The following code calculates the position of the interface
        // between the ground and sky on the horizon face:
        double h = innerRadius*Math.cos(Math.toRadians(90 - tiltDegree));
        double justTiltY = center.y - h;

        // Find the number of pixels that represents each degree of tilt.
        float pxPerDegree = (innerBoundingBox.height()/2)/45f;

        // Now iterate over 180 degrees, centered on the current tilt value, to give a sliding scale of possible pitch.
        for (int i = 90; i >= -90; i -= 10) {
            double ypos = justTiltY + i * pxPerDegree;
            // Only display the scale within the inner face.
            if ((ypos < (innerBoundingBox.top + mTextHeight)) ||
                    (ypos > innerBoundingBox.bottom - mTextHeight)) continue;

            // Draw a line and the tilt angle for each scale increment.
            canvas.drawLine(startX, (float) ypos, endX, (float) ypos, mMarkerPaint);
            int displayPos = (int) (tiltDegree - i);
            String displayString = String.valueOf(displayPos);
            float stringSizeWidth = mTextPaint.measureText(displayString);
            canvas.drawText(displayString,
                    (int)(center.x-stringSizeWidth/2), (int)(ypos)+1,
                    mTextPaint);
        }

        // Now draw a thicker line at the earth/sky interface. Change the stroke thickness of the markerPaint object
        // before drawing the line (then set it back to the previous value).
        mMarkerPaint.setStrokeWidth(2);
        canvas.drawLine(center.x - radius / 2,
                (float)justTiltY, center.x + radius / 2, (float)justTiltY, mMarkerPaint);
        mMarkerPaint.setStrokeWidth(1);

        // To make it easier to read the exact roll, you should draw an arrow and display a text string
        // that shows the exact value.
        // Create a new Path, and use the moveTo / lineTo methods to construct an open arrow that points straight up.
        // Draw the path and a text string that shows the current roll.
        // Draw the arrow
        Path rollArrow = new Path();
        rollArrow.moveTo(center.x - 3, (int)innerBoundingBox.top + 14);
        rollArrow.lineTo(center.x, (int)innerBoundingBox.top + 10);
        rollArrow.moveTo(center.x + 3, innerBoundingBox.top + 14);
        rollArrow.lineTo(center.x, innerBoundingBox.top + 10);
        canvas.drawPath(rollArrow, mMarkerPaint);
        // Draw the string
        String rollText = String.valueOf(rollDegree);
        double rollTextWidth = mTextPaint.measureText(rollText);
        canvas.drawText(rollText,
                (float)(center.x - rollTextWidth / 2), innerBoundingBox.top + mTextHeight + 2, mTextPaint);

        // Spin the canvas back to upright so that you can draw the rest of the face markings.
        canvas.restore();

        // Draw the roll dial markings by rotating the canvas 10 degrees at a time to draw either a mark or a value.
        // When you’ve completed the face, restore the canvas to its upright position.
        canvas.save();
        canvas.rotate(180, center.x, center.y);
        for (int i = -180; i < 180; i += 10) {
            // Show a numeric value every 30 degrees
            if (i % 30 == 0) {
                String rollString = String.valueOf(i * -1);
                float rollStringWidth = mTextPaint.measureText(rollString);
                PointF rollStringCenter = new PointF(
                        center.x - rollStringWidth / 2, innerBoundingBox.top + 1 + mTextHeight);
                canvas.drawText(rollString,
                        rollStringCenter.x, rollStringCenter.y,
                        mTextPaint);
            }
            // Otherwise draw a marker line
            else {
                canvas.drawLine(center.x, (int)innerBoundingBox.top,
                                center.x, (int)innerBoundingBox.top + 5,
                                mMarkerPaint);
            }
            canvas.rotate(10, center.x, center.y);
        }
        canvas.restore();

        // Creating the face is drawing the heading markers around the outside edge
        canvas.save();
        canvas.rotate(-1 * (mBearing), px, py);
        // Should this be a double?
        float increment = 22.5f;
        for (float i = 0; i < 360; i += increment) {
            CompassDirection cd = CompassDirection.values()[(int)(i / 22.5)];
            String headString = cd.toString();
            float headStringWidth = mTextPaint.measureText(headString);
            PointF headStringCenter = new PointF(center.x - headStringWidth / 2, boundingBox.top + 1 + mTextHeight);
            if (i % increment == 0)
                canvas.drawText(headString, headStringCenter.x, headStringCenter.y, mTextPaint);
            else
                canvas.drawLine(center.x, (int)boundingBox.top, center.x, (int)boundingBox.top + 3, mMarkerPaint);
            canvas.rotate((int)increment, center.x, center.y);
        }
        canvas.restore();

        // Adding a “glass dome” over the top to give the illusion of a watch face.
        // Using the radial gradient array we constructed earlier, create a new Shader and Paint object.
        // Use them to draw a circle over the inner face that makes it look like it’s covered in glass.
        RadialGradient glassShader = new RadialGradient(px, py,
                (int)innerRadius,
                mGlassGradientColors,
                mGlassGradientPositions,
                Shader.TileMode.CLAMP);

        Paint glassPaint = new Paint();
        glassPaint.setShader(glassShader);
        canvas.drawOval(innerBoundingBox, glassPaint);

        // All that’s left is to draw two more circles as clean borders for the inner and outer face boundaries.
        // Then restore the canvas to upright, and finish the onDraw method.
        // Draw the outer ring
        canvas.drawOval(boundingBox, mCirclePaint);

        // Draw the inner ring
        mCirclePaint.setStrokeWidth(2);
        canvas.drawOval(innerBoundingBox, mCirclePaint);

        canvas.restore();
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
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
    }

    public float getRoll() {
        return mRoll;
    }

    public void setRoll(float roll) {
        mRoll = roll;
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
    }
}
