package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.MikesDemoApplication;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.HeroesListActivity;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview.dagger.DaggerSplashComponent;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview.dagger.SplashContextModule;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview.mvp.SplashPresenter;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview.mvp.SplashView;

import javax.inject.Inject;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.UI.splash
 * Created by GiveMeWingzzz on 11/6/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Inject
    SplashView splashView;

    @Inject
    SplashPresenter splashPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSplashComponent.builder()
                .appComponent(MikesDemoApplication.getNetComponent())
                .splashContextModule(new SplashContextModule(this))
                .build().inject(this);

        setContentView(splashView.constructView());
        splashPresenter.onCreate();

    }

    public void showHeroesListActivity() {

        Log.d("loaded", "ok showed");
        Intent i = new Intent(this, HeroesListActivity.class);
        startActivity(i);
        this.finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashPresenter.onDestroy();
    }

}
