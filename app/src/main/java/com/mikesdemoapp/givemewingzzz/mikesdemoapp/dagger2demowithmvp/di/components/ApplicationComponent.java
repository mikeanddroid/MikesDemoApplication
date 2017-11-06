package com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.components;

import android.app.Application;
import android.content.Context;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.DaggerApplicationContext;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.data.DataManager;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.data.DbHelper;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.data.SharedPrefsHelper;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.modules.ApplicationModule;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.MikesDemoApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.components
 * Created by GiveMeWingzzz on 11/3/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    /**
     * When the dependencies are provided through field injection
     * i.e. @inject on the member variables, we have to tell the Dagger to scan this class through the implementation of this interface.
     */
    void inject(MikesDemoApplication mikesDemoApplication);

    @DaggerApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefsHelper getPreferenceHelper();

    DbHelper getDbHelper();

}
