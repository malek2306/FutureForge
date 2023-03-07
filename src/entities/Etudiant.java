/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;

/**
 *
 * @author Name
 */
public class Etudiant extends User {
    public Etudiant(String nom,String prenom , String username, String email, String tel,String mdp,String role,InputStream pfp,String mat) {
        super (nom,prenom , username, email, tel,mdp,role,pfp,mat);
    }
    
}
