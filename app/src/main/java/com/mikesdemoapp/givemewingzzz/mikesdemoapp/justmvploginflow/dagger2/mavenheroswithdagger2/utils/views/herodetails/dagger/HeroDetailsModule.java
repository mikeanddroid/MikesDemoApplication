package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.dagger;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.models.Hero;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.HeroDetailsActivity;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.mvp.HeroDetailsView;

import dagger.Module;
import dagger.Provides;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.dagger
 * Created by GiveMeWingzzz on 11/6/2017.
 */

@Module
public class HeroDetailsModule {

    HeroDetailsActivity detailsContext;
    Hero hero;

    public HeroDetailsModule(HeroDetailsActivity context, Hero aHero) {
        this.detailsContext = context;
        this.hero = aHero;
    }

    @Provides
    HeroDetailsView provideView() {
        return new HeroDetailsView(detailsContext, hero);
    }
}