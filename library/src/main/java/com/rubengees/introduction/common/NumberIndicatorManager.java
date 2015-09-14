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
