/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementFX.Entities;

import java.sql.Date;

/**
 *
 * @author GHADA
 */
public class OffreAbonnement {
  private int id_ofab;
  private String nom;
  private String description ;
  private double reduction ;
  private TypeAbonnement type ;
  private Date date_debut ;
  private Date date_fin ;

    public int getId_ofab() {
        return id_ofab;
    }

    public void setId_ofab(int id_ofab) {
        this.id_ofab = id_ofab;
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

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }

    public TypeAbonnement getType() {
        return type;
    }

    public void setType(TypeAbonnement type) {
        this.type = type;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public OffreAbonnement() {
    }

    public OffreAbonnement(int id_ofab, String nom, String description, double reduction,TypeAbonnement type, Date date_debut, Date date_fin) {
        this.id_ofab = id_ofab;
        this.nom = nom;
        this.description = description;
        this.reduction = reduction;
        this.type=type;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    public OffreAbonnement( String nom, String description, double reduction, TypeAbonnement type,Date date_debut, Date date_fin) {
        this.nom = nom;
        this.description = description;
        this.reduction = reduction;
        this.type = type;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "OffreAbonnement{" + "id_ofab=" + id_ofab + ", nom=" + nom + ", description=" + description + ", reduction=" + reduction + ", type=" + type + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
   
    
}
