/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Firas
 */
public class Offre {
    private int id_offre,places_dispo;
    private String image_vehicule,nom_chauff,prenom_chauff,num_chauff,heure,trajet,date_offre;
    private double prix_offre;
  

    public Offre(int id_offre, String image_vehicule, String nom_chauff, String prenom_chauff, String num_chauff, String heure, String trajet, String date_offre,int places_dispo, double prix_offre) {
        this.id_offre = id_offre;
        this.image_vehicule = image_vehicule;
        this.nom_chauff = nom_chauff;
        this.prenom_chauff = prenom_chauff;
        this.num_chauff = num_chauff;
        this.heure = heure;
        this.trajet = trajet;
        this.date_offre = date_offre;
        this.places_dispo = places_dispo;
        this.prix_offre = prix_offre;
    }
    public Offre(String image_vehicule, String nom_chauff, String prenom_chauff, String num_chauff, String heure, String trajet, String date_offre,int places_dispo, double prix_offre) {
        this.image_vehicule = image_vehicule;
        this.nom_chauff = nom_chauff;
        this.prenom_chauff = prenom_chauff;
        this.num_chauff = num_chauff;
        this.heure = heure;
        this.trajet = trajet;
        this.date_offre = date_offre;
        this.places_dispo = places_dispo;
        this.prix_offre = prix_offre;
    }


    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public void setImage_vehicule(String image_vehicule) {
        this.image_vehicule = image_vehicule;
    }

    public void setNom_chauff(String nom_chauff) {
        this.nom_chauff = nom_chauff;
    }

    public void setPrenom_chauff(String prenom_chauff) {
        this.prenom_chauff = prenom_chauff;
    }

    public void setNum_chauff(String num_chauff) {
        this.num_chauff = num_chauff;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setTrajet(String trajet) {
        this.trajet = trajet;
    }

    public void setDate_offre(String date_offre) {
        this.date_offre = date_offre;
    }

    public void setPlaces_dispo(int places_dispo) {
        this.places_dispo = places_dispo;
    }

    public void setPrix_offre(double prix_offre) {
        this.prix_offre = prix_offre;
    }

    public int getId_offre() {
        return id_offre;
    }

    public String getImage_vehicule() {
        return image_vehicule;
    }

    public String getNom_chauff() {
        return nom_chauff;
    }

    public String getPrenom_chauff() {
        return prenom_chauff;
    }

    public String getNum_chauff() {
        return num_chauff;
    }

    public String getHeure() {
        return heure;
    }

    public String getTrajet() {
        return trajet;
    }

    public String getDate_offre() {
        return date_offre;
    }
     public int getPlaces_dispo() {
        return places_dispo;
    }

    public double getPrix_offre() {
        return prix_offre;
    }

    @Override
    public String toString() {
        return "Offre{" + "image_vehicule=" + image_vehicule + ", nom_chauff=" + nom_chauff + ", prenom_chauff=" + prenom_chauff + ", num_chauff=" + num_chauff + ", heure=" + heure + ", trajet=" + trajet + ", date_offre=" + date_offre + ", places_dispo=" + places_dispo + ", prix_offre=" + prix_offre + '}';
    }


    

    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offre other = (Offre) obj;
        if (this.id_offre != other.id_offre) {
            return false;
        }
        return true;
    }
}
