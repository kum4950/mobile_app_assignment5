package com.bjtu2018kumseungwon.gymclub;


public class item_activity {
    String class_Name;
    int profilePhoto;
    int nbViews;
    String videoURL;

    public item_activity() {

    }

    public item_activity(String class_Name, int profilePhoto, int nbViews,String videoURL) {

        this.class_Name = class_Name;
        this.profilePhoto = profilePhoto;
        this.videoURL = videoURL;
        this.nbViews= nbViews;

    }

    public String getVideoURL() { return videoURL;  }

    public String getClass_Name() {
        return class_Name;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public int getNbViews() {

        return nbViews;
    }

    public void setVideoURL(String videoURL) {this.videoURL = videoURL; }

    public void setClass_Name(String profileName) {
        this.class_Name = profileName;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setNbViews(int nbViews) {
        this.nbViews = nbViews;
    }
}


