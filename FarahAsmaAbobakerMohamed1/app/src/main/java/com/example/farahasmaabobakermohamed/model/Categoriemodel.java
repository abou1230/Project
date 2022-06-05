package com.example.farahasmaabobakermohamed.model;

import java.io.Serializable;

public class Categoriemodel implements Serializable {
    String img_url;
    String nom;
    String type;
    String description;

    public Categoriemodel(String img_url, String nom, String type, String description) {
        this.img_url = img_url;
        this.nom = nom;
        this.type = type;
        this.description = description;
    }

    public Categoriemodel() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
