package com.manunimahmud.instafollow.models;

public class DashboardModel {
    private int postImage, userImage;
    private String userName, profession, like, comment, share, description;

    public DashboardModel(int postImage, int userImage, String userName, String profession, String like, String comment, String share, String description){
        this.postImage = postImage;
        this.userImage = userImage;
        this.userName = userName;
        this.profession = profession;
        this.like = like;
        this.comment = comment;
        this.share = share;
        this.description = description;
    }
    public int getPostImage(){
        return postImage;
    }
    public void setPostImage(int postImage){
        this.postImage = postImage;
    }
    public int getUserImage(){
        return userImage;
    }
    public void setUserImage(int userImage){
        this.userImage = userImage;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getProfession(){
        return profession;
    }
    public void setProfession(String profession){
        this.profession = profession;
    }
    public String getLike(){
        return like;
    }
    public void setLike(String like){
        this.like = like;
    }
    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public String getShare(){
        return share;
    }
    public void setShare(String share){
        this.share = share;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
}
