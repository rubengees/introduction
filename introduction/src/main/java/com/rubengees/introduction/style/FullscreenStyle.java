package com.rubengees.introduction.style;

import android.app.Activity;
import android.support.annotation.NonNull;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

/**
 * Style for showing the {@link com.rubengees.introduction.IntroductionActivity} in fullscreen.
 *
 * @author Ruben Gees
 */
public class FullscreenStyle extends Style {

    @Override
    public void applyStyle(@NonNull Activity activity) {
        activity.getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
    }
}
