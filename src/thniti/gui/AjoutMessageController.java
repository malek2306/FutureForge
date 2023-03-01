/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import thniti.entities.Messagerie;
import thniti.entities.Reclamation;
import thniti.services.ServiceMessagerie;
import thniti.services.ServiceReclamation;

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
        
    
 String ContenuM = contenuM.getText();
     String Etat = etat.getText();
     
       String etats ="en cours";
       Date date = new Date(System.currentTimeMillis());
        Messagerie ce =new Messagerie( ContenuM,  "non traité");
       ServiceMessagerie ces =new ServiceMessagerie();
        ces.ajouter(ce);
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Votre etat est ajouté");
alert1.showAndWait();
    }
    
    
}
