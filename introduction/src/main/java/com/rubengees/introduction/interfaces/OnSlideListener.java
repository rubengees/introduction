package com.rubengees.introduction.interfaces;

import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class OnSlideListener {

    public void onSlideChanged(int from, int to) {
        // To be optionally implemented by the user
    }

    public void onSlideInit(int position, @NonNull TextView title, @NonNull ImageView image,
                            @NonNull TextView description) {
        // To be optionally implemented by the user
    }
}
