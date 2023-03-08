/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Reservation_covoiturage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utiles.DataSource;

/**
 *
 * @author LENOVO
 */
public class ServiceReservationCovoiturage {

    Connection cnx = DataSource.getInstance().getCnx();

     public void ajouter(Reservation_covoiturage r) {
        try {
            String req = "INSERT INTO `reservation_covoiturage` (`nom`, `prenom`,`pnt_rencontre`,`distination`,`nbr_place`,`date`) VALUES ('" + r.getNom() + "', '" + r.getPrenom() + "' , '" + r.getPnt_rencontre() + "' , '" + r.getDistination() + "' , '" + r.getNbr_place() + "','" + r.getDate() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation crée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
        
        
        // public void ajouter(Reservation_covoiturage r) {
       
       /* try {
           String req = "INSERT INTO `reservation_covoiturage` (`nom`, `prenom`, `pnt_rencontre`, `distination`, `nbr_place`, `date`) " 
                    + "VALUES ('" + r.getNom()+ "', '" + r.getPrenom()+ "' , '" + r.getPnt_rencontre()+ "','" +r.getDistination()+"','" +r.getNbr_place()+ "','"+r.getDate()+ "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation crée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
    }*/
    
    public void supprimer(int id_reservation) {
        try {
            String req = "DELETE FROM `reservation_covoiturage` WHERE id_reservation = " + id_reservation;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier1(Reservation_covoiturage p) {
        try {
            String req = "UPDATE `reservation_covoiturage` SET `nom` = '" + p.getNom() + "', `prenom` = '" + p.getPrenom() + "', `pnt_rencontre` = '" + p.getPnt_rencontre()+"', `date` = '" + p.getDate()+ "' WHERE id_reservation  = " + p.getId_reservation();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation_covoiturage updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public List<Reservation_covoiturage> getAll() throws SQLException{
        List<Reservation_covoiturage> list = new ArrayList<>();
        
        String req = "Select * from reservation_covoiturage";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Reservation_covoiturage rc = new Reservation_covoiturage(rs.getInt(1), 
                    rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getInt(6),rs.getString(7));
            list.add(rc);
        }
        return list;
    }
}

   