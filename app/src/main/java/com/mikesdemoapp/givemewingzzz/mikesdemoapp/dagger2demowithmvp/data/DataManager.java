package com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.data;

import android.content.Context;
import android.content.res.Resources;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.DaggerApplicationContext;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.DaggerUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.data
 * Created by GiveMeWingzzz on 11/3/2017.
 */

@Singleton
public class DataManager {

    private Context mContext;
    private DbHelper mDbHelper;
    private SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    public DataManager(@DaggerApplicationContext Context context,
                       DbHelper dbHelper,
                       SharedPrefsHelper sharedPrefsHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public Long createUser(DaggerUser user) throws Exception {
        return mDbHelper.insertUser(user);
    }

    public void deleteUsersTable() throws Exception {
        mDbHelper.deleteUsersTable();
    }

    public void createDaggerUsers(List<DaggerUser> daggerUserList) throws Exception {
        for (DaggerUser daggerUser : daggerUserList) {
            mDbHelper.insertUser(daggerUser);
        }
    }

    public DaggerUser getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        return mDbHelper.getDaggerUser(userId);
    }

    public List<DaggerUser> getAllDaggerUsers(List<Long> userIDs) throws Resources.NotFoundException, NullPointerException {

        List<DaggerUser> daggerUsers = new ArrayList<>();

        for (Long userId : userIDs) {
            daggerUsers.add(mDbHelper.getDaggerUser(userId));
        }

        return daggerUsers;
    }

}
