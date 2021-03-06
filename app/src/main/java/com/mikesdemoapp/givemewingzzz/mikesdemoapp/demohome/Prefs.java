package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private static final String PRE_LOAD = "preLoad";
    private static final String IS_DATA_SUCCESS = "IS_DATA_SUCCESS";
    private static final String CURRENT_TOKEN_KEY = "CURRENT_TOKEN_KEY";
    private static final String PREFS_NAME = "mikesappdemo";
    private static Prefs instance;
    private final SharedPreferences sharedPreferences;

    public Prefs(Context context) {

        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static Prefs with(Context context) {

        if (instance == null) {
            instance = new Prefs(context);
        }
        return instance;
    }

    public void setPreLoad(boolean totalTime) {

        sharedPreferences
                .edit()
                .putBoolean(PRE_LOAD, totalTime)
                .apply();
    }

    public void setDataSuccess(boolean isSuccess) {
        sharedPreferences.edit().putBoolean(IS_DATA_SUCCESS, isSuccess).apply();
    }

    public void setCurrentApiKey(String currentApiKey) {
        sharedPreferences.edit().putString(CURRENT_TOKEN_KEY, currentApiKey).apply();
    }

    public String getCurrentApiKey() {
        return sharedPreferences.getString(CURRENT_TOKEN_KEY, "");
    }

    public boolean isSuccess() {
        return sharedPreferences.getBoolean(IS_DATA_SUCCESS, false);
    }

    public boolean getPreLoad() {
        return sharedPreferences.getBoolean(PRE_LOAD, false);
    }


}
