package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.PathMotion;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.Dagger2HomeActivity;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes.LoadMoreListActivity;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.activities.LandingPage;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.loginflow.LoginActivityMVC;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.splashview.SplashScreenActivity;
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

    AppConstants.TransitionType transitionType;
    AppConstants.TransitionType transitionType2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.git_item_details);

        transitionType = (AppConstants.TransitionType) getIntent().getSerializableExtra(AppConstants.KEY_ANIM_TYPE);
        transitionType2 = (AppConstants.TransitionType) getIntent().getSerializableExtra(AppConstants.KEY_ANIM_TYPE2);

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

        // For overlap between Exiting  MainActivityMVC.java and Entering TransitionActivity.java
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
                intent = new Intent(DemoDetails.this, LoadMoreListActivity.class);
                break;
            case 3:
                intent = new Intent(DemoDetails.this, Dagger2HomeActivity.class);
                break;
            case 4:
                intent = new Intent(DemoDetails.this, LoginActivityMVC.class);
                break;
            case 5:
                intent = new Intent(DemoDetails.this, SplashScreenActivity.class);
                break;

        }

        if (intent != null) {
            startActivity(intent);
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No Application Found");
            builder.setMessage("No application found with that selected item in the list. The application will be added soon and you will be notified.");
            builder.setPositiveButton("Main Menu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }

    }

    private void initAnimation() {

        if (transitionType != null) {
            switch (transitionType) {

                case Explode: { // For Explode By Code
                    Explode enterTransition = new Explode();
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }

                case Slide: { // For Slide By Code
                    Slide enterTransition = new Slide();
                    enterTransition.setSlideEdge(Gravity.END);
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_very_long));
//                enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }

                case Fade: { // For Fade By Code
                    Fade enterTransition = new Fade();
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }

            }
        }

        if (transitionType2 != null) {
            switch (transitionType2) {

                case Explode: { // For Explode By Code
                    Explode enterTransition = new Explode();
                    enterTransition.setPathMotion(new PathMotion() {
                        @Override
                        public Path getPath(float v, float v1, float v2, float v3) {
                            return null;
                        }
                    });
                    enterTransition.setMode(Visibility.MODE_IN);
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }

                case Slide: { // For Slide By Code
                    Slide enterTransition = new Slide();
                    enterTransition.setSlideEdge(Gravity.END);
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_very_long));
//                enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }

                case Fade: { // For Fade By Code
                    Fade enterTransition = new Fade();
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }

            }
        }

    }


}
