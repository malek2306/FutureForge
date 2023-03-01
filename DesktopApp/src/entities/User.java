/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Blob;

/**
 *
 * @author Name
 */
public  class User {
    private int id;
    private String nom, prenom,username, datec, role, email, tel, mdp,mat;
    Blob pfp;

    public User() {
    }

    public User(String nom,String prenom , String username, String email, String tel,String mdp,String role,Blob pfp,String mat) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.mdp = mdp;
        this.role = role;
        this.pfp=pfp;
        this.mat=mat;
    }

    public User(int id,String nom,String prenom , String username, String email, String tel,String mdp,String role,Blob pfp,String mat) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.mdp = mdp;
        this.role = role;
        this.pfp=pfp;
        this.mat=mat;
    }
    public User(int id,String nom,String prenom , String username, String email, String tel,String mdp,String role,String mat) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.mdp = mdp;
        this.role = role;
        this.mat=mat;
    }
     public User(int id,String nom,String prenom , String username, String email, String tel,String mdp,String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.mdp = mdp;
        this.role = role;
        this.mat=mat;
    }
    //getter
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }
    
    public String getMdp() {
        return mdp;
    }
    
    public String getRole() {
        return role;
    }
    
    public Blob getPfp() {
        return pfp;
    }
    
    public String getMat() {
        return mat;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void setPfp(Blob pfp) {
        this.pfp = pfp;
    }
    
    public void setPfp(String mat) {
        this.mat = mat;
    }

    @Override
    public String toString() {
        return "user{" + "nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", email=" + email + ", tel=" + tel + ", mdp=" + mdp + ", role=" + role + ", pfp=" + pfp + ", pfp=" + mat + "}";
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
