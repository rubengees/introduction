package com.rubengees.introduction.util;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.Button;

import com.rubengees.introduction.R;
import com.rubengees.introduction.interfaces.ViewPagerProcessor;

/**
 * Manages the Buttons of the Activity.
 *
 * @author Ruben Gees
 */
public class ButtonManager implements ViewPagerProcessor {

    private final int slideAmount;
    private final AppCompatImageButton previous;
    private final AppCompatImageButton next;
    private final Button skip;
    private final boolean showPreviousButton;
    private final boolean showSkipButton;

    /**
     * @param previous           The Button to navigate one slide back.
     * @param next               The Button to navigate one slide forth. Also used to show the done icon, if the
     *                           current Slide is the last Slide.
     * @param skip               The Button to skip the introduction.
     * @param showPreviousButton If the Button to navigate back should not be shown, pass false.
     * @param showSkipButton     If the Button for skipping should not be shown, pass false.
     * @param slideAmount        The amount of Slides.
     */
    public ButtonManager(@NonNull AppCompatImageButton previous, @NonNull AppCompatImageButton next,
                         @NonNull Button skip, boolean showPreviousButton, boolean showSkipButton, int slideAmount) {
        this.previous = previous;
        this.next = next;
        this.skip = skip;
        this.showPreviousButton = showPreviousButton;
        this.showSkipButton = showSkipButton;
        this.slideAmount = slideAmount;

        init();
    }

    private void init() {
        if (showPreviousButton) {
            previous.setVisibility(View.VISIBLE);
            previous.setImageResource(previousIcon());
        } else {
            previous.setVisibility(View.INVISIBLE);
        }

        if (showSkipButton) {
            skip.setVisibility(View.VISIBLE);
        } else {
            skip.setVisibility(View.GONE);
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
            next.setImageResource(nextIcon());

            if (showSkipButton) {
                skip.setVisibility(View.VISIBLE);
            }
        } else {
            next.setImageResource(R.drawable.introduction_ic_done);

            if (showSkipButton) {
                skip.setVisibility(View.GONE);
            }
        }
    }

    @DrawableRes
    private int nextIcon() {
        if (OrientationUtils.isRTL(next.getContext())) {
            return R.drawable.introduction_ic_arrow_previous;
        } else {
            return R.drawable.introduction_ic_arrow_next;
        }
    }

    @DrawableRes
    private int previousIcon() {
        if (OrientationUtils.isRTL(next.getContext())) {
            return R.drawable.introduction_ic_arrow_next;
        } else {
            return R.drawable.introduction_ic_arrow_previous;
        }
    }
}
