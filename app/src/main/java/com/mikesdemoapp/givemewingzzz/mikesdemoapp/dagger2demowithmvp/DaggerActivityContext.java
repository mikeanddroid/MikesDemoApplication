package com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp
 * Created by GiveMeWingzzz on 11/3/2017.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DaggerActivityContext {
}
