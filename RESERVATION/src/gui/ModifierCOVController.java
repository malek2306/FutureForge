/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation_covoiturage;
import java.io.IOException;
import java.net.URL;
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
import services.ServiceReservationCovoiturage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierCOVController implements Initializable {

    @FXML
    private AnchorPane VBOX;
    @FXML
    private Button modif;
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
    private ServiceReservationCovoiturage serviceReservationCovoiturage;
    private Reservation_covoiturage reservationcov;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviceReservationCovoiturage = new ServiceReservationCovoiturage();
    }    

    @FXML
    private void bfmodif(ActionEvent event) {
        reservationcov.setNom(tf_nom.getText());
        reservationcov.setPrenom(tf_prenom.getText());
        reservationcov.setPnt_rencontre(tf_pnt.getText());
        reservationcov.setDistination(tf_distination.getText());
       reservationcov.setNbr_place(Integer.parseInt(tf_nbr.getText()));
        reservationcov.setDate(tf_date.getText());

        
            serviceReservationCovoiturage.modifier1(reservationcov);
            System.out.println("reservation covoiturage updated !");
        }
    
        
        
    

    @FXML
    private void btclean(ActionEvent event) {
         tf_nom.setText(null);
        tf_prenom.setText(null);
        tf_pnt.setText(null);
        tf_distination.setText(null);
        tf_nbr.setText(null);
        tf_date.setText(null);
        
        
    }

    @FXML
    private void btnretour(MouseEvent event) {
        VBOX.getScene().getWindow().hide();
        Stage Affiche = new Stage();
        Parent fxml;
        try {
            fxml = FXMLLoader.load(getClass().getResource("tableViewCovoiturage.fxml"));

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

    void setTextField(String nom, String prenom, String pnt_rencontre, String distination, int nbr_place , String date) {
        reservationcov = new Reservation_covoiturage();
        if (tf_nbr.getText().isEmpty()) {
    // handle the empty case
} else {
             
    int nbr_placee = Integer.parseInt(tf_nbr.getText());
     tf_nbr.setText(Integer.parseInt(tf_nbr.getText()) + "");
    // rest of the code that uses busNumber
}
        
        
        
        
        tf_nom.setText(nom);
        

    tf_prenom.setText(prenom);
 tf_pnt.setText(pnt_rencontre);
        tf_date.setText(date);
        
        tf_distination.setText(distination);
        
    }}