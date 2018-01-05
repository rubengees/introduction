package com.rubengees.introduction.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rubengees.introduction.Slide;

import java.util.List;

import static com.rubengees.introduction.IntroductionFragment.newInstance;

/**
 * The Adapter for the ViewPager.
 * It creates the Fragments for each position.
 *
 * @author Ruben Gees
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public class PagerAdapter extends FragmentStatePagerAdapter {

    private final List<Slide> slides;

    public PagerAdapter(FragmentManager fm, List<Slide> slides) {
        super(fm);

        this.slides = slides;
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        return newInstance(slides.get(position));
    }

    @Override
    public int getCount() {
        return slides.size();
    }
}

