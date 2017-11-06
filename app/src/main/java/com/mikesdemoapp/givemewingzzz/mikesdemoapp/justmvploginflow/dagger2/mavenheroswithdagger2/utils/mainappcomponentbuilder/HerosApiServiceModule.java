package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.mainappcomponentbuilder;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.wsapi.HeroApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.mainappcomponentbuilder
 * Created by GiveMeWingzzz on 11/6/2017.
 */

@Module
public class HerosApiServiceModule {

    private static final String BASE_URL = "http://coemygroup.fr/";

    @AppScope
    @Provides
    HeroApi provideApiService(OkHttpClient client, RxJavaCallAdapterFactory rxAdapter) {

        Retrofit retrofit = new Retrofit.Builder().client(client)
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter).build();

        return retrofit.create(HeroApi.class);
    }

}
