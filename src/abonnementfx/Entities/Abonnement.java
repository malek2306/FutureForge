/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementFX.Entities;
import java.io.File;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.sql.Date;



public class Abonnement {
    private int id_ab;
    private String nom;
    private String prenom ;
    private String email ;
    private String identifiant ;
    private String cin ;
    private TypeAbonnement type;
    private Date dateDebut;
    private Date dateFin;
    private double prix;
    private int id_offre;
    private String img;
    public Abonnement () {}

    public Abonnement(String nom, String prenom, String img, String email, String identifiant, String cin, TypeAbonnement type, Date dateDebut, Date dateFin, double prix, int id_offre) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.identifiant = identifiant;
        this.cin = cin;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.id_offre = id_offre;
        this.img = img;
    }
    public Abonnement(String nom, String prenom, String email, String identifiant, String cin, TypeAbonnement type, Date dateDebut, Date dateFin, double prix, int id_offre) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.identifiant = identifiant;
        this.cin = cin;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.id_offre = id_offre;
    }

    public Abonnement(int id ,String nom, String prenom, String img, String email, String identifiant, String cin, TypeAbonnement type, Date dateDebut, Date dateFin, double prix, int id_offre) {
        this.id_ab=id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.identifiant = identifiant;
        this.cin = cin;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.id_offre = id_offre;
        this.img = img;
    }
    


    public int getId_ab() {
        return id_ab;
    }

   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public TypeAbonnement getType() {
        return type;
    }

    public void setType(TypeAbonnement type) {
        this.type = type;
    }

   
    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    

    

   

  

    
}
	
