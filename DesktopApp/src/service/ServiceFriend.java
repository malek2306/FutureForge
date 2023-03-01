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
        int nb=0;
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
    
    public Friend getList(String username) {
        Friend p = null;
        String req = "SELECT * FROM user WHERE username1 = ? AND valide_f = 0";

        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            p = new Friend(resultSet.getInt(1), resultSet.getString("username1"), resultSet.getString("username2"), resultSet.getInt("valide_f"));
        } catch (SQLException e) {
            System.err.println("Error checking username: " + e.getMessage());
        }
        return p;
    }

}
