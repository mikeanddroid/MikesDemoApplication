package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

import android.content.Context;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;

import java.util.ArrayList;

public class AppConstants {

    public static class API_KEYS {
        public static final String MAP_API_KEY_8 = "AIzaSyB_4ArrUzutNyoc24_mG5NVV0TUAP5TsZo";
    }

    public static final String BASE_URL = "https://maps.googleapis.com";
    public static final String INSTA_BASE_URL = "https://maps.googleapis.com";
    public static final String BASE_IMAGE_URL = "https://maps.googleapis.com";

    // Intent Keys
    public static final String IMAGE_URL_KEY = "IMAGE_URL_KEY";
    public static final String LOCATION_NAME_KEY = "LOCATION_NAME_KEY";
    public static final String LOCATION_ADDRESS_KEY = "LOCATION_ADDRESS_KEY";
    public static final String LOCATION_GEO_KEY = "LOCATOIN_GEO_KEY";
    public static final String LOCATION_RATING_KEY = "LOCATION_RATING_KEY";
    public static final String LOCATION_IMAGE_REFERENCE = "LOCATION_IMAGE_REFERENCE";

    public static final String GIT_REPO_NAME_KEY = "GIT_REPO_NAME_KEY";
    public static final String GIT_REPO_DESC_KEY = "GIT_REPO_DESC_KEY";
    public static final String GIT_REPO_POSITION_KEY = "GIT_REPO_POSITION_KEY";
    public static final String GIT_REPO_IMAGE_KEY = "GIT_REPO_IMAGE_KEY";

    public static ArrayList<String> getApiKeys() {

        ArrayList<String> apikeys = new ArrayList<>();
        apikeys.add(API_KEYS.MAP_API_KEY_8);

        return apikeys;

    }

    public String getCurrentApiKey() {

        Prefs prefs = Prefs.with(MikesDemoApplication.getInstance());

        if (prefs.isSuccess()) {
            return prefs.getCurrentApiKey();
        }
        return "";
    }

    public static ArrayList<GitRepo> getDefaultList(Context context) {

        String sampleDesc = context.getResources().getString(R.string.sample_desc);
        String instaDesc = context.getResources().getString(R.string.insta_desc);
        String yelpDesc = context.getResources().getString(R.string.yelp_desc);
        String mapsDesc = context.getResources().getString(R.string.maps_desc);

        ArrayList<GitRepo> gitRepoArrayList = new ArrayList<>();

        gitRepoArrayList.add(new GitRepo(context.getString(R.string.maps_cluster_demo), mapsDesc, R.mipmap.github, true));
        gitRepoArrayList.add(new GitRepo(context.getString(R.string.insta_auth_demo), instaDesc, R.mipmap.github, true));
        gitRepoArrayList.add(new GitRepo(context.getString(R.string.yelp_auth_demo), yelpDesc, R.mipmap.github, true));

        for (int i = gitRepoArrayList.size() - 1; i < 10; i++) {
            gitRepoArrayList.add(new GitRepo("Demo App " + i, "Sample Description : " + sampleDesc, R.mipmap.github, false));
        }

        return gitRepoArrayList;

    }

    public static final String KEY_ANIM_TYPE = "KEY_ANIM_TYPE";
    public static final String KEY_TITLE = "KEY_TITLE";

    public static enum TransitionType {
        Explode, Slide, Fade
    }

}
