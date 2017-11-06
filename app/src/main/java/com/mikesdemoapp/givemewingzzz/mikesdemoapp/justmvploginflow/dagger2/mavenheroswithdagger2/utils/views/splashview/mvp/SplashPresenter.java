package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview.mvp;

import android.util.Log;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.UiUtils;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.rx.RxSchedulers;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.UI.splash.mvp
 * Created by GiveMeWingzzz on 11/6/2017.
 */

public class SplashPresenter {

    private SplashModel model;
    private RxSchedulers rxSchedulers;
    private CompositeSubscription subscriptions;

    public SplashPresenter(SplashModel model, RxSchedulers rxSchedulers, CompositeSubscription subscriptions) {
        this.model = model;
        this.rxSchedulers = rxSchedulers;
        this.subscriptions = subscriptions;
    }

    public void onCreate() {
        subscriptions.add(getHeroesList());
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Subscription getHeroesList() {
        return model.isNetworkAvailable().doOnNext(networkAvailable -> {
            if (!networkAvailable) {
                Log.d("no conn", "No Network Connection");
            }
        }).
                filter(isNetworkAvailable -> true).
                flatMap(isAvailable -> model.isNetworkAvailable()).
                subscribeOn(rxSchedulers.internet()).
                observeOn(rxSchedulers.androidThread()).subscribe(aBoolean -> model.presentHeroesListActivity(), throwable -> UiUtils.handleThrowable(throwable));
    }

}
