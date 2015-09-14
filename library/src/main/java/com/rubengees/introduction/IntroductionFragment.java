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

package com.rubengees.introduction;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.rubengees.introduction.entity.Slide;

import pl.droidsonroids.gif.GifImageView;


/**
 * A Fragment which displays a single Slide.
 *
 * @author Ruben Gees
 */
public class IntroductionFragment extends Fragment {

    private Slide slide;
    private View root;
    private TextView title;
    private GifImageView image;
    private FrameLayout descriptionContainer;

    public IntroductionFragment() {
        // Required empty public constructor
    }

    public static IntroductionFragment newInstance(Slide slide) {
        IntroductionFragment fragment = new IntroductionFragment();
        Bundle args = new Bundle();
        args.putParcelable("introduction_slide", slide);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.slide = getArguments().getParcelable("introduction_slide");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createViews(inflater, container);
        initViews(inflater);
        handleTranslucency();

        return root;
    }

    private void handleTranslucency() {
        SystemBarTintManager.SystemBarConfig config = getIntroductionActivity().getSystemBarTintConfig();
        if (config != null) {
            root.setPadding(0, config.getPixelInsetTop(false), config.getPixelInsetRight(), config.getPixelInsetBottom());
        }
    }

    private void initViews(LayoutInflater inflater) {
        if (slide.getTitle() != null) {
            title.setText(slide.getTitle());
        }

        if (slide.getDescription() == null && slide.getOption() != null) {
            AppCompatCheckBox option = (AppCompatCheckBox) inflater.inflate(R.layout.introduction_fragment_option, descriptionContainer, false);

            option.setText(slide.getOption().getTitle());
            option.setChecked(slide.getOption().isActivated());
            option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    slide.getOption().setActivated(isChecked);
                }
            });
            option.setSupportButtonTintList(ContextCompat.getColorStateList(getContext(), android.R.color.white));
            descriptionContainer.addView(option);
        } else if (slide.getDescription() != null) {
            TextView description = (TextView) inflater.inflate(R.layout.introduction_fragment_description, descriptionContainer, false);

            description.setText(slide.getDescription());
            descriptionContainer.addView(description);
        }

        if (slide.getImageResource() != null) {
            image.setImageResource(slide.getImageResource());
        }

        root.setBackgroundColor(slide.getColor());
    }

    private void createViews(LayoutInflater inflater, ViewGroup container) {
        root = inflater.inflate(R.layout.introduction_fragment, container, false);
        title = (TextView) root.findViewById(R.id.introduction_fragment_title);
        image = (GifImageView) root.findViewById(R.id.introduction_fragment_image);
        descriptionContainer = (FrameLayout) root.findViewById(R.id.introduction_fragment_description_container);
    }

    public IntroductionActivity getIntroductionActivity() {
        return (IntroductionActivity) getActivity();
    }
}
