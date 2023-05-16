package com.manunimahmud.instafollow.models;

public class CommentModel {
    private int image;
    private String comment, time;

    public CommentModel(int image, String comment, String time) {
        this.image = image;
        this.comment = comment;
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
