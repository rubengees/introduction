package com.rubengees.introductionsample;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rubengees.introduction.Slide;

/**
 * An implementation of the CustomViewBuilder to return a very basic view.
 * This class cannot be anonymous because Java.
 *
 * @author Ruben Gees
 */
public class CustomViewBuilderImpl2 implements Slide.CustomViewBuilder {

    @NonNull
    @Override
    public View buildView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return inflater.inflate(R.layout.layout_custom_2, parent, false);
    }
}
