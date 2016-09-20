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
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewPager;

import com.rubengees.introduction.entity.Slide;
import com.rubengees.introduction.interfaces.IndicatorManager;
import com.rubengees.introduction.style.Style;
import com.rubengees.introduction.style.TranslucentStyle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Builder class with method chaining to create the IntroductionActivity.
 *
 * @author Ruben Gees
 */
public class IntroductionBuilder {

    public static final int INTRODUCTION_REQUEST_CODE = 32142;

    public static final int ORIENTATION_PORTRAIT = 0;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_BOTH = 2;

    static final String BUNDLE_SLIDES = "introduction_slides";
    static final String BUNDLE_STYLE = "introduction_style";
    static final String BUNDLE_ORIENTATION = "introduction_orientation";
    static final String BUNDLE_SHOW_PREVIOUS_BUTTON = "introduction_show_previous_button";
    static final String BUNDLE_SHOW_INDICATOR = "introduction_show_indicator";
    static final String BUNDLE_SKIP_STRING = "introduction_skip_string";
    static final String BUNDLE_SKIP_RESOURCE = "introduction_skip_resource";
    static final String BUNDLE_ALLOW_BACK_PRESS = "introduction_allow_back_press";

    private Activity context;
    private ArrayList<Slide> slides;
    private Style style;
    private Boolean showPreviousButton;
    private Boolean showIndicator;
    private String skipString;
    private Integer skipResource;
    private Boolean allowBackPress;

    @Orientation
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
    @NonNull
    public IntroductionBuilder withSlides(@NonNull @Size(min = 1) List<Slide> slides) {
        if (slides.size() < 1) {
            throw new IllegalArgumentException("You must add at least one slide.");
        }

        this.slides.addAll(new ArrayList<>(slides));

        return this;
    }

    /**
     * The Slides to display.
     *
     * @param slides A List of Slides.
     * @return The current instance.
     * @throws IllegalArgumentException If an empty list was passed.
     */
    @NonNull
    public IntroductionBuilder withSlides(Slide... slides) {
        return withSlides(Arrays.asList(slides));
    }

    /**
     * Sets the Style of the Activity. Currently are Translucent and Fullscreen available.
     * If this Method was not called, Translucent is selected as default.
     *
     * @param style The style.
     * @return The current instance.
     * @throws IllegalArgumentException If the provided int was not one of the available styles.
     */
    @NonNull
    public IntroductionBuilder withStyle(@NonNull Style style) {
        this.style = style;

        return this;
    }

    /**
     * Assigns a custom IndicatorManager. If this Method is not called, the
     * {@link com.rubengees.introduction.common.DotIndicatorManager} will be used by default.
     *
     * @param manager The IndicatorManager.
     * @return The current instance.
     */
    @NonNull
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
    @NonNull
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
    @NonNull
    public IntroductionBuilder withIndicatorEnabled(boolean enabled) {
        this.showIndicator = enabled;

        return this;
    }

    /**
     * Specifies whether to show a skip button or not.
     * If you specified a resource earlier it will be overridden.
     *
     * @param text The text to show.
     * @return The current instance.
     */
    @NonNull
    public IntroductionBuilder withSkipEnabled(@NonNull String text) {
        this.skipString = text;
        this.skipResource = null;

        return this;
    }

    /**
     * Specifies whether to show a skip button or not.
     * If you specified a text earlier it will be overridden.
     *
     * @param resource The resource to show.
     * @return The current instance.
     */
    @NonNull
    public IntroductionBuilder withSkipEnabled(@StringRes int resource) {
        this.skipResource = resource;
        this.skipString = null;

        return this;
    }

    /**
     * Sets if ít is allowed for the user to press the back button at the first slide to cancel the
     * introduction. The default value is false.
     *
     * @param allow If the back button cancels the introduction.
     * @return The current instance.
     */
    public IntroductionBuilder withAllowBackPress(boolean allow) {
        this.allowBackPress = allow;

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
    @NonNull
    public IntroductionBuilder withForcedOrientation(@Orientation int orientation) {
        this.orientation = orientation;

        return this;
    }

    /**
     * Assigns a
     * {@link com.rubengees.introduction.IntroductionConfiguration.OnSlideListener} to the
     * Activity.
     *
     * @param onSlideChangedListener The listener.
     * @return The current instance.
     */
    @NonNull
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
    @NonNull
    public IntroductionBuilder withPageTransformer(@NonNull ViewPager.PageTransformer
                                                           pageTransformer) {
        IntroductionConfiguration.getInstance().setPageTransformer(pageTransformer);

        return this;
    }

    private void check() {
        if (slides == null) {
            throw new RuntimeException("You need to add slides.");
        }

        if (style == null) {
            style = new TranslucentStyle();
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

        if (allowBackPress == null) {
            allowBackPress = false;
        }
    }

    /**
     * This Method finally start the Activity and displays the provided data.
     */
    public void introduceMyself() {
        check();

        Intent intent = new Intent(context, IntroductionActivity.class);
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList(BUNDLE_SLIDES, slides);
        bundle.putSerializable(BUNDLE_STYLE, style);
        bundle.putInt(BUNDLE_ORIENTATION, orientation);
        bundle.putBoolean(BUNDLE_SHOW_PREVIOUS_BUTTON, showPreviousButton);
        bundle.putBoolean(BUNDLE_SHOW_INDICATOR, showIndicator);
        bundle.putString(BUNDLE_SKIP_STRING, skipString);
        bundle.putBoolean(BUNDLE_ALLOW_BACK_PRESS, allowBackPress);

        if (skipResource != null) {
            bundle.putInt(BUNDLE_SKIP_RESOURCE, skipResource);
        }

        intent.putExtras(bundle);

        Activity parent = context.getParent();

        if (parent != null) {
            context = parent;
        }

        context.startActivityForResult(intent, INTRODUCTION_REQUEST_CODE);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ORIENTATION_PORTRAIT, ORIENTATION_LANDSCAPE, ORIENTATION_BOTH})
    public @interface Orientation {

    }
}
