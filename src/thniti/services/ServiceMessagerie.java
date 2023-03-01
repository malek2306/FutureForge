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
import thniti.entities.Reclamation;

/**
 *
 * @author 21692
 */
public class ServiceMessagerie implements IService<Messagerie> {
    Connection cnx = DataSource.getInstance().getCnx();
    
@Override
public void ajouter(Messagerie p) {
    
    if (p.getContenuM().isEmpty() || p.getEtat().isEmpty()) {
    throw new IllegalArgumentException("Veuillez renseigner tous les champs !");
}
    
    try {
        
            String req = "INSERT INTO `Reclamation` (`contenuM`, `etat`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getContenuM());
            ps.setString(2, p.getEtat());
           
            ps.executeUpdate();
            System.out.println("msg created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        @Override
    public List<Messagerie> getAll() {
 ObservableList<Messagerie> messages = FXCollections.observableArrayList();
        try {
            String req = "select * from messagerie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Messagerie p = new Messagerie();
                p.setContenuM(rs.getString("contenuM"));
                p.setEtat(rs.getString("etat"));
                
                
               messages.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return messages;    }

    

    @Override
    public void supprimer(Messagerie t) {
        String sql = "delete from messagerie where id_M=?";
       try {
         PreparedStatement ste = cnx.prepareStatement(sql);
           ste.setInt(1, t.getId_M());
           ste.executeUpdate();
      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }
    }
    
    

    @Override
    public void modifier(Messagerie p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Messagerie getOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public List<Messagerie> TRI() {
 ObservableList<Messagerie> messages = FXCollections.observableArrayList();
        try {
            String req = "select * from messagerie ORDER BY etat DESC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Messagerie p = new Messagerie();
                p.setContenuM(rs.getString("contenuM"));
                p.setEtat(rs.getString("etat"));
                
                
               messages.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return messages;    }

 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
