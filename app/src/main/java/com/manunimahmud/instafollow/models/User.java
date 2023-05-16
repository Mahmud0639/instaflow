package com.manunimahmud.instafollow.models;

public class User {
    private String userName, email, password, profession, coverPhoto, profilePic;

    public User() {

    }

    public User(String userName, String email, String password, String profession) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.profession = profession;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCoverPhoto(){
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto){
        this.coverPhoto = coverPhoto;
    }

    public String getProfilePic(){
        return profilePic;
    }
    public void setProfilePic(String profilePic){
        this.profilePic = profilePic;
    }
}
