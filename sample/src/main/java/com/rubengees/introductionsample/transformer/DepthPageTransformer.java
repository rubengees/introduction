package com.rubengees.introductionsample.transformer;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/**
 * An Animator for the ViewPager.
 *
 * @author Ruben Gees
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DepthPageTransformer extends BasePageTransformer {

    private static final float MIN_SCALE = 0.75f;

    @Override
    protected void transformPage(View page, int pageIndex, float position) {
        int pageWidth = page.getWidth();

        if (inRange(position)) {
            if (isLeftPage(position)) {
                // Use the default slide transition when moving to the left page.
                page.setAlpha(1);
                page.setTranslationX(0);
                page.setScaleX(1);
                page.setScaleY(1);
            } else {
                // Fade the page out.
                page.setAlpha(1 - position);

                // Counteract the default slide transition.
                page.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            }
        } else {
            page.setAlpha(0);
        }
    }
}
