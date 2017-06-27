package com.test.admin.testproj.tests.animation;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.test.admin.testproj.R;

public class SvgActivity extends Activity {

/*    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //VectorDrawableCompat animatedVector = VectorDrawableCompat.create(this.getResources(), R.drawable.vector_arrow_left, null);

        ImageView imgArrow = (ImageView) findViewById(R.id.img_left_right_arrow);
        //imgArrow.setImageDrawable(animatedVector);

        Drawable drawable = imgArrow.getDrawable();
        Log.e("WWW", "WWW drawable=" + drawable);
        Log.e("WWW", "WWW drawable instanceof Animatable =" + (drawable instanceof Animatable));
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }
}
