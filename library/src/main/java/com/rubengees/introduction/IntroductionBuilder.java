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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.v4.view.ViewPager;

import com.rubengees.introduction.entity.Slide;
import com.rubengees.introduction.interfaces.IndicatorManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A Builder class with method chaining to create the IntroductionActivity.
 *
 * @author Ruben Gees
 */
public class IntroductionBuilder {

    public static final int INTRODUCTION_REQUEST_CODE = 32142;

    public static final int STYLE_FULLSCREEN = 0;
    public static final int STYLE_TRANSLUCENT = 1;

    public static final int ORIENTATION_PORTRAIT = 0;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_BOTH = 2;

    private Activity context;
    private ArrayList<Slide> slides;
    private Integer style;
    private Boolean showPreviousButton;
    private Boolean showIndicator;
    private Integer orientation;

    /**
     * The Activity the Introduction should start from. Any Callbacks are delivered to that one.
     *
     * @param context The Activity.
     */
    public IntroductionBuilder(@NonNull Activity context) {
        this.context = context;
        this.slides = new ArrayList<>();
    }

    /**
     * The Slides to display.
     *
     * @param slides A List of Slides.
     * @return The current instance.
     * @throws IllegalArgumentException If an empty list was passed.
     */
    public IntroductionBuilder withSlides(@NonNull @Size(min = 1) List<Slide> slides) {
        if (slides.size() < 1) {
            throw new IllegalArgumentException("You must add at least one slide.");
        }

        this.slides.addAll(new ArrayList<>(slides));

        return this;
    }

    /**
     * Sets the Style of the Activity. Currently are Translucent and Fullscreen available.
     * If this Method was not called, Translucent is selected as default.
     *
     * @param style The style.
     * @return The current instance.
     * @throws IllegalArgumentException If the provided int was not one of the available styles.
     */
    @SuppressWarnings("unused")
    public IntroductionBuilder withStyle(@IntRange(from = 0, to = 1) int style) {
        if (style == 0 || style == 1) {
            this.style = style;
        } else {
            throw new IllegalArgumentException("You must specify one of the available styles.");
        }

        return this;
    }

    /**
     * Assignes a custom IndicatorManager. If this Method is not called, the
     * {@link com.rubengees.introduction.common.DotIndicatorManager} will be used by default.
     *
     * @param manager The IndicatorManager.
     * @return The current instance.
     */
    public IntroductionBuilder withIndicatorManager(@NonNull IndicatorManager manager) {
        IntroductionConfiguration.getInstance().setIndicatorManager(manager);

        return this;
    }

    /**
     * Enables or disables the button to go back a Slide. This is just for design, it is still
     * possible to go back with swiping. If this Method is not called, the button will be enabled
     * by default.
     *
     * @param enabled True if the button should enabled, false otherwise.
     * @return The current instance.
     */
    public IntroductionBuilder withPreviousButtonEnabled(boolean enabled) {
        this.showPreviousButton = enabled;

        return this;
    }

    /**
     * Enables or disables the indicator. This method has a higher priority than
     * {@link #withIndicatorManager(IndicatorManager)}, so there will be no indicator, even if you
     * provided your own Manager.
     *
     * @param enabled True if indicators should disabled.
     * @return The current instance.
     */
    public IntroductionBuilder withIndicatorEnabled(boolean enabled) {
        this.showIndicator = enabled;

        return this;
    }

    /**
     * Forces an orientation for the Activity. Available are portrait, landscape and both.
     * If this Method is not called, there will be no forced orientation.
     *
     * @param orientation The orientation.
     * @return The current instance.
     * @throws IllegalArgumentException If the passed int is not one of the available orientations.
     */
    public IntroductionBuilder withForcedOrientation(@IntRange(from = 0, to = 2) int orientation) {
        if (orientation < 0 || orientation > 2) {
            throw new IllegalArgumentException("You must specify one of the available orientations");
        } else {
            this.orientation = orientation;
        }

        return this;
    }

    /**
     * Assignes a
     * {@link com.rubengees.introduction.IntroductionConfiguration.OnSlideListener} to the
     * Activity.
     *
     * @param onSlideChangedListener The listener.
     * @return The current instance.
     */
    public IntroductionBuilder withOnSlideListener(@NonNull IntroductionConfiguration.OnSlideListener
                                                                  onSlideChangedListener) {
        IntroductionConfiguration.getInstance().setOnSlideChangedListener(onSlideChangedListener);

        return this;
    }

    /**
     * Applies a PageTransformer to the Activity. You can
     * find some implementations in the commons package.
     *
     * @param pageTransformer The transformer.
     * @return The current instance.
     */
    public IntroductionBuilder withPageTransformer(@NonNull ViewPager.PageTransformer pageTransformer) {
        IntroductionConfiguration.getInstance().setPageTransformer(pageTransformer);

        return this;
    }

    private void check() {
        if (style == null) {
            style = STYLE_TRANSLUCENT;
        }

        if (orientation == null) {
            orientation = ORIENTATION_BOTH;
        }

        if (showPreviousButton == null) {
            showPreviousButton = true;
        }

        if (showIndicator == null) {
            showIndicator = true;
        }
    }

    /**
     * This Method finally start the Activity and displays the provided data.
     */
    public void introduceMyself() {
        check();

        Intent intent = new Intent(context, IntroductionActivity.class);
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("introduction_slides", slides);
        bundle.putInt("introduction_style", style);
        bundle.putInt("introduction_orientation", orientation);
        bundle.putBoolean("introduction_show_previous_button", showPreviousButton);
        bundle.putBoolean("introduction_show_indicator", showIndicator);

        intent.putExtras(bundle);
        context.startActivityForResult(intent, INTRODUCTION_REQUEST_CODE);
    }
}
