package com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes;

import android.app.Activity;
import android.graphics.Path;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.PathMotion;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.AppConstants;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes
 * Created by GiveMeWingzzz on 10/19/2017.
 */

public class ImageDemoUtils {

    public static void initAnimation(AppConstants.TransitionType transitionType,
                                     AppConstants.TransitionType transitionType2, Activity activity) {

        if (transitionType != null) {
            switch (transitionType) {

                case Explode: { // For Explode By Code
                    Explode enterTransition = new Explode();
                    enterTransition.setDuration(activity.getResources().getInteger(R.integer.anim_duration_long));
                    activity.getWindow().setEnterTransition(enterTransition);
                    break;
                }

                case Slide: { // For Slide By Code
                    Slide enterTransition = new Slide();
                    enterTransition.setSlideEdge(Gravity.END);
                    enterTransition.setDuration(activity.getResources().getInteger(R.integer.anim_duration_very_very_long));
//                enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
                    activity.getWindow().setEnterTransition(enterTransition);
                    break;
                }

                case Fade: { // For Fade By Code
                    Fade enterTransition = new Fade();
                    enterTransition.setDuration(activity.getResources().getInteger(R.integer.anim_duration_very_long));
                    activity.getWindow().setEnterTransition(enterTransition);
                    break;
                }

            }
        }

        if (transitionType2 != null) {
            switch (transitionType2) {

                case Explode: { // For Explode By Code
                    Explode enterTransition = new Explode();
                    enterTransition.setPathMotion(new PathMotion() {
                        @Override
                        public Path getPath(float v, float v1, float v2, float v3) {
                            return null;
                        }
                    });
                    enterTransition.setMode(Visibility.MODE_IN);
                    enterTransition.setDuration(activity.getResources().getInteger(R.integer.anim_duration_medium));
                    activity.getWindow().setEnterTransition(enterTransition);
                    break;
                }

                case Slide: { // For Slide By Code
                    Slide enterTransition = new Slide();
                    enterTransition.setSlideEdge(Gravity.END);
                    enterTransition.setDuration(activity.getResources().getInteger(R.integer.anim_duration_very_very_long));
//                enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
                    activity.getWindow().setEnterTransition(enterTransition);
                    break;
                }

                case Fade: { // For Fade By Code
                    Fade enterTransition = new Fade();
                    enterTransition.setDuration(activity.getResources().getInteger(R.integer.anim_duration_very_long));
                    activity.getWindow().setEnterTransition(enterTransition);
                    break;
                }

            }
        }

    }

}
