/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author LENOVO
 */
public class Reservation_covoiturage {

   
    private int id_reservation;
    private String nom, prenom;
    private String pnt_rencontre;
    private String distination;
    private int nbr_place ;
    private String date ;

    public Reservation_covoiturage() {
    }
    
    public Reservation_covoiturage(String nom, String prenom,String pnt_rencontre ,String distination, int nbr_place, String date) {
        this.nom = nom;
        this.prenom = prenom;
        this.nbr_place = nbr_place;
        this.date = date;
        this.distination=distination;
        this.pnt_rencontre=pnt_rencontre;
    }

    public Reservation_covoiturage(int id_reservation, String nom, String prenom ,String pnt_rencontre ,String distination ,int nbr_place, String date) {
        this.id_reservation = id_reservation;
        this.nom = nom;
        this.prenom = prenom;
        this.nbr_place = nbr_place;
        this.date=date;
        this.distination=distination;
        this.pnt_rencontre=pnt_rencontre;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public String getPnt_rencontre() {
        return pnt_rencontre;
    }

    public String getDistination() {
        return distination;
    }

    public String getDate() {
        return date;
    }

    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public void setPnt_rencontre(String pnt_rencontre) {
        this.pnt_rencontre = pnt_rencontre;
    }

    public void setDistination(String distination) {
        this.distination = distination;
    }

    public void setDate(String date) {
        this.date = date;
    }

   

    @Override
    public String toString() {
        return "Reservation_bus{" + "nom=" + nom + ", prenom=" + prenom + ", pnt_rencontre=" + pnt_rencontre + ", distination=" + distination +  '}';
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
        final Reservation_covoiturage other = (Reservation_covoiturage) obj;
        if (this.id_reservation != other.id_reservation) {
            return false;
        }
        return true;
    }
    
    
    
}

