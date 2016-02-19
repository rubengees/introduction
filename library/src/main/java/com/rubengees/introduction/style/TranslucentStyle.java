package com.rubengees.introduction.style;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public class TranslucentStyle extends Style {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void applyStyle(@NonNull Activity activity) {
        Window w = activity.getWindow();

        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
}
