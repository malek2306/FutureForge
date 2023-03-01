/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thniti.entities.Reclamation;
import thniti.utils.DataSource;

/**
 *
 * @author 21692
 */
public class recService {
 Statement ste;
 Connection conn = DataSource.getInstance().getCnx();
  
    //Mèthode pour afficher produit
    public List<Reclamation> afficherprod() {
       List<Reclamation> list = new ArrayList<>();
        try {
            String req = "Select * from reclamation";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Reclamation p = new Reclamation();
             p.setId_R(RS.getInt("Id_R"));
             p.setTypeR(RS.getString("TypeR"));
             p.setDescriptionR	(RS.getString("DescriptionR"));
             p.setObjet	(RS.getString("Objet"));
             p.setDateR(RS.getTimestamp("DateR"));
             p.setEtat(RS.getString("Etat"));
             p.setId_u(RS.getInt("id_cat"));
             
             


list.add(p);
            }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }    
}
