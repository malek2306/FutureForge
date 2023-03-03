/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.entities;

import java.time.LocalDate;
import java.util.Date;
import javafx.scene.control.ComboBox;






public class Evenement {
    private int id;
    private String nom;
    private String type;
    private String description;
    private LocalDate date;
    private Categories Categories ;

    public Evenement(int id, String type, String description, LocalDate date, Categories Categories) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
        this.Categories = Categories;
    }

    public Evenement(String nom, String type, LocalDate date, Categories Categories) {
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.Categories = Categories;
    }

    public Evenement(String nom, String type, String description, LocalDate date, Categories Categories) {
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.date = date;
        this.Categories = Categories;
    }

   

   
    public Evenement() {
    }
    
    // Getters and setters

    public Evenement(int id) {
        this.id = id;
    }

    public Evenement(int id, String nom, String type, String description, LocalDate date, Categories Categories) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.date = date;
        this.Categories = Categories;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Categories getCategories() {
        return Categories;
    }

    public void setCategories(Categories Categories) {
        this.Categories = Categories;
    }
    

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", description=" + description + ", date=" + date + ", Categories=" + Categories + '}';
    }

  

    
}
