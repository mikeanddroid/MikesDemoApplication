package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.service;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.utils.AppConstants;
import com.squareup.okhttp.OkHttpClient;

import io.realm.RealmObject;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class InstagramBaseClient {

    public static final String TAG = InstagramBaseClient.class.getSimpleName();

    private InstagramBaseClient() {
    }

    public static RetrofitInterface getRetrofitInterface() {
        return LazySIDCInterface.INSTANCE;
    }

    private static class LazySIDCInterface {
        private static final RetrofitInterface INSTANCE = initializeInterface();

        private static RetrofitInterface initializeInterface() {
            // Create the necessary GSON to handle exclusion of Realm pieces
            Gson gson = new GsonBuilder()
                    .setExclusionStrategies(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            return f.getDeclaringClass().equals(RealmObject.class);
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            return false;
                        }
                    })
                    .create();

            // Configure OkHttp+AppD
            OkHttpClient client = new OkHttpClient();

            // Create retrofit instance
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return retrofit.create(RetrofitInterface.class);
        }
    }

}
