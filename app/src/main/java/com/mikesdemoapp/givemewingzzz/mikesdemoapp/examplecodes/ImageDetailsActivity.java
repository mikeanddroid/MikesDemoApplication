package com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.AppConstants;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes
 * Created by GiveMeWingzzz on 10/19/2017.
 */

public class ImageDetailsActivity extends AppCompatActivity {

    AppConstants.TransitionType transitionType;
    AppConstants.TransitionType transitionType2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_content);

        transitionType = (AppConstants.TransitionType) getIntent().getSerializableExtra(AppConstants.KEY_ANIM_TYPE);
        transitionType2 = (AppConstants.TransitionType) getIntent().getSerializableExtra(AppConstants.KEY_ANIM_TYPE2);

        ImageView imageView = (ImageView) findViewById(R.id.demoItemDetailImageView);
        TextView imageViewName = (TextView) findViewById(R.id.demoItemDetailImageName);
        TextView imageViewDate = (TextView) findViewById(R.id.demoItemDetailImageDate);

        Intent intent = getIntent();

        String imageUrl = null;
        String imageName = null;
        String imageDate = null;

        imageUrl = intent.getStringExtra(MyDialogFragment.IMAGE_URL_KEY);
        imageName = intent.getStringExtra(MyDialogFragment.IMAGE_NAME_KEY);
        imageDate = intent.getStringExtra(MyDialogFragment.IMAGE_DATE_KEY);

        GlideApp.with(this).load(imageUrl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        progressBar.setVisibility(View.GONE);
                return false;
            }
        }).placeholder(new ColorDrawable(Color.BLACK)).fitCenter().into(imageView);

        imageViewName.setText(imageName);
        imageViewDate.setText(imageDate);

        ImageDemoUtils.initAnimation(transitionType, transitionType2, this);

    }

}
