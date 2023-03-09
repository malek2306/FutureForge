/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementFX.Entities;

/**
 *
 * @author GHADA
 */
public class Categorie {
   private String type_abonnement ;
   private int id_type ;

    public Categorie(String type_abonnement, int id_type) {
        this.type_abonnement = type_abonnement;
        this.id_type = id_type;
    }

    public Categorie(String type_abonnement) {
        this.type_abonnement = type_abonnement;
    }

    public String getType_abonnement() {
        return type_abonnement;
    }

    public int getId_type() {
        return id_type;
    }

    public void setType_abonnement(String type_abonnement) {
        this.type_abonnement = type_abonnement;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id_type;
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
        final Categorie other = (Categorie) obj;
        if (this.id_type != other.id_type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categorie{" + "type_abonnement=" + type_abonnement + '}';
    }
   
}
