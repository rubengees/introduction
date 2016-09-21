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

    /**
     * Registers a listener.
     *
     * @param listener The listener.
     */
    public void setListener(@Nullable OnUserSelectionListener listener) {
        this.onUserSelectionListener = listener;
    }
}
