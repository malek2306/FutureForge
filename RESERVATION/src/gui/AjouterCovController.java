/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation_covoiturage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceReservationCovoiturage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterCovController implements Initializable {

    @FXML
    private AnchorPane VBOX;
    @FXML
    private Button ajooouut;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_pnt;
    @FXML
    private Button reeeetouuuur;
    @FXML
    private TextArea tf_distination;
    @FXML
    private TextArea tf_date;
    @FXML
    private TextArea tf_nbr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static boolean isValidDate(String dateString) {
        String[] dateParts = dateString.split("/");
        if (dateParts.length != 3) {
            return false;
        }

        int day;
        int month;
        int year;
        try {
            day = Integer.parseInt(dateParts[0]);
            month = Integer.parseInt(dateParts[1]);
            year = Integer.parseInt(dateParts[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        if (month < 1 || month > 12) {
            return false;
        }
        if (year < 2023) {
            return false;
        }

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int maxDays = daysInMonth[month - 1];
        if (month == 2 && isLeapYear(year)) {
            maxDays = 29;
        }

        if (day < 1 || day > maxDays) {
            return false;
        }

        return true;
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            return true;
        }
        return false;
    }

    private boolean checkUserExistence(String nom, String prenom) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thnity(1)", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservation_covoiturage WHERE nom='" + nom + "' AND prenom='" + prenom + "'");
            if (rs.next()) {
                return true;
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la vérification de l'existence de l'utilisateur : " + ex.getMessage());
        }
        return false;
    }

    @FXML
    private void btAjouter(ActionEvent event) {
        String nom = tf_nom.getText();
        String prenom = tf_prenom.getText();
        String pnt = tf_pnt.getText();
        String distination = tf_distination.getText();
        String nbr = tf_nbr.getText();
        String date = tf_date.getText();

        if (nom.isEmpty() || prenom.isEmpty() || pnt.isEmpty() || distination.isEmpty() || nbr.isEmpty() || date.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuiller Remplir Les champs!!");
            alert.showAndWait();
        } else if (checkUserExistence(nom, prenom) == true) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nom et Prenom deja existent");
            alert.showAndWait();
        } else if (isValidDate(date) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Date non valide");
            alert.showAndWait();
        } else {
            ServiceReservationCovoiturage rs = new ServiceReservationCovoiturage();
            Reservation_covoiturage c = new Reservation_covoiturage();
            rs.ajouter(c);
        }
    }

    @FXML
    private void btclean(ActionEvent event) {
        tf_nom.setText(null);
        tf_prenom.setText(null);
        tf_distination.setText(null);
        tf_date.setText(null);
        tf_nbr.setText(null);
        tf_pnt.setText(null);
    }

    @FXML
    private void btnretour(MouseEvent event) {

        try {
            VBOX.getScene().getWindow().hide();
            Stage Affiche;
            Affiche = new Stage();
            Parent fxml;

            fxml = FXMLLoader.load(getClass().getResource("tableViewCovoiturage.fxml"));

            Scene scene = new Scene(fxml);
            Affiche.setScene(scene);
            Affiche.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCovController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
