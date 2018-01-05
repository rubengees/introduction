package com.rubengees.introduction.interfaces;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Manager which shows some type of position indicator.
 *
 * @author Ruben Gees
 */
public abstract class IndicatorManager implements ViewPagerProcessor {

    protected OnUserSelectionListener onUserSelectionListener;

    /**
     * Method which must be implemented, where the layout is created. Don't add the layout to the
     * parent here, this will handled later.
     *
     * @param inflater    The LayoutInflater
     * @param parent      The parent for the View.
     * @param slideAmount The amount of slides
     * @return The View, containing the indicator.
     */
    @NonNull
    public abstract View init(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent,
                              @IntRange(from = 0) int slideAmount);

    /**
     * Registers a listener.
     *
     * @param listener The listener.
     */
    public void setListener(@Nullable OnUserSelectionListener listener) {
        this.onUserSelectionListener = listener;
    }

    /**
     * Interface for selections by the user e.g. clicking a dot.
     */
    public interface OnUserSelectionListener {

        /**
         * Called when the user selected another page. This method is <strong>not</strong> invoked
         * if {@link ViewPagerProcessor#select(int)} is called.
         *
         * @param position The position of the new page.
         */
        void onSelection(int position);
    }
}
