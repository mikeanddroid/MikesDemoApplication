package com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.data;

/**
 * Created by GiveMeWingzzz on 9/27/2017.
 */

public class DemosRepos {

    private String repoName;
    private int repoImage;

    public DemosRepos(String repoName, int repoImage) {
        this.repoName = repoName;
        this.repoImage = repoImage;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public int getRepoImage() {
        return repoImage;
    }

    public void setRepoImage(int repoImage) {
        this.repoImage = repoImage;
    }
}
