package com.rubengees.introduction.interfaces;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

/**
 * The interface to define a custom View.
 * Note: You cannot use a anonymous class to implement this. See the CustomViewBuilderImpl in
 * the sample.
 */
public interface CustomViewBuilder extends Serializable {

    /**
     * Returns the desired view for this slide.
     *
     * @param inflater The inflater.
     * @param parent   The parent of the new view. Only for inflation purposes. Do not add your
     *                 View to this.
     * @return The new View.
     */
    @NonNull
    View buildView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
}
