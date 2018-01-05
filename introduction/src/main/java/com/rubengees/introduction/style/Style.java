package com.rubengees.introduction.style;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import java.io.Serializable;

/**
 * Class for managing the Style of the Introduction.
 *
 * @author Ruben Gees
 */
public abstract class Style implements Serializable {

    /**
     * Apply the basic style here. This is called before onCreate, so you can set a theme here or
     * set some flags.
     *
     * @param activity The activity to apply the style on.
     */
    public abstract void applyStyle(@NonNull Activity activity);

    /**
     * Apply specific parameters to the view hierarchy containing the ViewPager for the slides.
     *
     * @param activity The containing Activity.
     * @param root     The root of the Activity.
     */
    @SuppressWarnings({"unused", "EmptyMethod"})
    public void applyStyleOnActivityView(@NonNull Activity activity, @NonNull View root) {
        // To be implemented by the user.
    }

    /**
     * Apply specific parameters on a fragment showing a slide.
     *
     * @param fragment The fragment containing the slide.
     * @param root     The root view of the fragment.
     */
    @SuppressWarnings({"unused", "EmptyMethod"})
    public void applyStyleOnFragmentView(@NonNull Fragment fragment, @NonNull View root) {
        // To be implemented by the user.
    }
}
