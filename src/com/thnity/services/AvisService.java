package com.thnity.services;

import com.thnity.entities.Avis;
import com.thnity.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvisService {

    private static AvisService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public AvisService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static AvisService getInstance() {
        if (instance == null) {
            instance = new AvisService();
        }
        return instance;
    }

    public List<Avis> getByOffre(int idOffre) {
        List<Avis> listAvis = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `avis` AS x RIGHT JOIN `offre` AS y ON x.id_offre = y.id_offre WHERE x.id_offre = y.id_offre AND y.id_offre = " + idOffre);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listAvis.add(new Avis(
                        resultSet.getInt("id_avis"),
                        resultSet.getInt("id_offre"),
                        resultSet.getInt("id_user"),
                        resultSet.getInt("rate"),
                        resultSet.getString("description_avis")
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) avis : " + exception.getMessage());
        }
        return listAvis;
    }

    public boolean add(Avis avis) {
        String request = "INSERT INTO `avis`(`id_offre`, `id_user`, `rate`, `description_avis`) VALUES(?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, avis.getIdOffre());
            preparedStatement.setInt(2, avis.getIdEtudiant());
            preparedStatement.setInt(3, avis.getRate());
            preparedStatement.setString(4, avis.getDescriptionAvis());

            preparedStatement.executeUpdate();
            System.out.println("Avis added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) avis : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Avis avis) {
        String request = "UPDATE `avis` SET `id_offre` = ?, `id_user` = ?, `rate` = ?, `description_avis` = ? WHERE `id_avis`=" + avis.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, avis.getIdOffre());
            preparedStatement.setInt(2, avis.getIdEtudiant());
            preparedStatement.setInt(3, avis.getRate());
            preparedStatement.setString(4, avis.getDescriptionAvis());

            preparedStatement.executeUpdate();
            System.out.println("Avis edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) avis : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `avis` WHERE `id_avis`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Avis deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) avis : " + exception.getMessage());
        }
        return false;
    }
}
