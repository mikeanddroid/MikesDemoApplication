package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.media.tags;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by GiveMeWingzz on 1/13/2016.
 */
public class MediaTagWrapper extends RealmObject {

    private RealmList<MediaTag> data;

    public void setData(RealmList<MediaTag> data) {
        this.data = data;
    }

    public RealmList<MediaTag> getData() {
        return data;
    }
}
