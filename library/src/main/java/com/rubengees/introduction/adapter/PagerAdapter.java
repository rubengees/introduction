package com.rubengees.introduction.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rubengees.introduction.IntroductionFragment;
import com.rubengees.introduction.entity.Slide;

import java.util.List;

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
        return IntroductionFragment.newInstance(slides.get(position));
    }

    @Override
    public int getCount() {
        return slides.size();
    }
}
