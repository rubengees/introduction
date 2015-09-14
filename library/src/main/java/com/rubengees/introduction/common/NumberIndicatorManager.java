package com.rubengees.introduction.common;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rubengees.introduction.R;
import com.rubengees.introduction.interfaces.IndicatorManager;

/**
 * An implementation of the IndicatorManager.
 * It shows the current and total pages with text.
 *
 * @author Ruben Gees
 */
public class NumberIndicatorManager extends IndicatorManager {

    private int slideAmount;
    private TextView text;

    @Override
    public View init(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent,
                     int slideAmount) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.introduction_indicator_layout_number, parent,
                false);
        text = (TextView) root.findViewById(R.id.introduction_indicator_number_text);
        this.slideAmount = slideAmount;

        return root;
    }

    @Override
    public void select(int position) {
        text.setText((position + 1) + "/" + slideAmount);
    }
}
