/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.services;

import thniti.entities.Messagerie;
import thniti.entities.User;
import thniti.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author 21692
 */
public class ServiceMessagerie implements IService<Messagerie>{
    
    
    Connection cnx ;
Statement stm;
   
        public ServiceMessagerie(){
            cnx=DataSource.getInstance().getCnx();
        }
        @Override
   public void ajouter(Messagerie message) {
       
      if (message.getContenuM().isEmpty() || message.getEtat().isEmpty() ) {
    throw new IllegalArgumentException("Veuillez renseigner tous les champs !");
}
         if (!message.getContenuM().matches("^[A-Z].*")) {
    throw new IllegalArgumentException("Le champ contenu et etat doit commencer par une lettre majuscule !");
}
if (message.getContenuM().length() > 10) {
        throw new IllegalArgumentException("Le titre de l'article ne doit pas dépasser 10 caractères !");
}

         try {
            String qry = "INSERT INTO messagerie (contenuM,etat) VALUES (  '" + message.getContenuM() + "', '" + message.getEtat() + "')";
            stm = cnx.createStatement();
            System.out.println("message Added Successfully !");
            stm.executeUpdate(qry);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
       
    }
    

    @Override
    public void supprimer(int id) {
       try {
     
       String req = "DELETE FROM messagerie WHERE id_M=" +id ;
        stm = cnx.createStatement();
            System.out.println("Deleted Successfully !");
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }}

    @Override
    public void modifier(Messagerie p,int id) {
             try {
      String qry=   " UPDATE Messagerie SET " + "    contenuM= ?, etat = ?  WHERE id_M= " + id;
               PreparedStatement ps = cnx.prepareStatement(qry);
        ps.setString(1, p.getContenuM());
        ps.setString(2, p.getEtat());
        //ps.setString(3, t.getObjet());
       // ps.setString(4, t.getEtat());
       
        ps.executeUpdate();
        System.out.println("message modifiée");
    } catch (SQLException ex) {
        System.out.println("Error updating message: " + ex.getMessage());
    }
        /*PreparedStatement ps = cnx.prepareStatement(qry);
        try {
            ps.setString(1, p.getContenuM());
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMessagerie.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps.setString(2, p.getEtat());
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMessagerie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        ps.executeUpdate();
        System.out.println("message modifiée");
    } catch (SQLException ex) {
        System.out.println("Error updating reclamation: " + ex.getMessage());
    }*/
    }
    

    @Override
    public List<Messagerie> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Messagerie getOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

   
}
