/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.gui;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import thniti.entities.Reclamation;
import thniti.services.ServiceReclamation;

public class ModifierController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modifier(ActionEvent event) {
    ServiceReclamation serviceReclamation = new ServiceReclamation();
    Reclamation reclamation = new Reclamation(); // create a new Reclamation object

    reclamation.setTypeR(TypeR.getText());
    reclamation.setDescriptionR(DescriptionR.getText());
    reclamation.setObjet(Objet.getText());
    //reclamation.setDateR(Date.valueOf(DateR.getValue()));
    reclamation.setEtat(etat.getText());

    // modify the existing record in the database using the serviceReclamation object
    //serviceReclamation.modifier(reclamation);

    // clear the text fields and date picker
    TypeR.clear();
    DescriptionR.clear();
    Objet.clear();
    DateR.setValue(null);
    etat.clear();
}

}