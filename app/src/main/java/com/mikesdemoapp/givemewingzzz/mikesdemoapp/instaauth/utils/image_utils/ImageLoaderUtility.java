package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.utils.image_utils;

import android.widget.ImageView;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.MikesDemoApplication;
import com.squareup.picasso.Picasso;

/**
 * Image Loading utility to load images.
 */
public class ImageLoaderUtility {

    public static void loadImage(String imageURI, ImageView imageView) {
        Picasso.with(MikesDemoApplication.getInstance()).load(imageURI).into(imageView);
    }

}
