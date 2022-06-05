package com.example.farahasmaabobakermohamed.model;

public class SeanceModel {
    String img_url;
    String nom;
    String description;

    public SeanceModel() {
    }

    public SeanceModel(String img_url, String nom, String description) {
        this.img_url = img_url;
        this.nom = nom;
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
