package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.service;

import android.util.Log;

import com.squareup.okhttp.Response;

import java.io.IOException;

public class RequestInterceptor {

    public static final String TAG = RequestInterceptor.class.getSimpleName();
//    public static final retrofit.RequestInterceptor mIntercepter = new BaseUrlInterceptor();
//
//    private static class BaseUrlInterceptor extends BaseRequestInterceptor {
//
//        @Override
//        public void intercept(retrofit.RequestInterceptor.RequestFacade request) {
//
//            Token token = new Token();
//            token.load(InstagramDemoApplication.getInstance().getApplicationContext());
//
//            request.addEncodedQueryParam("access_token", token.getAccessToken());
//        }
//    }

    public static class BaseUrlInterceptor extends BaseRequestInterceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            // Take each request and chain query params
            Log.d(TAG, " Request : " + chain.request().url());

            return null;
        }
    }

}
