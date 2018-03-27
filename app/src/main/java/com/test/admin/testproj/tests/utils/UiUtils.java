package com.test.admin.testproj.tests.utils;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

public class UiUtils {

    public static final int SHORT_ANIM_TIME = 200;
    public static final int MEDIUM_ANIM_TIME = 400;
    public static final int LONG_ANIM_TIME = 600;

    public static void setText(String text, EditText et) {
        et.setText(text);
        et.setSelection(text.length());
    }

    public static Drawable setTint(Drawable d, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(d);
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }

    public static boolean hitTest(View v, int x, int y) {
        final int tx = (int) (ViewCompat.getTranslationX(v) + 0.5f);
        final int ty = (int) (ViewCompat.getTranslationY(v) + 0.5f);
        final int left = v.getLeft() + tx;
        final int right = v.getRight() + tx;
        final int top = v.getTop() + ty;
        final int bottom = v.getBottom() + ty;

        return (x >= left) && (x <= right) && (y >= top) && (y <= bottom);
    }

    public static void disableTitle(Dialog dialog) {
        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
    }

    /*public static void logout(Context ctx, @NonNull ApiClient apiClient, @NonNull Session session) {
        session.logout(apiClient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loggedOut -> {
                    if (loggedOut && ctx != null) {
                        Intent intent = new Intent(ctx, SignInActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(intent);
                    }
                }, throwable -> {Logger.e("UiUtils", "logout, onError()", throwable);});
    }

    public static void logout(@NonNull Activity activity, @NonNull Session session) {
        session.logout();
        Intent intent = new Intent(activity, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }*/

    public static void clearFields(TextView... views) {
        for (TextView view : views) {
            view.setText("");
        }
    }

    public static void scrollToView(NestedScrollView scroller, View view) {
        int[] screenSize = getScreenSize(view.getContext());
        int y = 0;
        int halfScreenHeight = screenSize[1] / 2;

        if (view.getTop() > halfScreenHeight) {
            y = view.getTop() - halfScreenHeight;
        }
        final int finalY = y;
        scroller.post(() -> scroller.smoothScrollTo(0, finalY));
    }

    public static float getFloat(EditText editText) {
        try {
            return Float.valueOf(getText(editText));
        } catch (Exception e) {
            return 0;
        }
    }

    public static Float getFloatOrNull(EditText editText) {
        float number = getFloat(editText);
        return number > 0 ? number : null;
    }

    public static int getInt(EditText editText) {
        try {
            return Integer.valueOf(getText(editText));
        } catch (Exception e) {
            return 0;
        }
    }

    public static Integer getIntOrNull(EditText editText) {
        int number = getInt(editText);
        return number > 0 ? number : null;
    }


    public static String getText(TextView tv) {
        return tv.getText().toString().trim();
    }

    public static  String getTextOrNull(EditText editText) {
        String text = getText(editText);
        return TextUtils.isEmpty(text) ? null : text;
    }

    public static ListPopupWindow createAndShowListPopupWindow(View anchor, ListAdapter adapter, AdapterView.OnItemClickListener l) {
        ListPopupWindow popup = new ListPopupWindow(anchor.getContext());
        popup.setAnchorView(anchor);
        popup.setDropDownGravity(Gravity.BOTTOM);
        popup.setInputMethodMode(ListPopupWindow.INPUT_METHOD_NEEDED);
        popup.setAdapter(adapter);
        popup.setOnItemClickListener((parent, view, position, id) -> {
            popup.dismiss();
            if (l != null) {
                l.onItemClick(parent, view, position, id);
            }
        });
        popup.show();
        return popup;
    }

    public static ListPopupWindow createListPopupWindow(View anchor, ListAdapter adapter, AdapterView.OnItemClickListener l) {
        ListPopupWindow popup = new ListPopupWindow(anchor.getContext());
        popup.setAnchorView(anchor);
        popup.setWidth(anchor.getWidth());
        popup.setDropDownGravity(Gravity.BOTTOM);
        popup.setInputMethodMode(ListPopupWindow.INPUT_METHOD_NOT_NEEDED);
        popup.setAdapter(adapter);
        popup.setOnItemClickListener((parent, view, position, id) -> {
            popup.dismiss();
            if (l != null) {
                l.onItemClick(parent, view, position, id);
            }
        });
        return popup;
    }

    /**
     * Shows the progress UI and hides the content form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static void showProgress(final boolean show, View contentForm, View progress) {
        int shortAnimTime = progress.getResources().getInteger(android.R.integer.config_shortAnimTime);

        contentForm.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                contentForm.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progress.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progress.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    public static int[] getScreenSize(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return new int[] {size.x, size.y};
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(int dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

    public static void showKeyboard(final View view) {
        view.postDelayed(() -> {
            InputMethodManager imm =
                    (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            view.requestFocus();
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }, 100);
    }

    public static void showDialogFragmentAllowingStateLose(DialogFragment dialog, FragmentManager manager) {
        try {
            dialog.show(manager, dialog.getClass().getName());
        } catch (IllegalStateException e) {
        }
    }
}
