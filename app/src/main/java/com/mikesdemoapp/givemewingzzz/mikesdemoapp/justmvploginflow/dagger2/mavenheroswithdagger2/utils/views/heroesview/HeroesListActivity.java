package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.MikesDemoApplication;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.models.Hero;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.HeroDetailsActivity;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.dagger.DaggerHeroesComponent;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.dagger.HeroesModule;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.mvp.HeroesPresenter;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.mvp.HeroesView;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.UI.splash.heroes
 * Created by GiveMeWingzzz on 11/6/2017.
 */

public class HeroesListActivity extends AppCompatActivity {

    @Inject
    HeroesView view;

    @Inject
    HeroesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerHeroesComponent.builder().appComponent(MikesDemoApplication.getNetComponent()).heroesModule(new HeroesModule(this)).build().inject(this);
        setContentView(view.view());
        presenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void goToHeroDetailsActivity(Hero hero) {

        Intent in = new Intent(this, HeroDetailsActivity.class);
        in.putExtra("hero", (Serializable) hero);
        startActivity(in);

    }

}
