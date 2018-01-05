/*
 *   Copyright 2015 Ruben Gees
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.rubengees.introduction.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.View;

/**
 * Some utils to set different Orientations.
 *
 * @author Ruben Gees
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class OrientationUtils {

    private OrientationUtils() {
    }

    /**
     * Sets the orientation to landscape.
     *
     * @param activity The Activity.
     */
    public static void setOrientationLandscape(@NonNull Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * Sets the orientation to portrait.
     *
     * @param activity The Activity.
     */
    public static void setOrientationPortrait(@NonNull Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * Let the Activity handle the orientation itself.
     *
     * @param activity The Activity.
     */
    @SuppressWarnings("unused")
    public static void unlockOrientation(@NonNull Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    public static boolean isRTL(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            final int flag = context.getApplicationInfo().flags & ApplicationInfo.FLAG_SUPPORTS_RTL;
            final int layoutDirection = context.getResources().getConfiguration().getLayoutDirection();

            return flag == ApplicationInfo.FLAG_SUPPORTS_RTL && layoutDirection == View.LAYOUT_DIRECTION_RTL;
        } else {
            return false;
        }
    }
}
