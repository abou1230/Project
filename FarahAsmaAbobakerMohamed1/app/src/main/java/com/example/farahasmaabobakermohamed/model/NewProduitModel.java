package com.example.farahasmaabobakermohamed.model;

import java.io.Serializable;

public class NewProduitModel implements Serializable {
    String img_url;
    String descrip;
    String nom;
    String notation;
    int prix;
    String type;

    public NewProduitModel() {
    }

    public NewProduitModel(String img_url, String descrip, String nom, String notation, int prix, String type) {
        this.img_url = img_url;
        this.descrip = descrip;
        this.nom = nom;
        this.notation = notation;
        this.prix = prix;
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
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
