package com.rubengees.introduction.common;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rubengees.introduction.R;
import com.rubengees.introduction.interfaces.IndicatorManager;

/**
 * An implementation of the IndicatorManager.
 * It shows the current and total pages with small dots.
 *
 * @author Ruben Gees
 */
public class DotIndicatorManager extends IndicatorManager {

    private ViewGroup root;

    @NonNull
    @Override
    public View init(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int slideAmount) {
        root = (ViewGroup) inflater.inflate(R.layout.introduction_indicator_layout_dot, parent, false);

        for (int i = 0; i < slideAmount; i++) {
            ImageView dot = (ImageView) inflater.inflate(R.layout.introduction_indicator_item_dot, root, false);

            dot.setTag(i);
            dot.setOnClickListener(view -> {
                if (onUserSelectionListener != null) {
                    onUserSelectionListener.onSelection((Integer) view.getTag());
                }
            });

            root.addView(dot);
        }

        return root;
    }

    @Override
    public void select(int position) {
        for (int i = 0; i < root.getChildCount(); i++) {
            ImageView dot = (ImageView) root.getChildAt(i);

            if (i == position) {
                dot.setImageResource(R.drawable.introduction_dot_white);
            } else {
                dot.setImageResource(R.drawable.introduction_dot_transparent_grey);
            }
        }
    }
}
