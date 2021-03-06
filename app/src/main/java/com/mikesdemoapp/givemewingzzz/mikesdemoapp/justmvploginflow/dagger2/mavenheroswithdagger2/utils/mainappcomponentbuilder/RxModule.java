package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.mainappcomponentbuilder;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.rx.AppRxSchedulers;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.mainappcomponentbuilder
 * Created by GiveMeWingzzz on 11/6/2017.
 */

@Module
public class RxModule {

    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }

}
