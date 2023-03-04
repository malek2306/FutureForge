/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.entities;

import java.util.Objects;

/**
 *
 * @author farah
 */
public class Categories {
     private int id ;
    private String nom ;
    private String description;
   private byte[] photo ;
   
    public Categories() {
    }

    public Categories(String nom, String description, byte[] photo) {
        this.nom = nom;
        this.description = description;
        this.photo = photo;
    }

    public Categories(int id, String nom, String description, byte[] photo) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
    }

        

   

  
   
    
   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public byte[] getPhoto() {
         return photo;
     }
     
     public void setPhoto(byte[] photo) {
         this.photo = photo;
     }

     @Override
    public String toString() {
        return Integer.toString(id);}
    

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Categories other = (Categories) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        return true;
    }

   
    

  
    }

