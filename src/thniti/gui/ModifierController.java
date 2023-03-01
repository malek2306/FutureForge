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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import thniti.entities.Reclamation;
import thniti.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField TypeR;
    @FXML
    private TextField DescriptionR;
    @FXML
    private TextField Objet;
    @FXML
    private TextField etat;
    Reclamation rec;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceReclamation s = new ServiceReclamation();
        initData(rec);
    }    

    @FXML
    private void modifier(ActionEvent event) {
        rec.setTypeR(TypeR.getText());
        rec.setDescriptionR(DescriptionR.getText());
        rec.setObjet(Objet.getText());
        rec.setEtat(etat.getText());
 

          ServiceReclamation s = new ServiceReclamation();

        // update the user in the database
        s.modifier(rec);

        // show a success message to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification réussie");
        alert.setHeaderText(null);
        alert.setContentText("L'etat a été modifié avec succès !");
        alert.showAndWait();
        
    }
    public void initData(Reclamation rec) {
    this.rec = rec;
    if (rec != null) {
        TypeR.setText(rec.getTypeR());
        DescriptionR.setText(rec.getDescriptionR());
        Objet.setText(rec.getObjet());
        etat.setText(rec.getEtat());
        
        
    
    }
}
    
}
