package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.service;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.ListWrapper;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.UserWrapper;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.media.data.MediaInfoWrapper;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.media.tags.MediaTagWrapper;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.media.user.UserFollows;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.media.user.UsersListWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by GiveMeWingzz on 1/7/2016.
 */
public interface RetrofitInterface {

    @GET("/v1/users/self/")
    public void getSelfUserInfo(Callback<UserWrapper> cb);

    @GET("/v1/users/{user-id}/")
    public void getUserInfoByID(@Path("user-id") String user_id, Callback<UserWrapper> cb);

    @GET("/v1/users/self/media/recent/")
    public void getUserRecentMedia(Callback<MediaInfoWrapper> cb);

    @GET("/v1/users/{user-id}/media/recent/")
    public void getRecentUserMediaInfo(@Path("user-id") String user_id, Callback<UserWrapper> cb);

    @GET("/v1/users/self/media/liked")
    public void getRecentMediaLikedInfo(Callback<UserWrapper> cb);

    @GET("/v1/users/search")
    public void getSearchedUserByName(@Query("q") String userName, Callback<UsersListWrapper> cb);

    // Media //

    @GET("/v1/media/{media-id}")
    public void getMediaInfoWithID(@Path("media-id") String media_id, Callback<MediaInfoWrapper> cb);


    // Tags //
    @GET("/v1/tags/{tag-name}/media/recent")
    public void getMediaForTagName(@Path("tag-name") String tag_name, Callback<MediaTagWrapper> cb);

    // Follows
    @GET("/v1/users/self/follows")
    public void getUserFollows(Callback<ListWrapper<UserFollows>> cb);

}
