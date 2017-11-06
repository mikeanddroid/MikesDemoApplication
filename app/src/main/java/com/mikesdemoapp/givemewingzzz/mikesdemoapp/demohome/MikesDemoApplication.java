package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.data.DataManager;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.components.ApplicationComponent;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.components.DaggerApplicationComponent;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.modules.ApplicationModule;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.mainappcomponentbuilder.AppComponent;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.mainappcomponentbuilder.AppContextModule;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.mainappcomponentbuilder.DaggerAppComponent;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by GiveMeWingzzz on 9/28/2017.
 */

public class MikesDemoApplication extends Application {

    public static MikesDemoApplication instance;
    // Size of mini thumb in pixels.
    private static final int MINI_THUMB_SIZE = 100;

    protected ApplicationComponent applicationComponent;

    private static AppComponent appComponent;

    @Inject
    DataManager dataManager;

    public static MikesDemoApplication get(Context context) {
        return (MikesDemoApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        initAppComponent();

        applicationComponent.inject(this);

        // Init Realm DB
        instance = this;
        Realm.init(this);
        Realm.setDefaultConfiguration(DBHelper.getRealmConfig(this));

    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appContextModule(new AppContextModule(this)).build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public static AppComponent getNetComponent() {
        return appComponent;
    }

    public static MikesDemoApplication getInstance() {
        return instance;
    }

    @GlideOption
    public static void miniThumb(RequestOptions options) {
//        options.fitCenter().override(MINI_THUMB_SIZE);
//        options.fitCenter().override(MINI_THUMB_SIZE);

    }

}
