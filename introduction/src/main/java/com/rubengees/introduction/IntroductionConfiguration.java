package com.rubengees.introduction;

import android.graphics.Typeface;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubengees.introduction.interfaces.IndicatorManager;
import com.rubengees.introduction.interfaces.OnSlideListener;

/**
 * A helper-singleton to provide some data to the {@link IntroductionActivity}.
 * Don't use this Class or even modify the fields in it.
 *
 * @author Ruben Gees
 */
final class IntroductionConfiguration {

    private static IntroductionConfiguration INSTANCE;

    private OnSlideListener onSlideListener;
    private ViewPager.PageTransformer pageTransformer;
    private IndicatorManager indicatorManager;
    private Typeface titleTypeface;
    private Typeface descriptionTypeface;

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
    Typeface getTitleTypeface() {
        return titleTypeface;
    }

    void setTitleTypeface(Typeface typeface) {
        this.titleTypeface = typeface;
    }

    @Nullable
    Typeface getDescriptionTypeface() {
        return descriptionTypeface;
    }

    void setDescriptionTypeface(Typeface typeface) {
        this.descriptionTypeface = typeface;
    }
}
