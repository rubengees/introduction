package com.rubengees.introduction.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Listener for {@link com.rubengees.introduction.Slide} events.
 *
 * @author Ruben Gees
 */
public abstract class OnSlideListener {

    public void onSlideChanged(int from, int to) {
    }

    public void onSlideInit(int position, @Nullable TextView title, @NonNull ImageView image,
                            @Nullable TextView description) {
    }
}
