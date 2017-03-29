package com.goalieunionapps.grmacsfc.Utils;

import android.animation.TimeInterpolator;
import android.view.View;

import com.goalieunionapps.grmacsfc.R;

/**
 * Created by jonsaliers on 3/27/17.
 */

public class ProgressBarUtils {

    public static void displayProgressBar(View progressView) {

        progressView.animate().alpha(1f).setDuration(500).setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float v) {
                if (v < 0.5) {
                    return 0;
                } else {
                    return (v - 0.5f) / 0.5f;
                }

            }
        });

    }

    public static void hideProgressBar(View progressView) {
        progressView.clearAnimation();
        progressView.animate().alpha(0f);
    }
}
