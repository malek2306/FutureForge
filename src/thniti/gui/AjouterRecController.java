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
import thniti.Services.BadWordsFilter;
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
       
        BadWordsFilter filter = new BadWordsFilter();
    //String inputText = "This is a test sentence that contains a bad word: shit";
    if (filter.containsBadWord(description)) {
    // Display an error message or take other actions as needed
     Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
       
        alert.setContentText("The input description contains a bad word.");
        alert.showAndWait();
    System.out.println("The input text contains a bad word.");
} else {
     Reclamation ce =new Reclamation( Type,  description,  objets, date,  "non traité", 1);
       ServiceReclamation ces =new ServiceReclamation();
        ces.ajouter(ce);
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Votre etat est ajouté");
alert1.showAndWait();
        
    }
       
      
    
}


}
