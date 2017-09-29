package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by GiveMeWingzzz on 9/28/2017.
 */

public class MikesDemoApplication extends Application {

    public static MikesDemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        // Init Realm DB
        instance = this;
        Realm.init(this);
        Realm.setDefaultConfiguration(DBHelper.getRealmConfig(this));

    }

    public static MikesDemoApplication getInstance() {
        return instance;
    }

}
