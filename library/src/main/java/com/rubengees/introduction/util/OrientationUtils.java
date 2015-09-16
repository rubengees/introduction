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
    @SuppressWarnings("unused")
    public static void unlockOrientation(@NonNull Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

}
