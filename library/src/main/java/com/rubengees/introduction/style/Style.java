package com.rubengees.introduction.style;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

import java.io.Serializable;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public abstract class Style implements Serializable {

    public abstract void applyStyle(@NonNull Activity activity);

    public void applyStyleOnActivityView(@NonNull Activity activity, @NonNull ViewGroup root) {

    }

    public void applyStyleOnFragmentView(@NonNull Fragment fragment, @NonNull ViewGroup root) {

    }

}
