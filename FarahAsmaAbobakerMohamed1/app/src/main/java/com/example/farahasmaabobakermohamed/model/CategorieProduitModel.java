package com.example.farahasmaabobakermohamed.model;

public class CategorieProduitModel {
    String img_url;
    String nom;
    String type;

    public CategorieProduitModel(String img_url, String nom, String type) {
        this.img_url = img_url;
        this.nom = nom;
        this.type = type;
    }

    public CategorieProduitModel() {
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
}
