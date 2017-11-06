package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.dagger;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.mainappcomponentbuilder.AppComponent;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.HeroesListActivity;

import dagger.Component;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.dagger
 * Created by GiveMeWingzzz on 11/6/2017.
 */

@HeroesScope
@Component(dependencies = {AppComponent.class}, modules = {HeroesModule.class})
public interface HeroesComponent {

    void inject(HeroesListActivity heroesActivity);
}