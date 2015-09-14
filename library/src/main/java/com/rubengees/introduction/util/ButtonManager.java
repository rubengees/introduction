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

package com.rubengees.introduction.util;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;

import com.rubengees.introduction.R;
import com.rubengees.introduction.interfaces.ViewPagerProcessor;

/**
 * Manages the Buttons of the Activity.
 *
 * @author Ruben Gees
 */
public class ButtonManager implements ViewPagerProcessor {
    private final int slideAmount;
    private ImageButton previous;
    private ImageButton next;
    private boolean showPreviousButton;

    /**
     * @param previous           The Button to navigate one slide back.
     * @param next               The Button to navigate on slide forth. Also used to show the done icon, if the
     *                           current Slide is the last Slide.
     * @param showPreviousButton If the Button to navigate back should not be shown, pass false.
     * @param slideAmount        The amount of Slides.
     */
    public ButtonManager(@NonNull ImageButton previous, @NonNull ImageButton next, boolean showPreviousButton, int slideAmount) {
        this.previous = previous;
        this.next = next;
        this.showPreviousButton = showPreviousButton;
        this.slideAmount = slideAmount;

        init();
    }

    private void init() {
        if (showPreviousButton) {
            previous.setVisibility(View.VISIBLE);
            previous.setImageResource(R.drawable.introduction_ic_arrow_previous);
        } else {
            previous.setVisibility(View.INVISIBLE);
        }

        next.setVisibility(View.VISIBLE);
    }

    /**
     * Method, which is called if a new Slide is selected.
     *
     * @param position The position of the new Slide.
     */
    public void select(int position) {
        if (showPreviousButton) {
            if (position > 0) {
                previous.setVisibility(View.VISIBLE);
            } else {
                previous.setVisibility(View.INVISIBLE);
            }
        }

        if (position < slideAmount - 1) {
            next.setImageResource(R.drawable.introduction_ic_arrow_next);
        } else {
            next.setImageResource(R.drawable.introduction_ic_done);
        }
    }
}
