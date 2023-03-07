package com.thnity.services;

import com.thnity.entities.Offre;
import com.thnity.utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OffreService {

    private static OffreService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public OffreService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static OffreService getInstance() {
        if (instance == null) {
            instance = new OffreService();
        }
        return instance;
    }

    public List<Offre> getAll() {
        List<Offre> listOffre = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `offre`");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listOffre.add(new Offre(
                        resultSet.getInt("id_offre"),
                        resultSet.getInt("id_user"),
                        resultSet.getString("image_vehicule"),
                        resultSet.getString("prenom_chauff"),
                        resultSet.getString("num_chauff"),
                        LocalDate.parse(String.valueOf(resultSet.getDate("date_offre"))),
                        LocalTime.parse(String.valueOf(resultSet.getTime("heure"))),
                        resultSet.getInt("prix_offre"),
                        resultSet.getString("depart"),
                        resultSet.getString("destination"),
                        resultSet.getInt("places_dispo")

                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) offre : " + exception.getMessage());
        }
        return listOffre;
    }

    public boolean add(Offre offre) {
        String request = "INSERT INTO `offre`(`image_vehicule`, `prenom_chauff`, `num_chauff`, `date_offre`, `heure`, `prix_offre`, `depart`, `destination`, `places_dispo`, `id_user`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, offre.getImageVehicule());
            preparedStatement.setString(2, offre.getPrenomChauff());
            preparedStatement.setString(3, offre.getNumChauff());
            preparedStatement.setDate(4, Date.valueOf(offre.getDateOffre()));
            preparedStatement.setTime(5, Time.valueOf(offre.getHeure()));
            preparedStatement.setInt(6, offre.getPrixOffre());
            preparedStatement.setString(7, offre.getDepart());
            preparedStatement.setString(8, offre.getDestination());
            preparedStatement.setInt(9, offre.getPlacesDispo());
            preparedStatement.setInt(10, offre.getIdEtudiant());

            preparedStatement.executeUpdate();
            System.out.println("Offre added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) offre : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Offre offre) {

        String request = "UPDATE `offre` SET `image_vehicule` = ?, `prenom_chauff` = ?, `num_chauff` = ?, `date_offre` = ?, `heure` = ?, `prix_offre` = ?, `depart` = ?, `destination` = ?, `places_dispo` = ? WHERE `id_offre`=" + offre.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, offre.getImageVehicule());
            preparedStatement.setString(2, offre.getPrenomChauff());
            preparedStatement.setString(3, offre.getNumChauff());
            preparedStatement.setDate(4, Date.valueOf(offre.getDateOffre()));
            preparedStatement.setTime(5, Time.valueOf(offre.getHeure()));
            preparedStatement.setInt(6, offre.getPrixOffre());
            preparedStatement.setString(7, offre.getDepart());
            preparedStatement.setString(8, offre.getDestination());
            preparedStatement.setInt(9, offre.getPlacesDispo());

            preparedStatement.executeUpdate();
            System.out.println("Offre edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) offre : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `offre` WHERE `id_offre`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Offre deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) offre : " + exception.getMessage());
        }
        return false;
    }
}
