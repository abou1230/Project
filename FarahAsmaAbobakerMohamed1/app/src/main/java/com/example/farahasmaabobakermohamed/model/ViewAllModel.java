package com.example.farahasmaabobakermohamed.model;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    String img_url;
    String nom;
    String description;
    int prix;
    String type;

    public ViewAllModel() {
    }

    public ViewAllModel(String img_url, String nom, String description, int prix, String type) {
        this.img_url = img_url;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.type = type;
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
