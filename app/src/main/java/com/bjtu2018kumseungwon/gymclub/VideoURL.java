package com.bjtu2018kumseungwon.gymclub;


public class VideoURL {

    String videoURL;
    String class_Name;

    public VideoURL(String videoURL) {
        this.videoURL = videoURL;
        int idx = videoURL.indexOf("Gymvideo/");
        int idx2 = videoURL.indexOf(".mp4");
        class_Name = videoURL.substring(idx + 1,idx2);
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
