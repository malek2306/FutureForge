/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation_bus;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceReservationBus;

/**
 * FXML Controller class
 *
 */
public class ModifierBUSController implements Initializable {

    @FXML
    private AnchorPane VBOX;
    @FXML
    private Button modif;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_num;
    @FXML
    private Button reeeetouuuur;
    @FXML
    private TextArea tf_date;
    @FXML
    private TextArea tf_destination;
    @FXML
    private TextArea tf_email;

    private ServiceReservationBus serviceReservationBus;
    private Reservation_bus reservationBus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initialize service
        serviceReservationBus = new ServiceReservationBus();
    }

    @FXML
    
    private void btmodif(ActionEvent event) {
        // update reservation
       
        reservationBus.setNom(tf_nom.getText());
        reservationBus.setPrenom(tf_prenom.getText());
        reservationBus.setNum_place(Integer.parseInt(tf_num.getText()));
        reservationBus.setDate(tf_date.getText());
        reservationBus.setEmail(tf_email.getText());
        reservationBus.setDestination(tf_destination.getText());

        
            serviceReservationBus.modifier1(reservationBus);
            System.out.println("reservation_bus updated !");
        }
    

    @FXML
    private void btclean(ActionEvent event) {
        tf_nom.setText(null);
        tf_prenom.setText(null);
        tf_num.setText(null);
        tf_date.setText(null);
        tf_email.setText(null);
        tf_destination.setText(null);
    }

    @FXML
    private void btnretour(MouseEvent event) {
        VBOX.getScene().getWindow().hide();
        Stage Affiche = new Stage();
        Parent fxml;
        try {
            fxml = FXMLLoader.load(getClass().getResource("tableView.fxml"));

            Scene scene = new Scene(fxml);
            Affiche.setScene(scene);
            Affiche.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setUpdate(boolean b) {
        // not used
    }

    void setTextField(String nom, String prenom, int num_place, String date, String email, String destination) {
        reservationBus = new Reservation_bus();
        if (tf_num.getText().isEmpty()) {
    // handle the empty case
} else {
             
    int busNumber = Integer.parseInt(tf_num.getText());
     tf_num.setText(Integer.parseInt(tf_num.getText()) + "");
    // rest of the code that uses busNumber
}
        
        
        
        
        tf_nom.setText(nom);
        

    tf_prenom.setText(prenom);
 
        tf_date.setText(date);
        tf_email.setText(email);
        tf_destination.setText(destination);
        
    }}
       
