package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.service;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;

public abstract class BaseRequestInterceptor implements Interceptor {

    /**
     * Called for every request.
     *
     * @param chain
     */

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
