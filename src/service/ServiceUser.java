/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Admin;
import entities.Etudiantm;
import entities.Friend;
import entities.User;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.scene.Cursor.cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import service.ServiceFriend;
import service.ServiceUser;
import util.Login;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import util.Data;

/**
 *
 * @author Name
 */
public class ServiceUser {

    Connection cnx = Data.getInstance().getCnx();

    public void ajouter(User u) throws IOException {
        String role = "";
        if (u instanceof Admin) {
            role = "Admin";
        } else if (u instanceof Etudiantm) {
            role = "Etudiant_m";
        } else {
            role = "Etudiant";
        }
        try {
            String req = "INSERT INTO `user` (`nom_u`, `prenom_u`, `email_u`, `tel_u`, `role_u`, `password_u`, `user_name`,`matricule_u`,`pfp_u`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getTel());
            ps.setString(5, role);
            ps.setString(6, u.getMdp());
            ps.setString(7, u.getUsername());
            ps.setString(8, u.getMat());
            if (u.getPfp() != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = u.getPfp().read(buffer)) > -1) {
                    baos.write(buffer, 0, len);
                }
                baos.flush();
                ps.setBytes(9, baos.toByteArray());
            } else {
                ps.setNull(9, Types.BLOB);
            }
            ps.executeUpdate();
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
    public List<User> Search(String text) {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM User WHERE nom_u LIKE '%" + text + "%'";
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
                p = new User(rs.getInt(1), rs.getString("nom_u"), rs.getString("prenom_u"), rs.getString("user_name"), rs.getString("email_u"), rs.getString("tel_u"), rs.getString("password_u"), rs.getString("role_u"), rs.getString("matricule_u"));
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

    public int countEtudiantmUsers() throws SQLException {
        String req = "SELECT COUNT(*) FROM user WHERE role_u = ?";
        int count = 0;
        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, "Etudiantm");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error checking: " + e.getMessage());
        }
        return count;
    }

    public void displayUserImage(int userId, ImageView imageView) throws SQLException, IOException {
        String query = "SELECT * FROM user WHERE id_user = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Blob blob = rs.getBlob("pfp_u");
            InputStream inputStream = blob.getBinaryStream();
            Image image = new Image(inputStream);
            imageView.setImage(image);
        }
    }
    /*private Connection connection;
    public Blob imageViewToBlob(ImageView imageView) throws IOException, SQLException {
        // Get Image object from ImageView
        BufferedImage image = SwingFXUtils.fromFXImage(imageView.getImage(), null);
        // Convert Image object to byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", outputStream);
        byte[] byteArray = outputStream.toByteArray();
        // Create Blob object from byte array
        Blob blob = connection.createBlob();
        blob.setBytes(1, byteArray);
        return blob;
    }*/
}
