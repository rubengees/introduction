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

    @Override
    public View init(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int slideAmount) {
        root = (ViewGroup) inflater.inflate(R.layout.introduction_indicator_layout_dot, parent, false);

        for (int i = 0; i < slideAmount; i++) {
            ImageView dot = (ImageView) inflater.inflate(R.layout.introduction_indicator_item_dot, root, false);

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
