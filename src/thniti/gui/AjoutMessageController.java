/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import thniti.entities.Messagerie;
import thniti.services.ServiceMessagerie;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class AjoutMessageController implements Initializable {

    @FXML
    private TextField contenuM;
    @FXML
    private TextField etat;
    @FXML
    private Button addM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addM(ActionEvent event) {
        ServiceMessagerie msgService = new ServiceMessagerie();
    String type = contenuM.getText();
    String description = etat.getText();
    

    // Perform input validation and error handling here

    Messagerie comment = new Messagerie(contenuM, etat); // use localDate here
    try {                                  //try de controle de saisie
  msgService.ajouter(comment);
} catch (IllegalArgumentException ex) {
    System.out.println(ex.getMessage());
    

    // Clear fields after adding article
    contenuM.clear();
    etat.clear();
    
}
    }
    
}
