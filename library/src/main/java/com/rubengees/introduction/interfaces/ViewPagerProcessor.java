package com.rubengees.introduction.interfaces;

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
    void select(int position);

}
