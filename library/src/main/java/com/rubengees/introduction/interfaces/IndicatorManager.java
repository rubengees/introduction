package com.rubengees.introduction.interfaces;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Manager which shows some type of position indicator.
 *
 * @author Ruben Gees
 */
public abstract class IndicatorManager implements ViewPagerProcessor {

    /**
     * Method which must be implemented, where the layout is created. Don't add the layout to the
     * parent here, this will handled later.
     *
     * @param inflater    The LayoutInflater
     * @param parent      The parent for the View.
     * @param slideAmount The amount of slides
     * @return The View, containing the indicator.
     */
    public abstract View init(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @IntRange(from = 0) int slideAmount);

    /**
     * Method which handles the selection of a new Slide.
     * @param position The new position.
     */
    @Override
    public abstract void select(int position);
}
