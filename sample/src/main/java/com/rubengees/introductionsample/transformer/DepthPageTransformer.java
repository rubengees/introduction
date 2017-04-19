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
 *
 *   Taken from: <a href="http://developer.android.com/training/animation/screen-slide.html">Android Developers</a>
 */

package com.rubengees.introductionsample.transformer;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/**
 * An Animator for the ViewPager.
 *
 * @author Ruben Gees
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DepthPageTransformer extends BasePageTransformer {
    private static final float MIN_SCALE = 0.75f;

    @Override
    protected void transformPage(View page, int pageIndex, float position) {
        int pageWidth = page.getWidth();

        if (inRange(position)) {
            if (isLeftPage(position)) {
                // Use the default slide transition when moving to the left page
                page.setAlpha(1);
                page.setTranslationX(0);
                page.setScaleX(1);
                page.setScaleY(1);
            } else {
                // Fade the page out.
                page.setAlpha(1 - position);

                // Counteract the default slide transition
                page.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            }
        } else {
            page.setAlpha(0);
        }
    }
}
