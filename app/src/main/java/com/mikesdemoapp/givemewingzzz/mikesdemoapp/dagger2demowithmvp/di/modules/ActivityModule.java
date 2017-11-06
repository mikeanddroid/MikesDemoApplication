package com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.modules;

import android.app.Activity;
import android.content.Context;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.DaggerActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.modules
 * Created by GiveMeWingzzz on 11/3/2017.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @DaggerActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

}
