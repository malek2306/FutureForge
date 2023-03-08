/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.mail.Message;

/**
 *
 * @author LENOVO
 */

public class Reservation_bus {

    public static void send(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id_reservation_bus;
    private String nom, prenom,date,email,destination;
    private int num_place ;

    public Reservation_bus() {
    }

    public Reservation_bus(String nom, String prenom, String date, String email, String destination, int num_place) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.email = email;
        this.destination = destination;
        this.num_place = num_place;
    }

    public Reservation_bus(int id_reservation_bus, String nom, String prenom , int num_place, String date, String email,String destination) {
        this.id_reservation_bus = id_reservation_bus;
        this.nom = nom;
        this.prenom = prenom;
        this.num_place = num_place;
        this.date = date;
        this.email = email;
        this.destination = destination;
        
    }

   

  
    
    public Reservation_bus(String nom, String prenom,int num_place) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_place = num_place;
    }

    public Reservation_bus(int id_reservation_bus, String nom, String prenom , int num_place) {
        this.id_reservation_bus = id_reservation_bus;
        this.nom = nom;
        this.prenom = prenom;
        this.num_place = num_place;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId_reservation_bus() {
        return id_reservation_bus;
    }

    public int getNum_place() {
        return num_place;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNum_place(int num_place) {
        this.num_place = num_place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Reservation_bus{" + "id_reservation_bus=" + id_reservation_bus + ", nom=" + nom + ", prenom=" + prenom + ", date=" + date + ", email=" + email + ", destination=" + destination + ", num_place=" + num_place + '}';
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
        final Reservation_bus other = (Reservation_bus) obj;
        if (this.id_reservation_bus != other.id_reservation_bus) {
            return false;
        }
        return true;
    }

   


    
}
