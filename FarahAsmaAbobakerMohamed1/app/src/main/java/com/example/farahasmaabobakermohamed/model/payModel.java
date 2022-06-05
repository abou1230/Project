package com.example.farahasmaabobakermohamed.model;

public class payModel {
    String Currenttime;
    String Currentdate;
    String Image;
    String nomproduit;
    String prixproduit;
    int totalprix;
    String totalquantite;
    String Status;
    String documentId;

    public payModel() {
    }

    public payModel(String currenttime, String currentdate, String image, String nomproduit, String prixproduit, int totalprix, String totalquantite,String Status) {
        Currenttime = currenttime;
        Currentdate = currentdate;
        Image = image;
        this.nomproduit = nomproduit;
        this.prixproduit = prixproduit;
        this.totalprix = totalprix;
        this.totalquantite = totalquantite;
        this.Status=Status;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getCurrenttime() {
        return Currenttime;
    }

    public void setCurrenttime(String currenttime) {
        Currenttime = currenttime;
    }

    public String getCurrentdate() {
        return Currentdate;
    }

    public void setCurrentdate(String currentdate) {
        Currentdate = currentdate;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public String getPrixproduit() {
        return prixproduit;
    }

    public void setPrixproduit(String prixproduit) {
        this.prixproduit = prixproduit;
    }

    public int getTotalprix() {
        return totalprix;
    }

    public void setTotalprix(int totalprix) {
        this.totalprix = totalprix;
    }

    public String getTotalquantite() {
        return totalquantite;
    }

    public void setTotalquantite(String totalquantite) {
        this.totalquantite = totalquantite;
    }

    public String getStatus() { return Status; }
    public void setStatus(String Status) { this.Status = Status; }

}
