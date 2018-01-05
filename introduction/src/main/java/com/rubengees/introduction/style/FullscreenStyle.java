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

package com.rubengees.introduction.style;

import android.app.Activity;
import android.support.annotation.NonNull;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

/**
 * Style for showing the {@link com.rubengees.introduction.IntroductionActivity} in fullscreen.
 *
 * @author Ruben Gees
 */
public class FullscreenStyle extends Style {

    @Override
    public void applyStyle(@NonNull Activity activity) {
        activity.getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
    }
}
