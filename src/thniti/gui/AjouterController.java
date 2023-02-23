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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import thniti.entities.Reclamation;
import thniti.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class AjouterController implements Initializable {

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
    
    ServiceReclamation commentaireService = new ServiceReclamation();
    String type = TypeR.getText();
    String description = DescriptionR.getText();
    String objet = Objet.getText();
    java.sql.Date date = java.sql.Date.valueOf(DateR.getValue());
    String Etat = etat.getText(); // assuming this is the attribute for the "etat" field

    // Perform input validation and error handling here

    Reclamation comment = new Reclamation(type, description, objet, date, Etat,2); // use localDate here
    try {                                  //try de controle de saisie
  commentaireService.ajouter(comment);
} catch (IllegalArgumentException ex) {
    System.out.println(ex.getMessage());
    

    // Clear fields after adding article
    TypeR.clear();
    DescriptionR.clear();
    Objet.clear();
    DateR.setValue(null);
    etat.clear();
}
    
}}
