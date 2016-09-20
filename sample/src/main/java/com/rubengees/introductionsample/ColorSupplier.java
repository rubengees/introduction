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

    private static final String ILLEAGAL_ARGUMENT_POSITION_MESSAGE = "The position cannot be " +
            "larger than the amount of colors";
    private static Integer[] colors = new Integer[]{R.color.green, R.color.indigo, R.color.orange};

    private ColorSupplier() {
    }

    @ColorInt
    public static int getColorResForPosition(int position) {
        if (position > colors.length) {
            throw new IllegalArgumentException(ILLEAGAL_ARGUMENT_POSITION_MESSAGE);
        }

        return colors[position];
    }

    @ColorInt
    public static int getColorForPosition(@NonNull Context context, int position) {
        if (position > colors.length) {
            throw new IllegalArgumentException(ILLEAGAL_ARGUMENT_POSITION_MESSAGE);
        }

        return ContextCompat.getColor(context, colors[position]);
    }

}
