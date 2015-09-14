package com.rubengees.introduction.util;

import android.content.res.Resources;
import android.support.annotation.NonNull;

/**
 * Various utils.
 *
 * @author Ruben Gees
 */
public class Utils {

    /**
     * Checks if translucency is available on the device.
     *
     * @param resources The Resources
     * @return True, if translucency is available.
     */
    public static boolean isTranslucencyAvailable(@NonNull Resources resources) {
        int id = resources.getIdentifier("config_enableTranslucentDecor", "bool", "android");

        return id != 0 && resources.getBoolean(id);
    }
}
