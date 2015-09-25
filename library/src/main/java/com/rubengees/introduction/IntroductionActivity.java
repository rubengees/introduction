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

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.rubengees.introduction.adapter.PagerAdapter;
import com.rubengees.introduction.common.DotIndicatorManager;
import com.rubengees.introduction.entity.Option;
import com.rubengees.introduction.entity.Slide;
import com.rubengees.introduction.interfaces.IndicatorManager;
import com.rubengees.introduction.util.ButtonManager;
import com.rubengees.introduction.util.OrientationUtils;
import com.rubengees.introduction.util.Utils;

import java.util.ArrayList;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public class IntroductionActivity extends AppCompatActivity {

    public static final String OPTION_RESULT = "introduction_option_result";

    private ArrayList<Slide> slides;
    private int style;

    private ViewPager pager;
    private ImageButton previous;
    private ImageButton next;
    private FrameLayout indicatorContainer;
    private ViewGroup bottomBar;

    private IntroductionConfiguration configuration;

    private ButtonManager buttonManager;
    private IndicatorManager indicatorManager;

    private boolean showPreviousButton;
    private boolean showIndicator;
    private int orientation;
    private SystemBarTintManager.SystemBarConfig config;

    private int previousPagerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getFieldsFromBundle();
        applyStyle();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_activity);

        configuration = IntroductionConfiguration.getInstance();

        findViews();
        initSlides();
        initManagers();
        initViews();

        if (savedInstanceState == null) {
            select(0);
        } else {
            previousPagerPosition = savedInstanceState.getInt("previous_pager_position");
            select(previousPagerPosition);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("previous_pager_position", previousPagerPosition);
    }

    @Nullable
    SystemBarTintManager.SystemBarConfig getSystemBarTintConfig() {
        return config;
    }

    private void getFieldsFromBundle() {
        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {
            bundle = new Bundle();
        }

        slides = bundle.getParcelableArrayList("introduction_slides");
        style = bundle.getInt("introduction_style", IntroductionBuilder.STYLE_TRANSLUCENT);
        orientation = bundle.getInt("introduction_orientation",
                IntroductionBuilder.ORIENTATION_BOTH);
        showPreviousButton = bundle.getBoolean("introduction_show_previous_button", true);
        showIndicator = bundle.getBoolean("introduction_show_indicator", true);

        if (slides == null) {
            slides = new ArrayList<>();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void applyStyle() {
        if (style == IntroductionBuilder.STYLE_FULLSCREEN) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (style == IntroductionBuilder.STYLE_TRANSLUCENT) {
            if (Utils.isTranslucencyAvailable(getResources())) {
                Window w = getWindow();

                w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }

        if (orientation == IntroductionBuilder.ORIENTATION_PORTRAIT) {
            OrientationUtils.setOrientationPortrait(this);
        } else if (orientation == IntroductionBuilder.ORIENTATION_LANDSCAPE) {
            OrientationUtils.setOrientationLandscape(this);
        }
    }

    private void findViews() {
        pager = (ViewPager) findViewById(R.id.introduction_activity_pager);
        bottomBar = (ViewGroup) findViewById(R.id.introduction_activity_bottom_bar);
        previous = (ImageButton) findViewById(R.id.introduction_activity_button_previous);
        next = (ImageButton) findViewById(R.id.introduction_activity_button_next);
        indicatorContainer = (FrameLayout)
                findViewById(R.id.introduction_activity_container_indicator);

        if (style == IntroductionBuilder.STYLE_TRANSLUCENT) {
            handleTranslucency();
        }
    }

    private void handleTranslucency() {
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        config = tintManager.getConfig();
        RelativeLayout.LayoutParams params =
                (RelativeLayout.LayoutParams) bottomBar.getLayoutParams();
        params.height = params.height + config.getPixelInsetBottom();
        bottomBar.setLayoutParams(params);

        bottomBar.setPadding(0, -config.getPixelInsetBottom(), config.getPixelInsetRight(), 0);
    }

    private void initSlides() {
        for (int i = 0; i < slides.size(); i++) {
            Slide slide = slides.get(i);

            slide.init(this, i);
        }
    }

    private void initManagers() {
        buttonManager = new ButtonManager(previous, next, showPreviousButton, slides.size());
        indicatorManager = configuration.getIndicatorManager();

        if (indicatorManager == null) {
            if (showIndicator) {
                indicatorManager = new DotIndicatorManager();
            }
        }

        if (indicatorManager != null) {
            indicatorContainer.addView(indicatorManager.init(LayoutInflater.from(this),
                    indicatorContainer, slides.size()));
        }
    }

    private void initViews() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentIndex = pager.getCurrentItem();

                if (currentIndex == slides.size() - 1) {
                    handleFinish();
                } else {
                    pager.setCurrentItem(currentIndex + 1, true);
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(pager.getCurrentItem() - 1, true);
            }
        });

        pager.setAdapter(new PagerAdapter(getSupportFragmentManager(), slides));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position != previousPagerPosition) {
                    select(position);

                    IntroductionConfiguration.getInstance().
                            callOnSlideChanged(previousPagerPosition, position);
                    previousPagerPosition = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (configuration.getPageTransformer() != null) {
            pager.setPageTransformer(true, configuration.getPageTransformer());
        }
    }

    private void select(int position) {
        if (indicatorManager != null) {
            indicatorManager.select(position);
        }

        if (buttonManager != null) {
            buttonManager.select(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            handleFinishCancelled();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    private void handleFinish() {
        IntroductionConfiguration.getInstance().clear();
        ArrayList<Option> options = new ArrayList<>();

        for (Slide slide : slides) {
            if (slide.getOption() != null) {
                options.add(slide.getOption());
            }
        }

        Intent result = new Intent();

        result.putParcelableArrayListExtra(OPTION_RESULT, options);
        setResult(RESULT_OK, result);
        finish();
    }

    private void handleFinishCancelled() {
        IntroductionConfiguration.getInstance().clear();
        setResult(RESULT_CANCELED);
        finish();
    }
}
