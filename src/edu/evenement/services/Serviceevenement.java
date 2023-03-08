/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.services;

import edu.evenement.entities.Categories;
import edu.evenement.entities.Evenement;
import edu.evenement.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;


/**
 *
 * @author farah
 */
public class Serviceevenement {
       Connection cnx = DataSource.getInstance().getCnx(); 
      
      
       
  public void ajouter( Evenement e){
      try{
        String requete = "INSERT INTO evenements (nom, type, description, date, Categorie_id ) " + " VALUES (?,?,?,?,?)";
       PreparedStatement st =cnx.prepareStatement(requete);
       st.setString(1, e.getNom());
       st.setString(2, e.getType());
       st.setString(3, e.getDescription());
       st.setDate(4, java.sql.Date.valueOf(e.getDate()));
      st.setInt(5, e.getCategories().getId());
       st.executeUpdate();
            System.out.println("Evenement created !");
      }
      catch (SQLException ex) {
              System.err.println(ex.getMessage()  );
        }
    }
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `evenements` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("evenement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
       public void delete(Evenement t) {
        String sql = "delete from evenements where id=?";
       try {
         PreparedStatement ste = cnx.prepareStatement(sql);
           ste.setInt(1, t.getId());
           ste.executeUpdate();
      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }

    }
     public void modifier (Evenement e )  {
         try{
        String requete =  "UPDATE evenements SET nom = ?, type = ?, description = ?, date = ?, Categorie_id = ? WHERE id = ?" ;
       PreparedStatement st =cnx.prepareStatement(requete);
       st.setString(1, e.getNom());
       st.setString(2, e.getType());
       st.setString(3, e.getDescription());
      st.setDate(4, java.sql.Date.valueOf(e.getDate()));
      st.setInt(5, e.getCategories().getId());
      st.setInt(6,e.getId());
       st.executeUpdate();
            System.out.println("evenement updated !");
            }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     
 public List<Evenement> getAll() {
    List<Evenement> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM evenements ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Evenement e = new Evenement();
            e.setId(rs.getInt("id"));
            e.setNom(rs.getString("nom"));
            e.setType(rs.getString("type"));
            e.setDescription(rs.getString("description"));
             e.setDate(rs.getDate("date").toLocalDate());
            Categories c = new Categories();
            c.setId(rs.getInt("Categorie_id"));   
            e.setCategories(c);
            list.add(e);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}
public Evenement getOneById(int id) {
    Evenement e = null;
    try {
        String req = "SELECT * FROM evenements WHERE id=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            e = new Evenement();
            e.setId(rs.getInt("id"));
            e.setNom(rs.getString("nom"));
            e.setType(rs.getString("type"));
            e.setDescription(rs.getString("description"));
           e.setDate(rs.getDate("date").toLocalDate());
            Categories c = new Categories();
            c.setId(rs.getInt("Categorie_id"));   
            e.setCategories(c);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return e;
}
public List<Object[]> getEvenementsCountByDate() throws SQLException {
    List<Object[]> evenementsCountByDate = new ArrayList<>();
    String req = "SELECT date, COUNT(*) as evenements_count FROM evenements GROUP BY date";
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(req);
    while (rs.next()) {
        Object[] row = new Object[2];
        row[0] = rs.getString("date");
        row[1] = rs.getInt("evenements_count");
        evenementsCountByDate.add(row);
    }
    return evenementsCountByDate;
}
 public List<Object[]> countEveByType() throws SQLException {
    List<Object[]> countEveByType = new ArrayList<>();
    String req = "select type, count(*) as eve_count from evenements group by type";
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(req);
    while (rs.next()) {
        Object[] row = new Object[2];
        row[0] = rs.getString("type");
        row[1] = rs.getInt("eve_count");
        countEveByType.add(row);
    }
    return countEveByType;
}



   
}
    


     
  
   
   
     