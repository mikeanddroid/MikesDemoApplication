package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview.mvp;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.NetworkUtils;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview.SplashScreenActivity;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.models.Heroes;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.wsapi.HeroApi;

import rx.Observable;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.UI.splash.mvp
 * Created by GiveMeWingzzz on 11/6/2017.
 */

public class SplashModel {

    private HeroApi heroApi;
    private SplashScreenActivity splashScreenActivity;

    public SplashModel(HeroApi heroApi, SplashScreenActivity splashScreenActivity) {
        this.heroApi = heroApi;
        this.splashScreenActivity = splashScreenActivity;
    }

    Observable<Heroes> provideHerosList() {
        return heroApi.getHeroes();
    }

    Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(splashScreenActivity);
    }

    public void presentHeroesListActivity() {
        splashScreenActivity.showHeroesListActivity();
    }

}
