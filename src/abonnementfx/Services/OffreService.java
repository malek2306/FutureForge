/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementFX.Services;

import abonnementFX.Entities.OffreAbonnement;
import abonnementFX.Entities.TypeAbonnement;
import abonnementFX.Util.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OffreService {
    Connection cnx = MaConnexion.getInstance().getCnx();

    public void ajouter_offre(OffreAbonnement offre) {
        
        try {
            String req1="INSERT INTO `offre`( `nom`, `description`, `reduction`,`type`, `dateD`,`dateF`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req1);
            ps.setString(1, offre.getNom());
            ps.setString(2, offre.getDescription());
            ps.setDouble(3, offre.getReduction());
            ps.setString(4, offre.getType().toString());
            ps.setDate(5, offre.getDate_debut());
            ps.setDate(6, offre.getDate_fin());
            ps.executeUpdate();
            
            System.out.println("offre Added !");
        } catch (SQLException ex) {
            Logger.getLogger(OffreAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    public void annuler_offre(int id) {
        try {
            String req = "DELETE FROM `offre` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("offre annuler !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }


    public void modifier_offre(int id, OffreAbonnement offre) {
        try {
            String req = "UPDATE `offre` SET `nom` = '" + offre.getNom() +  "', `description` = '" + offre.getDescription()+ "', `reduction` = " + offre.getReduction() +  ", `type` = '" + offre.getType().toString() + "', `dateD` = '" + offre.getDate_debut() + "', `dateF` = '" + offre.getDate_fin()+ "' WHERE `id` = " + id;
            
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("offre updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    public List<OffreAbonnement> offre_dispo() {
        List<OffreAbonnement> offres = new ArrayList<OffreAbonnement>();
        try {
            String req = "SELECT * FROM offre WHERE datef >= CURRENT_DATE and dated <= CURRENT_DATE";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                TypeAbonnement type = null;
                if (rs.getString("type").equals(TypeAbonnement.MENSUEL.toString())){
                    type=TypeAbonnement.MENSUEL;
                }
                else if (rs.getString("type").equals(TypeAbonnement.SEMESTRILLE.toString())){
                    type=TypeAbonnement.SEMESTRILLE;
                }
                else if (rs.getString("type").equals(TypeAbonnement.ANNUELLE.toString())){
                    type=TypeAbonnement.ANNUELLE;
                }
                offres.add(new OffreAbonnement(rs.getInt("id"),rs.getString("nom"), rs.getString("description"), rs.getDouble("reduction"),type, rs.getDate("dated"), rs.getDate("datef")));
                
            }
            return offres;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public List<OffreAbonnement> allOffres() {
        List<OffreAbonnement> offres = new ArrayList<OffreAbonnement>();
        try {
            String req = "SELECT * FROM offre";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                TypeAbonnement type = null;
                if (rs.getString("type").equals(TypeAbonnement.MENSUEL.toString())){
                    type=TypeAbonnement.MENSUEL;
                }
                else if (rs.getString("type").equals(TypeAbonnement.SEMESTRILLE.toString())){
                    type=TypeAbonnement.SEMESTRILLE;
                }
                else if (rs.getString("type").equals(TypeAbonnement.ANNUELLE.toString())){
                    type=TypeAbonnement.ANNUELLE;
                }
                offres.add(new OffreAbonnement(rs.getInt("id"),rs.getString("nom"), rs.getString("description"), rs.getDouble("reduction"),type, rs.getDate("dated"), rs.getDate("datef")));
                
            }
            return offres;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public OffreAbonnement findOffre(int id_Clicked) {
        try {
            String req = "SELECT * FROM offre WHERE id = "+id_Clicked;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                TypeAbonnement type = null;
                if (rs.getString("type").equals(TypeAbonnement.MENSUEL.toString())){
                    type=TypeAbonnement.MENSUEL;
                }
                else if (rs.getString("type").equals(TypeAbonnement.SEMESTRILLE.toString())){
                    type=TypeAbonnement.SEMESTRILLE;
                }
                else if (rs.getString("type").equals(TypeAbonnement.ANNUELLE.toString())){
                    type=TypeAbonnement.ANNUELLE;
                }
                return new OffreAbonnement(rs.getInt("id"),rs.getString("nom"), rs.getString("description"), rs.getDouble("reduction"),type, rs.getDate("dated"), rs.getDate("datef")); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return null;
    }
   
}
