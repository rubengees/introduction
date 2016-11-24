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

package com.rubengees.introduction;

import android.graphics.Typeface;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubengees.introduction.interfaces.IndicatorManager;

/**
 * A helper-singleton to provide some data to the {@link IntroductionActivity}.
 * Don't use this Class or even modify the fields in it.
 *
 * @author Ruben Gees
 */
public final class IntroductionConfiguration {
    private static IntroductionConfiguration INSTANCE;

    private OnSlideListener onSlideListener;
    private ViewPager.PageTransformer pageTransformer;
    private IndicatorManager indicatorManager;
    private Typeface typeface;

    private IntroductionConfiguration() {

    }

    @NonNull
    synchronized static IntroductionConfiguration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IntroductionConfiguration();
        }

        return INSTANCE;
    }

    synchronized static void destroy() {
        if (INSTANCE != null) {
            INSTANCE.onSlideListener = null;
            INSTANCE.pageTransformer = null;
            INSTANCE.indicatorManager = null;

            INSTANCE = null;
        }
    }

    void setOnSlideChangedListener(@Nullable OnSlideListener onSlideChangedListener) {
        this.onSlideListener = onSlideChangedListener;
    }

    @Nullable
    ViewPager.PageTransformer getPageTransformer() {
        return pageTransformer;
    }

    void setPageTransformer(@Nullable ViewPager.PageTransformer pageTransformer) {
        this.pageTransformer = pageTransformer;
    }

    @Nullable
    IndicatorManager getIndicatorManager() {
        return indicatorManager;
    }

    void setIndicatorManager(@Nullable IndicatorManager indicatorManager) {
        this.indicatorManager = indicatorManager;
    }

    void callOnSlideChanged(@IntRange(from = 0) int from, @IntRange(from = 0) int to) {
        if (onSlideListener != null) {
            onSlideListener.onSlideChanged(from, to);
        }
    }

    void callOnSlideInit(@IntRange(from = 0) int position, @NonNull TextView title,
                         @NonNull ImageView image, @NonNull TextView description) {
        if (onSlideListener != null) {
            onSlideListener.onSlideInit(position, title, image, description);
        }
    }

    @Nullable
    Typeface getTypeface() {
        return typeface;
    }

    void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public static class OnSlideListener {

        protected OnSlideListener() {
        }

        protected void onSlideChanged(int from, int to) {
            // To be implemented by the user
        }

        protected void onSlideInit(int position, @NonNull TextView title, @NonNull ImageView image,
                                   @NonNull TextView description) {
            // To be implemented by the user
        }
    }
}
