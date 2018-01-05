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
public class ZoomOutPageTransformer extends BasePageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    protected void transformPage(View page, int pageIndex, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();

        if (inRange(position)) {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float verticalMargin = pageHeight * (1 - scaleFactor) / 2;
            float horizontalMargin = pageWidth * (1 - scaleFactor) / 2;

            if (position < 0) {
                page.setTranslationX(horizontalMargin - verticalMargin / 2);
            } else {
                page.setTranslationX(-horizontalMargin + verticalMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1).
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        } else {
            page.setAlpha(0);
        }
    }
}
