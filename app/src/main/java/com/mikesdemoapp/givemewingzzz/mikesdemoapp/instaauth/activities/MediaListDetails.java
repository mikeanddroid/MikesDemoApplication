package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.Token;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.media.tags.MediaTag;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.core.BaseAuthAppCompat;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.service.api_call.GetUser;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.utils.AppConstants;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by GiveMeWingzz on 1/13/2016.
 */
public class MediaListDetails extends BaseAuthAppCompat implements View.OnClickListener {

    @BindView(R.id.item_detail_caption)
    public TextView captionText;

    @BindView(R.id.item_detail_media)
    public ImageView mediaImage;

//    @Bind(R.id.profileBlur)
//    public ImageView profileBlur;

    @BindView(R.id.mediaDetailHolder)
    public ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_transition));
//        }

        setContentView(R.layout.master_list_details);

        ButterKnife.bind(this);

        setRefreshing(true);

        Intent intent = getIntent();
        String caption = intent.getStringExtra(AppConstants.MEDIA_CAPTION_KEY);
        String media_url = intent.getStringExtra(AppConstants.MEDIA_URL_KEY);
//        String media_id = intent.getStringExtra(AppConstants.MEDIA_URL_ID);

        if (caption != null) {
            captionText.setText(caption);
        }

//        if (media_id != null) {
//
//        }

//        ImageLoaderUtility.loadImage(media_url, mediaImage);
//        ImageLoaderUtility.blurAndLoadImage(media_url, mediaImage);

        setRefreshing(false);

        Animation animation;
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        viewGroup.startAnimation(animation);

    }

    @Subscribe
    public void getUserTagInfo(GetUser.MediaTagSuccess object) {

        RealmResults<MediaTag> realmResults = realm.where(MediaTag.class).findAll();


    }

    @Override
    protected void onTokenLoaded(Token token) {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
