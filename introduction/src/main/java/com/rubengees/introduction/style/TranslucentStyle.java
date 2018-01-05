package com.rubengees.introduction.style;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Window;

import static android.view.WindowManager.LayoutParams;

/**
 * Style for showing the {@link com.rubengees.introduction.IntroductionActivity} in translucent mode.
 *
 * @author Ruben Gees
 */
public class TranslucentStyle extends Style {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void applyStyle(@NonNull Activity activity) {
        Window w = activity.getWindow();

        w.setFlags(LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        w.setFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS, LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
}
