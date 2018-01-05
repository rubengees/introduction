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

package com.rubengees.introductionsample.transformer;

import android.graphics.Color;
import android.view.View;

import com.rubengees.introductionsample.ColorSupplier;

/**
 * An Animator for the ViewPager.
 *
 * @author Ruben Gees
 */
public class ColorPageTransformer extends BasePageTransformer {

    private static int blendColors(int color1, int color2, float ratio) {
        final float inverseRation = 1f - ratio;
        float r = Color.red(color1) * ratio + Color.red(color2) * inverseRation;
        float g = Color.green(color1) * ratio + Color.green(color2) * inverseRation;
        float b = Color.blue(color1) * ratio + Color.blue(color2) * inverseRation;

        return Color.rgb((int) r, (int) g, (int) b);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public void transformPage(final View page, final int pageIndex, final float position) {
        if (inRange(position)) {
            if (isRightPage(position)) {
                final int leftIndex = pageIndex - 1;
                final int rightIndex = pageIndex;

                final int leftColor = ColorSupplier.getColorForPosition(page.getContext(), leftIndex);
                final int rightColor = ColorSupplier.getColorForPosition(page.getContext(), rightIndex);

                final int composedColor = blendColors(leftColor, rightColor, position);
                page.setBackgroundColor(composedColor);
            } else if (isLeftPage(position)) {
                final int leftIndex = pageIndex;
                final int rightIndex = leftIndex + 1;

                final int leftColor = ColorSupplier.getColorForPosition(page.getContext(), leftIndex);
                final int rightColor = ColorSupplier.getColorForPosition(page.getContext(), rightIndex);

                final int composedColor = blendColors(leftColor, rightColor, 1 - Math.abs(position));
                page.setBackgroundColor(composedColor);
            } else {
                page.setBackgroundColor(ColorSupplier.getColorForPosition(page.getContext(), pageIndex));
            }
        } else {
            page.setBackgroundColor(ColorSupplier.getColorForPosition(page.getContext(), pageIndex));
        }
    }
}
