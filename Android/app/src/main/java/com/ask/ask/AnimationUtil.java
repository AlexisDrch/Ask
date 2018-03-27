package com.ask.ask;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by pulakazad on 3/27/18.
 */

public class AnimationUtil {

    public static void animate(RecyclerView.ViewHolder holder, boolean goesRight) {

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holder.itemView, "translationX", goesRight==true ? 200 : -200, 0);
                animatorTranslateX.setDuration(1000);

        animatorSet.playTogether(animatorTranslateX);
        animatorSet.start();
    }
}
