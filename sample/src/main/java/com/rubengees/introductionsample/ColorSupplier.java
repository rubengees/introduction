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

package com.rubengees.introductionsample;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.rubengees.introductionsample.transformer.ColorPageTransformer;

/**
 * Class for retrieving colors, especially for the {@link ColorPageTransformer}. In your project
 * use this in the MainActivity to get the colors for your Introduction. This is not done here
 * to keep the sample simple.
 *
 * @author Ruben Gees
 */
public final class ColorSupplier {

    private static final Integer[] colors = new Integer[]{R.color.green, R.color.indigo, R.color.orange};
    private static final String ILLEGAL_ARGUMENT_POSITION_MESSAGE = "The position cannot be " +
            "larger than the amount of colors";

    private ColorSupplier() {
    }

    @ColorInt
    public static int getColorResForPosition(int position) {
        if (position > colors.length) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_POSITION_MESSAGE);
        }

        return colors[position];
    }

    @ColorInt
    public static int getColorForPosition(@NonNull Context context, int position) {
        if (position > colors.length) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_POSITION_MESSAGE);
        }

        return ContextCompat.getColor(context, colors[position]);
    }
}
