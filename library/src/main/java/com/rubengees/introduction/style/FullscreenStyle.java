package com.rubengees.introduction.style;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.WindowManager;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public class FullscreenStyle extends Style {
    @Override
    public void applyStyle(@NonNull Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
