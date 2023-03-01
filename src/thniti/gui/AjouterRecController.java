/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import thniti.entities.Reclamation;
import thniti.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class AjouterRecController implements Initializable {

    @FXML
    private TextField TypeR;
    @FXML
    private TextField DescriptionR;
    @FXML
    private TextField Objet;
    @FXML
    private DatePicker DateR;
    @FXML
    private TextField etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
private void add(ActionEvent event) {
    
    String Type = TypeR.getText();
     String description = DescriptionR.getText();
      String objets = Objet.getText();
       String etats ="en cours";
       Date date = new Date(System.currentTimeMillis());
        Reclamation ce =new Reclamation( Type,  description,  objets, date,  "non traité", 1);
       ServiceReclamation ces =new ServiceReclamation();
        ces.ajouter(ce);
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Votre etat est ajouté");
alert1.showAndWait();
    
}}
