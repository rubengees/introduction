package com.rubengees.introduction.interfaces;

import android.support.annotation.IntRange;

/**
 * Interface for all classes, which handle Slide changes.
 *
 * @author Ruben Gees
 */
public interface ViewPagerProcessor {

    /**
     * Method which is called if a new Slide is selected.
     *
     * @param position The position of the new Slide.
     */
    void select(@IntRange(from = 0) int position);
}
