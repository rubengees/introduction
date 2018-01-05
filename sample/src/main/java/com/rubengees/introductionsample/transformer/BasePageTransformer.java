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

package com.rubengees.introductionsample.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Base class for all ViewPager transformer, simplifying implementation.
 *
 * @author Ruben Gees
 */
public abstract class BasePageTransformer implements ViewPager.PageTransformer {

    public static boolean inRange(final float position) {
        return position <= 1.0 && position >= -1.0;
    }

    public static boolean isLeftPage(final float position) {
        return position < 0;
    }

    public static boolean isRightPage(final float position) {
        return position > 0;
    }

    @Override
    public void transformPage(@NonNull final View page, final float position) {
        final int pageIndex = (Integer) page.getTag();

        transformPage(page, pageIndex, position);
    }

    protected abstract void transformPage(final View page, final int pageIndex, final float position);

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
