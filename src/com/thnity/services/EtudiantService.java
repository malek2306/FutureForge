package com.thnity.services;

import com.thnity.entities.Etudiant;
import com.thnity.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantService {

    private static EtudiantService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public EtudiantService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static EtudiantService getInstance() {
        if (instance == null) {
            instance = new EtudiantService();
        }
        return instance;
    }

    public Etudiant login(String username) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM etudiant WHERE `username` LIKE ?");

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Etudiant(
                        resultSet.getInt("id"),
                        resultSet.getString("username")
                );
            }
        } catch (SQLException e) {
            System.out.println("Aucun email : " + e.getMessage());
        }

        return null;
    }
}
