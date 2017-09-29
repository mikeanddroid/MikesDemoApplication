package com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.service;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.data.BaseModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Interface for creating requests
 */

public interface RetrofitInterface {

    // Use this api for passing in your own location(lat, lon)
    @GET("/maps/api/place/textsearch/json?query={searchType}&location={latitude},{longitude}&radius={radiusCover}&key= AIzaSyDk35iL27MteP2TYK7Wes13P4knEX1sFsk ")
    Call<BaseModel> getBBVALocationsWithQuery(@Query("searchType") String searchType, @Query("latitude") String latitude, @Query("longitude") String longitude, @Query("radius") int radius, @Query("type") String type, @Query("location") String location);

    // Use this api for getting bbva locations
    @GET("/maps/api/place/textsearch/json?query=BBVACompass&location=MY_LAT,MY_LAT&radius=10000")
    Call<BaseModel> getBBVALocations(@Query("key") String API_KEY);

    // Use this api for getting search results
    @GET("/maps/api/place/textsearch/json?location=MY_LAT,MY_LAT&radius=10000")
    Call<BaseModel> getSearchedLocations(@Query("query") String searchValue, @Query("key") String API_KEY);

    // Use this api for passing in your photo reference and getting back the image for that reference
    @GET("/maps/api/place/photo/")
    Call<BaseModel> getSearchedLocationImages(@Query("parameters") String photoReference, @Query("key") String API_KEY);

}
