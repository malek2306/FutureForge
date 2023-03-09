/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementAdmin;

import abonnementFX.Entities.OffreAbonnement;
import abonnementFX.Services.OffreService;
import abonnementfx.Util.Variables;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EditOffreController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField reduction;
    @FXML
    private TextArea description;
private OffreService os=new OffreService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nom.setText(Variables.offreSelected.getNom());
        reduction.setText(String.valueOf(Variables.offreSelected.getReduction()));
        description.setText(Variables.offreSelected.getDescription());
    }    

    @FXML
    private void updateOffre(ActionEvent event) {
        OffreAbonnement offre = new OffreAbonnement();
        offre.setNom(nom.getText());
        offre.setDescription(description.getText());
        offre.setReduction(Double.parseDouble(reduction.getText()));
        os.modifier_offre(Variables.offreSelected.getId_ofab(), offre);
        Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        primaryStage.close(); 
    }
    
}
