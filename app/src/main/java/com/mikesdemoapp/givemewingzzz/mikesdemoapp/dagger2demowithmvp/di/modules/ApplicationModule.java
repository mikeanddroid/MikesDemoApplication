package com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.DaggerApplicationContext;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.modules
 * Created by GiveMeWingzzz on 11/3/2017.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @DaggerApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "demo-dagger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }

}
