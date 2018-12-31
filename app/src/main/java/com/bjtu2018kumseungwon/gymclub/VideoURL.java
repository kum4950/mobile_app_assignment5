package com.bjtu2018kumseungwon.gymclub;


public class VideoURL {

    String videoURL;
    String class_Name;
    int views;
    String trainer;
    String frontURL="http://10.0.2.2:8080/Gymvideo/";
    String mp4 = ".mp4";
    public VideoURL(String class_Name,int views) {
        this.class_Name = class_Name;
        this.videoURL = frontURL+class_Name+mp4;
        this.views = views;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getClass_Name() {
        return class_Name;
    }

    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name;
    }

}
