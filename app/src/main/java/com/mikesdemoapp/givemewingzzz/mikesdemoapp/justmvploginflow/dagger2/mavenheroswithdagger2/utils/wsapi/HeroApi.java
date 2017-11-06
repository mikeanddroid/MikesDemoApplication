package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.wsapi;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.models.Heroes;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.wsapi
 * Created by GiveMeWingzzz on 11/6/2017.
 */

public interface HeroApi {

    @GET("test-mobile/iOS/json/test2.json")
    Observable<Heroes> getHeroes();

}
