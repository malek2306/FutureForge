/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation_covoiturage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.ServiceReservationCovoiturage;
import utiles.DataSource;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutercovoiturageController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_distination;
    @FXML
    private TextField tf_pnt;
    @FXML
    private DatePicker tf_date;
    
    private boolean update;
    ServiceReservationCovoiturage sp = new ServiceReservationCovoiturage();
    int x = Integer.parseInt(tf_nom.getText());
    Reservation_covoiturage rb;
   // Reservation_covoiturage rb = new Reservation_covoiturage(tf_nom.getText(), tf_prenom.getText(), x);
    @FXML
    private TextField tfnbr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btajouter(ActionEvent event) {
         Connection cnx = DataSource.getInstance().getCnx();
        
            Alert alert;
            if (tf_nom.getText().isEmpty() || tf_prenom.getText().isEmpty() || tf_pnt.getText().isEmpty()
                    || tf_distination.getText().isEmpty() || tfnbr.getText().isEmpty() ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("fill all blank fields");
                alert.showAndWait();
            } else {
            try {
                //try {
                String checkDATA = " SELECT nom FROM reservation_covoiturage WHERE nom = '"
                        + tf_nom.getText() + "'";
                PreparedStatement prepare = cnx.prepareStatement(checkDATA);
                ResultSet result = prepare.executeQuery();
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(tf_nom.getText() + "  IS ALREADY TAKEN ! ");
                    alert.showAndWait();
                } else if ( update == false ){
                    
                
                
                sp.ajouter(rb);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Reservation ADDED !", ButtonType.OK);
                a.showAndWait();
                } sp.modifier1(rb);
                } catch (SQLException ex) {
                Logger.getLogger(AjouterBUSController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            

        
    }

    @FXML
    private void btclean(ActionEvent event) {
        tf_nom.setText(null);
        tf_prenom.setText(null);
        tf_pnt.setText(null);
        tf_distination.setText(null);
        tfnbr.setText(null);
        
        
        
        
    }

        
        
    }


    

