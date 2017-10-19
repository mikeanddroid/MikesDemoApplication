package com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes
 * Created by GiveMeWingzzz on 10/18/2017.
 */

public class MyDialogFragment extends DialogFragment {


    private final String LOG_TAG = MyDialogFragment.class.getSimpleName();

    public static final String MY_DIALOG_FRAGMENT_TAG = "MY_DIALOG_FRAGMENT_TAG";

    public static final String IMAGE_URL_KEY = "IMAGE_URL_KEY";
    public static final String IMAGE_NAME_KEY = "IMAGE_NAME_KEY";
    public static final String IMAGE_DATE_KEY = "IMAGE_DATE_KEY";

    // onCreate --> (onCreateDialog) --> onCreateView --> onActivityCreated

    public static MyDialogFragment getMyDialogFragment(ImageDataModel imageDataModel) {

        MyDialogFragment myDialogFragment = new MyDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_URL_KEY, imageDataModel.getImageUrl());
        bundle.putString(IMAGE_NAME_KEY, imageDataModel.getName());
        bundle.putString(IMAGE_DATE_KEY, imageDataModel.getDate());

        myDialogFragment.setArguments(bundle);

        return myDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.v(LOG_TAG, "onCreateView");

        View dialogView = inflater.inflate(R.layout.dialog_content, container, false);

        ImageView imageView = (ImageView) dialogView.findViewById(R.id.demoItemDetailImageView);
        TextView imageViewName = (TextView) dialogView.findViewById(R.id.demoItemDetailImageName);
        TextView imageViewDate = (TextView) dialogView.findViewById(R.id.demoItemDetailImageDate);
//        final ProgressBar progressBar = (ProgressBar) dialogView.findViewById(R.id.demoItemDetailProgressBar);

        Bundle bundle = getArguments();

        String imageUrl = null;
        String imageName = null;
        String imageDate = null;

        if (bundle != null) {
            imageUrl = getArguments().getString(IMAGE_URL_KEY);
            imageName = getArguments().getString(IMAGE_NAME_KEY);
            imageDate = getArguments().getString(IMAGE_DATE_KEY);
        }

//        progressBar.setVisibility(View.VISIBLE);

        GlideApp.with(this).load(imageUrl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).placeholder(new ColorDrawable(Color.BLACK)).fitCenter().into(imageView);

        imageViewName.setText(imageName);
        imageViewDate.setText(imageDate);

        return dialogView;

    }


    // If shown as dialog, set the width of the dialog window

    // onCreateView --> onActivityCreated -->  onViewStateRestored --> onStart --> onResume

    @Override

    public void onResume() {

        super.onResume();

        Log.v(LOG_TAG, "onResume");

        if (getShowsDialog()) {

            // Set the width of the dialog to the width of the screen in portrait mode

            DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();

            int dialogWidth = Math.min(metrics.widthPixels, metrics.heightPixels);

            getDialog().getWindow().setLayout(dialogWidth, WRAP_CONTENT);

        }

    }


    private void showToast(String buttonName) {
        Toast.makeText(getActivity(), "Clicked on \"" + buttonName + "\"", Toast.LENGTH_SHORT).show();
    }


    // If dialog is cancelled: onCancel --> onDismiss

    @Override

    public void onCancel(DialogInterface dialog) {
        Log.v(LOG_TAG, "onCancel");
    }

    // If dialog is cancelled: onCancel --> onDismiss

    // If dialog is dismissed: onDismiss

    @Override

    public void onDismiss(DialogInterface dialog) {
        Log.v(LOG_TAG, "onDismiss");
    }

}
