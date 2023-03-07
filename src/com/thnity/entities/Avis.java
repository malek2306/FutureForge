package com.thnity.entities;

public class Avis {

    private int id;
    private int idOffre;
    private int idEtudiant;
    private int rate;
    private String descriptionAvis;

    public Avis(int id, int idOffre, int idEtudiaz, int rate, String descriptionAvis) {
        this.id = id;
        this.idOffre = idOffre;
        this.idEtudiant = idEtudiant;
        this.rate = rate;
        this.descriptionAvis = descriptionAvis;
    }

    public Avis(int idOffre, int idEtudiant, int rate, String descriptionAvis) {
        this.idOffre = idOffre;
        this.idEtudiant = idEtudiant;
        this.rate = rate;
        this.descriptionAvis = descriptionAvis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescriptionAvis() {
        return descriptionAvis;
    }

    public void setDescriptionAvis(String descriptionAvis) {
        this.descriptionAvis = descriptionAvis;
    }
}