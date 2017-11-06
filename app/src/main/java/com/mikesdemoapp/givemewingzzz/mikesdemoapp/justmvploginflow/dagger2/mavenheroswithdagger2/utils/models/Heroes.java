package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.models;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.models
 * Created by GiveMeWingzzz on 11/6/2017.
 */

public class Heroes {

    @Expose
    private List<Hero> elements;

    public List<Hero> getElements() {
        return elements;
    }

    public void setElements(List<Hero> elements) {
        this.elements = elements;
    }

}
