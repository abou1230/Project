package com.example.farahasmaabobakermohamed.model;

public class AdresseModel {

    String userAdresse;
    boolean isSelected;

    public AdresseModel() {
    }

    public AdresseModel(String userAdresse, boolean isSelected) {
        this.userAdresse = userAdresse;
        this.isSelected = isSelected;
    }

    public String getUserAdresse() {
        return userAdresse;
    }

    public void setUserAddress(String userAdresse) {
        this.userAdresse = userAdresse;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

