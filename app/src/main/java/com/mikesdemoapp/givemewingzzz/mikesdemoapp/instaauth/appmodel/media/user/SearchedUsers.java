package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.media.user;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by GiveMeWingzz on 1/12/2016.
 */
public class SearchedUsers extends RealmObject {

    @SerializedName("id")
    @PrimaryKey
    private String userId;

    @SerializedName("username")
    private String userName;

    @SerializedName("full_name")
    private String userFullName;

    @SerializedName("profile_picture")
    private String userProfilePicture;

    public SearchedUsers() {
    }

    public SearchedUsers(SearchedUsers copy) {
        this.userId = copy.getUserId();
        this.userName = copy.getUserName();
        this.userFullName = copy.getUserFullName();
        this.userProfilePicture = copy.getUserProfilePicture();
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }
}
