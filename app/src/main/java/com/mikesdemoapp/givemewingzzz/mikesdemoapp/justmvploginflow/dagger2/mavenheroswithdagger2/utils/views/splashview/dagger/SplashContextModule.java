package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview.dagger;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview.SplashScreenActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.UI.splash.dagger
 * Created by GiveMeWingzzz on 11/6/2017.
 */

@Module
public class SplashContextModule {

    SplashScreenActivity splashContext;

    public SplashContextModule(SplashScreenActivity context) {
        this.splashContext = context;
    }

    @SplashScope
    @Provides
    SplashScreenActivity provideSplashContext() {
        return splashContext;
    }


}