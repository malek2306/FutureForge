/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementFX.Services;

import abonnementFX.Entities.Abonnement;
import abonnementFX.Entities.OffreAbonnement;
import abonnementFX.Entities.TypeAbonnement;
import abonnementFX.Util.MaConnexion;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;

/**
 *
 * @author ASUS
 */
public class AbonnementService {
    Connection cnx = MaConnexion.getInstance().getCnx();

    public void ajouter_abonnement(Abonnement abonnement) {
        try {
            String req1="INSERT INTO `abonnement`( `nom`, `prenom`, `image`,`email`,`identifiant`, `cin` ,`type`,`dateD`,`dateF`,`prix`,`id_offre`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req1);
            ps.setString(1, abonnement.getNom());
            ps.setString(2, abonnement.getPrenom());
            if (abonnement.getImg()!=null)
                ps.setString(3, abonnement.getImg());
            else 
                ps.setString(3, null);
            ps.setString(4, abonnement.getEmail());
            ps.setString(5, abonnement.getIdentifiant());
            ps.setString(6, abonnement.getCin());
            ps.setString(7, abonnement.getType().toString());
            ps.setDate(8, abonnement.getDateDebut());
            ps.setDate(9, abonnement.getDateFin());
            ps.setDouble(10, abonnement.getPrix());
            ps.setInt(11, abonnement.getId_offre());
            
            ps.executeUpdate();
            
            System.out.println("abonnement Added !");
        } catch (SQLException ex) {
            Logger.getLogger(OffreAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }   




    public void annuler_abonnement(int id) {
        try {
            String req = "DELETE FROM `abonnement` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("abonnement annuler !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        }

    public List<Abonnement> getall_abonnements() {
        List<Abonnement> abonnements = new ArrayList<Abonnement>();
        try {
            String req = "SELECT * FROM abonnement ";
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
                File file = null;
                if (rs.getString("image")!=null)
                    file = new File(rs.getString("image"));
                String filePath = "";
                if (file==null)
                    filePath = "";
                else 
                    filePath = rs.getString("image");
                abonnements.add(new Abonnement(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"),filePath,rs.getString("email"), rs.getString("identifiant"),rs.getString("cin"), type,rs.getDate("dated"), rs.getDate("datef"),rs.getDouble("prix"),rs.getInt("id_offre")));
                
            }
            return abonnements;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }    
    }

    public Abonnement get_abonnement(String email) {
        Abonnement abonnement = null;
        try {
            String req = "SELECT * FROM abonnement where email = '"+email+"'";
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
                abonnement = new Abonnement(rs.getString("nom"), rs.getString("prenom"),rs.getString("image"),rs.getString("email"), rs.getString("identifiant"),rs.getString("cin"), type,rs.getDate("dated"), rs.getDate("datef"),rs.getDouble("prix"),rs.getInt("id_offre"));
                
            }
            return abonnement;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }      
    }

    public Abonnement get_abonnementByIdentifiant(String identifiant) {
        Abonnement abonnement = null;
        try {
            String req = "SELECT * FROM abonnement where identifiant = '"+identifiant+"'";
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
                abonnement = new Abonnement(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getString("image"),rs.getString("email"), rs.getString("identifiant"),rs.getString("cin"), type,rs.getDate("dated"), rs.getDate("datef"),rs.getDouble("prix"),rs.getInt("id_offre"));
                
            }
            return abonnement;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }    
    }

    public void modifier_abonnement(String identifiant, Abonnement abonnement) {
        try {
            String req = "UPDATE `abonnement` SET `nom` = '" + abonnement.getNom() +  "', `prenom` = '" + abonnement.getPrenom()+ "', `email` = '" + abonnement.getEmail()+  "', `cin` = '" + abonnement.getCin() + "', `identifiant` = '" + abonnement.getIdentifiant()+ "', `image` = '" + abonnement.getImg()+ "' WHERE `identifiant` = '" + identifiant+"'";
            
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("abonnement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    public Abonnement get_abonnementByIdentifiantDate(String identifiant) {
        Abonnement abonnement = null;
        try {
            String req = "SELECT * FROM abonnement where identifiant = '"+identifiant+"' and dateD<current_date() and dateF>current_date()";
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
                abonnement = new Abonnement(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getString("image"),rs.getString("email"), rs.getString("identifiant"),rs.getString("cin"), type,rs.getDate("dated"), rs.getDate("datef"),rs.getDouble("prix"),rs.getInt("id_offre"));
                
            }
            return abonnement;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }   
    }

}
