/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
import entities.Reservation_bus;
import services.Iservice;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utiles.DataSource;


public class ServiceReservationBus {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Reservation_bus r) {
       
        try {
           String req = "INSERT INTO `reservation_bus` (`nom`, `prenom`, `num_place`, `date`, `email`, `destination`) " 
                    + "VALUES ('" + r.getNom()+ "', '" + r.getPrenom()+ "' , '" + r.getNum_place()+ "','" +r.getDate()+"','" +r.getEmail() + "','"+r.getDestination() + "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation crée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
        }
    

 
    public void supprimer(int id_reservation_bus) {
        try {
            String req = "DELETE FROM `reservation_bus` WHERE id_reservation_bus = "
                    + id_reservation_bus;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   /*  public void modifier(Reservation_bus r) {
            String req = "UPDATE `reservation_bus` SET `nom` = ?, `prenom` = ? , `num_place` =? WHERE `reservation_bus`.`nom` = ?" ;
        try {
         
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, r.getNom());
             st.setString(2, r.getPrenom());
              st.setInt(3, r.getNum_place());
              st.setString(4, r.getNom());
              
            st.executeUpdate(req);
            
            System.out.println("reservation updated !");
        } catch (SQLException ex) {
           
            System.out.println(ex.getMessage());
        }
    }**/
     
       public void modifier1(Reservation_bus r) {
        try {
          String req = "UPDATE `reservation_bus` SET `nom`='" + r.getNom() + "', `prenom`='" + r.getPrenom() + "', `num_place`='" + r.getNum_place() + "', `date`='" + r.getDate() + "', `email`='" + r.getEmail() + "', `destination`='" + r.getDestination() + "' WHERE `id_reservation_bus`=" + r.getId_reservation_bus();

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation_bus updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
       public List<Reservation_bus> getAll() throws SQLException {
        List<Reservation_bus> list = new ArrayList<>();
        
        String req = "Select * from reservation_bus";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Reservation_bus p = new Reservation_bus(rs.getInt("id_reservation_bus"), rs.getString(2), rs.getString(3),rs.getInt("num_place"));
            list.add(p);
        }
        
        return list;
    }
    
       
       public Reservation_bus getOneById(int id) {
        Reservation_bus p = null;
        try {
            String req = "Select * from reservation_bus";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p = new Reservation_bus(rs.getInt(1), rs.getString(2), 
                        rs.getString(3),rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

}