package com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.components;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.Dagger2HomeActivity;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.PerActivity;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.modules.ActivityModule;

import dagger.Component;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di
 * Created by GiveMeWingzzz on 11/3/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(Dagger2HomeActivity dagger2HomeActivity);
}
