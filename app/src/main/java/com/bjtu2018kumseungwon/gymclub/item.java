package com.bjtu2018kumseungwon.gymclub;


public class item {
    int backgroud;
    String profileName;
    int profilePhoto;
    int nbFollowers;

    public item() {
    }

    public item(int backgroud, String profileName, int profilePhoto, int nbFollowers) {
        this.backgroud = backgroud;
        this.profileName = profileName;
        this.profilePhoto = profilePhoto;
        this.nbFollowers = nbFollowers;
    }

    public int getBackgroud() {
        return backgroud;
    }

    public String getProfileName() {
        return profileName;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public int getNbFollowers() {
        return nbFollowers;
    }

    public void setBackgroud(int backgroud) {
        this.backgroud = backgroud;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setNbFollowers(int nbFollowers) {
        this.nbFollowers = nbFollowers;
    }
}
