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

import android.support.v4.app.Fragment;
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
public class IntroductionConfiguration {
    private static IntroductionConfiguration INSTANCE;

    private OnSlideListener onSlideListener;
    private ViewPager.PageTransformer pageTransformer;
    private IndicatorManager indicatorManager;

    private IntroductionConfiguration() {

    }

    static IntroductionConfiguration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IntroductionConfiguration();
        }

        return INSTANCE;
    }

    void setOnSlideChangedListener(OnSlideListener onSlideChangedListener) {
        this.onSlideListener = onSlideChangedListener;
    }

    ViewPager.PageTransformer getPageTransformer() {
        return pageTransformer;
    }

    void setPageTransformer(ViewPager.PageTransformer pageTransformer) {
        this.pageTransformer = pageTransformer;
    }

    IndicatorManager getIndicatorManager() {
        return indicatorManager;
    }

    void setIndicatorManager(IndicatorManager indicatorManager) {
        this.indicatorManager = indicatorManager;
    }

    void callOnSlideChanged(int from, int to) {
        if (onSlideListener != null) {
            onSlideListener.onSlideChanged(from, to);
        }
    }

    void callOnSlideInit(Fragment context, int position, TextView title, ImageView image, TextView description) {
        if (onSlideListener != null) {
            onSlideListener.onSlideInit(context, position, title, image, description);
        }
    }

    void clear() {
        onSlideListener = null;
        pageTransformer = null;
        indicatorManager = null;

        INSTANCE = null;
    }

    public static abstract class OnSlideListener {
        protected void onSlideChanged(int from, int to) {

        }

        protected void onSlideInit(Fragment context, int position, TextView title, ImageView image, TextView description) {

        }
    }
}
