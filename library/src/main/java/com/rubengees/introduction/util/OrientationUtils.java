package com.rubengees.introduction.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;

/**
 * Some utils to set different Orientations.
 *
 * @author Ruben Gees
 */
public class OrientationUtils {

    /**
     * Sets the orientation to landscape.
     *
     * @param activity The Activity
     */
    public static void setOrientationLandscape(@NonNull Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * Sets the orientation to portrait.
     * @param activity The Activity
     */
    public static void setOrientationPortrait(@NonNull Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * Let the Activity handle the Orientation itself.
     * @param activity The Activity
     */
    public static void unlockOrientation(@NonNull Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

}
