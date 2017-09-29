package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

/**
 * Created by GiveMeWingzzz on 9/27/2017.
 */

public class GitRepo {

    private String repoName;
    private int repoImage;
    private String repoDesc;
    private boolean isActive;

    public GitRepo(String repoName, String repoDesc, int repoImage, boolean isActive) {
        this.repoName = repoName;
        this.repoDesc = repoDesc;
        this.repoImage = repoImage;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRepoDesc() {
        return repoDesc;
    }

    public void setRepoDesc(String repoDesc) {
        this.repoDesc = repoDesc;
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
