/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import offre.entities.Offre;
import offre.utils.DataSource;

/**
 *
 * @author Firas
 */
public class ServiceOffre implements IService<Offre> {

    Connection cnx = DataSource.getInstance().getCnx();

    

    @Override
    public void ajouter_offre(Offre O) {
        try {
            String req = "INSERT INTO `offre` (`image_vehicule`, `nom_chauff`,`prenom_chauff`,`num_chauff`,`date_offre`,`heure`,`places_dispo`,`prix_offre`,`trajet`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, O.getImage_vehicule());
            ps.setString(2, O.getNom_chauff());
            ps.setString(3, O.getPrenom_chauff());
            ps.setString(4, O.getNum_chauff());
            ps.setString(5, O.getDate_offre());
            ps.setString(6, O.getHeure());
            ps.setInt(7, O.getPlaces_dispo());
            ps.setDouble(8, O.getPrix_offre());
            ps.setString(9, O.getTrajet());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer_offre(int id) {
        try {
            String req = "DELETE FROM `offre` WHERE id_offre = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("offre deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void modifier_offre(Offre o) {
        try {
            String req = "UPDATE `offre` SET `image_vehicule` = '" + o.getImage_vehicule() + "', `nom_chauff` = '" + o.getNom_chauff() + "',`prenom_chauff` = '" + o.getPrenom_chauff() + "',`num_chauff` = '" + o.getNum_chauff() + "',`date_offre` = '" + o.getDate_offre() + "',`heure` = '" + o.getHeure() + "',`prix_offre` = '" + o.getPrix_offre() + "',`trajet` = '" + o.getTrajet()+ "',`places_dispo` = '" + o.getPlaces_dispo() + "' WHERE `offre`.`id_offre` = " + o.getId_offre();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Offre p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
   public List<Offre> getAll() {
        List<Offre> result = new ArrayList<>();
         try {
            String req="SELECT * FROM offre";
           Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                 int id = rs.getInt(1);
                 String image_vehicule = rs.getString(2);
                 String nom_chauff = rs.getString(3);
                 String prenom_chauff = rs.getString(4);
                 String num_chauff=rs.getString(5);
                 Double date_offre=rs.getDouble(6);
                 String heure = rs.getString(7);
                 String prix_offre=rs.getString(8);
                 String trajet=rs.getString(9);
                 String places_dispo=rs.getString(10);
                 Offre o= new Offre(id, image_vehicule, nom_chauff,prenom_chauff,num_chauff,date_offre,heure,prix_offre,trajet,places_dispo);
                 result.add(o);
                     }
            System.out.println("Abonnement added !");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
         return result;
    }


   
    }

    



