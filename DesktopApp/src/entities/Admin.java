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
public class Admin extends User {
    public Admin(String nom,String prenom , String username, String email, String tel,String mdp,String role,Blob pfp,String mat) {
        super (nom,prenom , username, email, tel,mdp,role,pfp,mat);
    }
    
}
