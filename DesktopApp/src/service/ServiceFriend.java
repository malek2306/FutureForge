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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import util.Data;

/**
 *
 * @author Name
 */
public class ServiceFriend {

    Connection cnx = Data.getInstance().getCnx();

    public void ajouter(Friend f) {
        try {
            String req = "INSERT INTO `friends` (`username1`, `username2`, `valide_f`) VALUES ('" + f.getUser1() + "', '" + f.getUser2() + "', '" + f.getValide() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Friend added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int notification(String u2) {
        int nb = 0;
        String req = "SELECT COUNT(*) FROM friends WHERE username2 = ? AND valide_f = 0";

        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, u2);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            nb = resultSet.getInt(1);
        } catch (SQLException e) {
            System.err.println("Error checking email: " + e.getMessage());
        }
        System.out.println(nb);
        return nb;
    }

    public List<Friend> getAll(String u2) {
        List<Friend> list = new ArrayList<>();
        String req = "SELECT * FROM friends WHERE username2 = ? AND valide_f = 0";
        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, u2);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Friend p = new Friend(rs.getInt(1), rs.getString("username1"), rs.getString("username2"), rs.getInt("valide_f"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public Friend getOneByUsername(String username) {
        Friend f = null;
        String req = "SELECT * FROM friends WHERE username1 = ?";

        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            rs.next();
            f = new Friend(rs.getInt(1), rs.getString("username1"), rs.getString("username2"), rs.getInt("valide_f"));
        } catch (SQLException e) {
            System.err.println("Error checking username: " + e.getMessage());
        }
        return f;
    }

    public void accept(Friend f) {
        try {
            String req = "UPDATE `friends` SET `valide_f` = '" + 1 + "' WHERE `friends`.`username1` = '" + f.getUser1() + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Friend added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Friend> IsFriend(String u) {
        List<Friend> list = new ArrayList<>();
        String req = "SELECT * FROM friends WHERE username1 = ? OR username2 = ? AND valide_f = 1";
        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, u);
            statement.setString(2, u);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Friend p = new Friend(rs.getInt(1), rs.getString("username1"), rs.getString("username2"), rs.getInt("valide_f"));
                list.add(p);
                System.out.println(p);
                System.out.println("frenbedes");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void supprimer(String username) {
        String req = "DELETE FROM friends WHERE username1 = ? OR username2=  ? ";
        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, username);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public int wehomies(String u1,String u2) {
        int ok = 0;
        return ok;
    }
}
