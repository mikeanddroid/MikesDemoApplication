package com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes;

import java.util.Comparator;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes
 * Created by GiveMeWingzzz on 10/22/2017.
 */

public class ExampleImageComparator implements Comparator<ImageDataModel> {


    @Override
    public int compare(ImageDataModel imageDataModel, ImageDataModel t1) {
        return imageDataModel.getImageCategory().compareTo(t1.getImageCategory());
    }
}
