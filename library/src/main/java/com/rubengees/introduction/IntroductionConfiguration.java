package com.rubengees.introduction;

import android.support.v4.view.ViewPager;

import com.rubengees.introduction.interfaces.IndicatorManager;

/**
 * A helper-singleton to provide some data to the {@link IntroductionActivity}.
 * Don't use this Class or even modify the fields in it.
 *
 * @author Ruben Gees
 */
public class IntroductionConfiguration {
    private static IntroductionConfiguration INSTANCE;

    private OnSlideChangedListener onSlideChangedListener;
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

    void setOnSlideChangedListener(OnSlideChangedListener onSlideChangedListener) {
        this.onSlideChangedListener = onSlideChangedListener;
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
        if (onSlideChangedListener != null) {
            onSlideChangedListener.onSlideChanged(from, to);
        }
    }

    void clear() {
        onSlideChangedListener = null;
        pageTransformer = null;
        indicatorManager = null;

        INSTANCE = null;
    }

    public interface OnSlideChangedListener {
        void onSlideChanged(int from, int to);
    }
}
