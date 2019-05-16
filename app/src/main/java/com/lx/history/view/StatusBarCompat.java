package com.lx.history.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;

public class StatusBarCompat {
    private static final int INVALID_VAL = -1;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void compat(Activity activity, int statusColor) {

        if (statusColor != INVALID_VAL) {
            activity.getWindow().setStatusBarColor(statusColor);
        }

    }

    public static void compat(Activity activity) {
        compat(activity, INVALID_VAL);
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
