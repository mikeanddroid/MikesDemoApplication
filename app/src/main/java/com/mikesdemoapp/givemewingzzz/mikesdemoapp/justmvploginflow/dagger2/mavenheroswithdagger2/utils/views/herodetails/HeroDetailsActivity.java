package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.models.Hero;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.dagger.DaggerHeroDetailsComponent;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.dagger.HeroDetailsModule;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.mvp.HeroDetailsView;

import javax.inject.Inject;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails
 * Created by GiveMeWingzzz on 11/6/2017.
 */

public class HeroDetailsActivity extends AppCompatActivity {

    @Inject
    HeroDetailsView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Hero hero = (Hero) getIntent().getExtras().get("hero");

        DaggerHeroDetailsComponent.builder().heroDetailsModule(new HeroDetailsModule(this, hero)).build().inject(this);

        setContentView(view.view());

    }

}
