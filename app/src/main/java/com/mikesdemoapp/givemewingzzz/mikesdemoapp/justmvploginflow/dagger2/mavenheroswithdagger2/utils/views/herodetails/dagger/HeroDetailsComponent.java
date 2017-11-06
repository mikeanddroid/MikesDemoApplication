package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.dagger;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.HeroDetailsActivity;

import dagger.Component;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.herodetails.dagger
 * Created by GiveMeWingzzz on 11/6/2017.
 */

@Component(modules = {HeroDetailsModule.class})
public interface HeroDetailsComponent {
    void inject(HeroDetailsActivity context);
}
