package com.example.farahasmaabobakermohamed.model;

public class AddproductModel {
    String img_url;
    String description;
    String nom;
    int prix;
    String type;

    public AddproductModel() {
    }

    public AddproductModel(String img_url, String description, String nom, int prix, String type) {
        this.img_url = img_url;
        this.description = description;
        this.nom = nom;
        this.prix = prix;
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
