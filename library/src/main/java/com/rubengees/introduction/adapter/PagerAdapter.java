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

package com.rubengees.introduction.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rubengees.introduction.entity.Slide;

import java.util.List;

import static com.rubengees.introduction.IntroductionFragment.newInstance;

/**
 * The Adapter for the Viewpager.
 * It creates the Fragments for each position.
 *
 * @author Ruben Gees
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private List<Slide> slides;

    public PagerAdapter(FragmentManager fm, List<Slide> slides) {
        super(fm);

        this.slides = slides;
    }

    @Override
    public Fragment getItem(int position) {
        return newInstance(slides.get(position));
    }

    @Override
    public int getCount() {
        return slides.size();
    }
}

