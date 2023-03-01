/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import entities.Etudiant;
import entities.Admin;
import entities.Etudiantm;
import java.io.InputStream;

import util.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Name
 */
public class ServiceUser {

    Connection cnx = Data.getInstance().getCnx();

    public void ajouter(User u) {
        String role = "";
        if (u instanceof Admin) {
            role = "Admin";
        }
        else
        if (u instanceof Etudiantm) {
            role = "Etudiant_m";
        }
        else
            role = "Etudiant";
        try {
            String req = "INSERT INTO `user` (`nom_u`, `prenom_u`, `email_u`, `tel_u`, `role_u`, `password_u`, `user_name`,`pfp_u`,`matricule_u`) VALUES ('" + u.getNom() + "', '" + u.getPrenom() + "', '" + u.getEmail() + "', '" + u.getTel() + "', '" + role + "', '" + u.getMdp() + "', '" + u.getUsername() + "', '" + u.getPfp() + "', '" + u.getMat() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*public void ajouter2(User u) {
        try {
            String req = "INSERT INTO `user` (`nom`, `prenom`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, u.getPrenom());
            ps.setString(1, u.getNom());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `User` WHERE id_user = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerusername(String username) {
        String req = "DELETE FROM `User` WHERE user_name = ?";

        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error checking username: " + e.getMessage());
        }
    }

    public void modifier(User u) {
        try {
            String req = "UPDATE `User` SET `nom_u` = '" + u.getNom() + "', `prenom_u` = '" + u.getPrenom() + "', `email_u` = '" + u.getEmail() + "', `tel_u` = '" + u.getTel() + "' WHERE `user`.`id_user` = " + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from User";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User p = new User(rs.getInt(1), rs.getString("nom_u"), rs.getString("prenom_u"), rs.getString("user_name"), rs.getString("email_u"), rs.getString("tel_u"), rs.getString("password_u"), rs.getString("role_u"), rs.getBlob("pfp_u"), rs.getString("matricule_u"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public User getOneById(int id) {
        User p = null;
        try {
            String req = "Select * from User";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p = new User(rs.getInt(1), rs.getString("nom_u"), rs.getString("prenom_u"), rs.getString("user_name"), rs.getString("email_u"), rs.getString("tel_u"), rs.getString("password_u"), rs.getString("role_u"), rs.getBlob("pfp_u"), rs.getString("matricule_u"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

    public User getOneByUsername(String username) {
        User p = null;
        String req = "SELECT * FROM user WHERE user_name = ?";

        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            p = new User(resultSet.getInt(1), resultSet.getString("nom_u"), resultSet.getString("prenom_u"), resultSet.getString("user_name"), resultSet.getString("email_u"), resultSet.getString("tel_u"), resultSet.getString("password_u"), resultSet.getString("role_u"), resultSet.getBlob("pfp_u"), resultSet.getString("matricule_u"));
        } catch (SQLException e) {
            System.err.println("Error checking username: " + e.getMessage());
        }
        return p;
    }

    public boolean isUsernameTaken(String username) {
        boolean isTaken = false;
        String req = "SELECT COUNT(*) FROM user WHERE user_name = ?";

        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                isTaken = true;
            }
        } catch (SQLException e) {
            System.err.println("Error checking username: " + e.getMessage());
        }
        return isTaken;
    }

    public boolean isEmailTaken(String email) {
        boolean isTaken = false;
        String req = "SELECT COUNT(*) FROM user WHERE email_u = ?";

        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                isTaken = true;
            }
        } catch (SQLException e) {
            System.err.println("Error checking email: " + e.getMessage());
        }
        return isTaken;
    }

    public int countEtudiantUsers() throws SQLException {
        String req = "SELECT COUNT(*) FROM user WHERE role_u <> ?";
        int count = 0;
        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, "Admin");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error checking: " + e.getMessage());
        }
        return count;
    }
    

    public InputStream getBinaryStream(String image_blob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
