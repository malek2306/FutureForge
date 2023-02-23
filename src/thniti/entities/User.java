/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.entities;

/**
 *
 * @author 21692
 */
public class User {
    private int id_u;
  
    private String nom_u,penom_u;
  
    private String email_u;
    
    private int tel_u;
    private String role_u;
   
public User(){
    } 

    public User(String nom_u, String penom_u, String email_u, int tel_u, String role_u) {
        this.nom_u = nom_u;
        this.penom_u = penom_u;
        this.email_u = email_u;
        this.tel_u = tel_u;
        this.role_u = role_u;
    }

    



    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public String getNom_u() {
        return nom_u;
    }

    public void setNom_u(String nom_u) {
        this.nom_u = nom_u;
    }

    public String getPenom_u() {
        return penom_u;
    }

    public void setPenom_u(String penom_u) {
        this.penom_u = penom_u;
    }

    public String getEmail_u() {
        return email_u;
    }

    public void setEmail_u(String email_u) {
        this.email_u = email_u;
    }

    public int getTel_u() {
        return tel_u;
    }

    public void setTel_u(int tel_u) {
        this.tel_u = tel_u;
    }

    public String getRole_u() {
        return role_u;
    }

    public void setRole_u(String role_u) {
        this.role_u = role_u;
    }



    
    


}
