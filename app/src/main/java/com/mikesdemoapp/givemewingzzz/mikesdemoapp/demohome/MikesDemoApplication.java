package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

import android.app.Application;

import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;

import io.realm.Realm;

/**
 * Created by GiveMeWingzzz on 9/28/2017.
 */

public class MikesDemoApplication extends Application {

    public static MikesDemoApplication instance;
    // Size of mini thumb in pixels.
    private static final int MINI_THUMB_SIZE = 100;

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

    @GlideOption
    public static void miniThumb(RequestOptions options) {
//        options.fitCenter().override(MINI_THUMB_SIZE);
//        options.fitCenter().override(MINI_THUMB_SIZE);

    }

}
