package com.rubengees.introduction.style;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.rubengees.introduction.R;
import com.rubengees.introduction.util.Utils;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public class TranslucentStyle extends Style {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void applyStyle(@NonNull Activity activity) {
        if (Utils.isTranslucencyAvailable(activity.getResources())) {
            Window w = activity.getWindow();

            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void applyStyleOnActivityView(@NonNull Activity activity, @NonNull ViewGroup root) {
        ViewGroup bottomBar = (ViewGroup) root.findViewById(R.id.introduction_activity_bottom_bar);
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();

        RelativeLayout.LayoutParams params =
                (RelativeLayout.LayoutParams) bottomBar.getLayoutParams();
        params.height = params.height + config.getPixelInsetBottom();
        bottomBar.setLayoutParams(params);

        bottomBar.setPadding(0, -config.getPixelInsetBottom(), config.getPixelInsetRight(), 0);
    }

    @Override
    public void applyStyleOnFragmentView(@NonNull Fragment fragment, @NonNull ViewGroup root) {
        SystemBarTintManager tintManager = new SystemBarTintManager(fragment.getActivity());
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();

        root.setPadding(0, config.getPixelInsetTop(false), config.getPixelInsetRight(),
                config.getPixelInsetBottom());
    }
}
