package com.thnity.entities;


import java.time.LocalDate;

public class Offre {

    private int id;
    private int idEtudiant;
    private String imageVehicule;
    private String prenomChauff;
    private String numChauff;
    private LocalDate dateOffre;
    private String heure;
    private int prixOffre;
    private String depart;
    private String destination;
    private int placesDispo;

    public Offre(int id, int idEtudiant, String imageVehicule, String prenomChauff, String numChauff, LocalDate dateOffre, String heure, int prixOffre, String depart, String destination, int placesDispo) {
        this.id = id;
        this.idEtudiant = idEtudiant;
        this.imageVehicule = imageVehicule;
        this.prenomChauff = prenomChauff;
        this.numChauff = numChauff;
        this.dateOffre = dateOffre;
        this.heure = heure;
        this.prixOffre = prixOffre;
        this.depart = depart;
        this.destination = destination;
        this.placesDispo = placesDispo;
    }

    public Offre(int idEtudiant, String imageVehicule, String prenomChauff, String numChauff, LocalDate dateOffre, String heure, int prixOffre, String depart, String destination, int placesDispo) {
        this.idEtudiant = idEtudiant;
        this.imageVehicule = imageVehicule;
        this.prenomChauff = prenomChauff;
        this.numChauff = numChauff;
        this.dateOffre = dateOffre;
        this.heure = heure;
        this.prixOffre = prixOffre;
        this.depart = depart;
        this.destination = destination;
        this.placesDispo = placesDispo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getImageVehicule() {
        return imageVehicule;
    }

    public void setImageVehicule(String imageVehicule) {
        this.imageVehicule = imageVehicule;
    }

    public String getPrenomChauff() {
        return prenomChauff;
    }

    public void setPrenomChauff(String prenomChauff) {
        this.prenomChauff = prenomChauff;
    }

    public String getNumChauff() {
        return numChauff;
    }

    public void setNumChauff(String numChauff) {
        this.numChauff = numChauff;
    }

    public LocalDate getDateOffre() {
        return dateOffre;
    }

    public void setDateOffre(LocalDate dateOffre) {
        this.dateOffre = dateOffre;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getPrixOffre() {
        return prixOffre;
    }

    public void setPrixOffre(int prixOffre) {
        this.prixOffre = prixOffre;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPlacesDispo() {
        return placesDispo;
    }

    public void setPlacesDispo(int placesDispo) {
        this.placesDispo = placesDispo;
    }
}
