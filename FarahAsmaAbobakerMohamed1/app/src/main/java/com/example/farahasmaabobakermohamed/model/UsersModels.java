package com.example.farahasmaabobakermohamed.model;

public class UsersModels {
    String name;
    String email;
    String password;
    String profileImg;
    String telephone;

    public UsersModels() {
    }

    public UsersModels(String name, String email, String password, String profileImg, String telephone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImg = profileImg;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
