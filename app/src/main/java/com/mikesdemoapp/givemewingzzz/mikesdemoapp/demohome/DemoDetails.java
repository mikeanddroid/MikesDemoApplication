package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.activities.LandingPage;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.base.MapsClusterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GiveMeWingzzz on 9/28/2017.
 */

public class DemoDetails extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.details_git_repo_desc)
    TextView git_repo_desc;

    @BindView(R.id.git_item_details_git_repo_name)
    TextView git_repo_name;

    @BindView(R.id.git_item_details_git_repo_link)
    TextView git_repo_link;

    @BindView(R.id.details_git_repo_button)
    Button git_repo_action;

    int repo_position;

    AppConstants.TransitionType type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.git_item_details);

        type = (AppConstants.TransitionType) getIntent().getSerializableExtra(AppConstants.KEY_ANIM_TYPE);

        ButterKnife.bind(this);

        initAnimation();

        Intent details = getIntent();

        String repo_name = details.getStringExtra(AppConstants.GIT_REPO_NAME_KEY);
        String repo_desc = details.getStringExtra(AppConstants.GIT_REPO_DESC_KEY);
        repo_position = details.getIntExtra(AppConstants.GIT_REPO_POSITION_KEY, -1);

        git_repo_name.setText(repo_name);
        git_repo_desc.setText(repo_desc);

        git_repo_link.setText(fromHtml(getResources().getString(R.string.git_repo_link)));

        git_repo_link.setOnClickListener(this);

        git_repo_action.setOnClickListener(this);

        // For overlap between Exiting  MainActivity.java and Entering TransitionActivity.java
        getWindow().setAllowEnterTransitionOverlap(false);

    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.git_item_details_git_repo_link:

                Uri uri = Uri.parse(getResources().getString(R.string.git_repo_link));

                Intent linkIntent = new Intent(Intent.ACTION_VIEW, uri);

                Intent chooser = Intent.createChooser(linkIntent, "Demo List");
                startActivity(chooser);

                break;

        }

        Intent intent = null;
        switch (repo_position) {

            case 0:
                intent = new Intent(DemoDetails.this, MapsClusterActivity.class);
                break;
            case 1:
                intent = new Intent(DemoDetails.this, LandingPage.class);
                break;
            case 2:
                break;
            case 3:
                break;

        }

        startActivity(intent);

    }

    private void initAnimation() {

        switch (type) {

            case Explode: { // For Explode By Code
                Explode enterTransition = new Explode();
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                getWindow().setEnterTransition(enterTransition);
                break;
            }

            case Slide: { // For Slide By Code
                Slide enterTransition = new Slide();
                enterTransition.setSlideEdge(Gravity.TOP);
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
                getWindow().setEnterTransition(enterTransition);
                break;
            }

            case Fade: { // For Fade By Code
                Fade enterTransition = new Fade();
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                getWindow().setEnterTransition(enterTransition);
                break;
            }

        }

    }


}
