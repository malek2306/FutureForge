/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.services;
import thniti.entities.Reclamation;
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


public class ServiceReclamation implements IService<Reclamation> {
    //Connection cnx;
 Connection cnx = DataSource.getInstance().getCnx();

@Override
public void ajouter(Reclamation p) {
    
    if (p.getTypeR().isEmpty() || p.getDescriptionR().isEmpty()) {
    throw new IllegalArgumentException("Veuillez renseigner tous les champs !");
}
    
    try {
        
            String req = "INSERT INTO `Reclamation` (`TypeR`, `DescriptionR`, `Objet`, `DateR`, `etat`,`id_u`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getTypeR());
            ps.setString(2, p.getDescriptionR());
            ps.setString(3, p.getObjet());
            ps.setTimestamp(4, new java.sql.Timestamp(p.getDateR().getTime()));
            ps.setString(5, p.getEtat());
            ps.setInt(6, p.getId_u());
            ps.executeUpdate();
            System.out.println("rec created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     @Override  
     public void supprimer(Reclamation t) {
        String sql = "delete from reclamation where id_R=?";
       try {
         PreparedStatement ste = cnx.prepareStatement(sql);
           ste.setInt(1, t.getId_R());
           ste.executeUpdate();
      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }}
     

    @Override
    public List<Reclamation> getAll() {
 ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
        try {
            String req = "select * from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setId_R(rs.getInt(1));
                p.setTypeR(rs.getString("TypeR"));
                p.setDescriptionR(rs.getString("DescriptionR"));
                p.setDateR(rs.getDate("DateR"));
                p.setObjet(rs.getString("Objet"));
                p.setEtat(rs.getString("etat"));
                
               reclamations.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;    }

    @Override
    public Reclamation getOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<Reclamation> affichercatprod() {
       List<Reclamation> list = new ArrayList<>();
        try {
            String req = "Select * from reclamation";
            Statement st = cnx.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Reclamation p = new Reclamation();
             p.setTypeR(RS.getString("TypeR"));
                p.setDescriptionR(RS.getString("DescriptionR"));
                p.setDateR(RS.getDate("DateR"));
                p.setObjet(RS.getString("Objet"));
                p.setEtat(RS.getString("etat"));
                p.setId_u(RS.getInt(""));
             
list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
public void Traiter( Reclamation c) {
       String sql = "update reclamation set etat=? where id_R=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
      ste.setString(1, "traité");
            ste.setInt(2,c.getId_R());
        
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  
    }
  

   
    @Override
public void modifier(Reclamation t) {
    try {
      
      String qry=   " UPDATE reclamation SET " + "    TypeR= ?, DescriptionR = ? , Objet  = ? ,Etat = ? WHERE id_R= ? " ;
              
        PreparedStatement ps = cnx.prepareStatement(qry);
        ps.setString(1, t.getTypeR());
        ps.setString(2, t.getDescriptionR());
        ps.setString(3, t.getObjet());
        ps.setString(4, t.getEtat());
        ps.setInt(5, t.getId_R());
       
        ps.executeUpdate();
        System.out.println("Reclamation modifiée");
    } catch (SQLException ex) {
        System.out.println("Error updating reclamation: " + ex.getMessage());
    }
}

public List<Reclamation> TRI() {
 ObservableList<Reclamation> Reclamations = FXCollections.observableArrayList();
        try {
            String req = "select * from reclamation ORDER BY DateR DESC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setTypeR(rs.getString("TypeR"));
                p.setDescriptionR(rs.getString("DescriptionR"));
                p.setDateR(rs.getDate("DateR"));
                p.setEtat(rs.getString("etat"));
               
                
                
                
                
               Reclamations.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Reclamations;    }

   

}









