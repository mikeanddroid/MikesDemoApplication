package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.media.user;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by GiveMeWingzz on 1/14/2016.
 */
public class UsersListWrapper extends RealmObject {

    private RealmList<UsersList> data;

    public RealmList<UsersList> getData() {
        return data;
    }

    public void setData(RealmList<UsersList> data) {
        this.data = data;
    }
}
