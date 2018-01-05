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
