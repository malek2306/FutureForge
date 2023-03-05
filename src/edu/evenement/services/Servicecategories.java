/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.services;

import edu.evenement.entities.Categories;
import edu.evenement.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.GridPane;

/**
 *
 * @author farah
 */
public class Servicecategories {
    Connection cnx = DataSource.getInstance().getCnx();

   
   public void ajouter(Categories c) throws SQLException {
        
            String req = "INSERT INTO `categories` (`nom`, `description`,`photo`) VALUES ('" + c.getNom() + "', '" + c.getDescription() + "', '" + c.getPhoto() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categories created !");
        
    }

   public void supprimer(int id) {
        try {
            String req = "DELETE FROM `categories` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categories deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Categories c) {
        try {
            String req = "UPDATE `categories` SET `nom` = '" + c.getNom() + "', `description` = '" + c.getDescription() +"', `photo` = '" + c.getPhoto() + "' WHERE `categories`.`id` = " + c.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categories updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Categories> getAll() {
    List<Categories> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM categories" ;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Categories c = new Categories(rs.getInt(1), rs.getString("nom"), rs.getString("description"), rs.getBytes("photo"));
            list.add(c);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}

   public Categories getOneById(int id) {
    Categories c = null;
    try {
        String req = "SELECT * FROM categories WHERE id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            c = new Categories(rs.getInt(1), rs.getString("nom"), rs.getString("description"), rs.getBytes("photo"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return c;
}
   
       public void delete(Categories t) {
        String sql = "delete from categories where id=?";
       try {
         PreparedStatement ste = cnx.prepareStatement(sql);
           ste.setInt(1, t.getId());
           ste.executeUpdate();
      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }

    }

    public void delete(GridPane gridPane) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    }

    
   

   
  
