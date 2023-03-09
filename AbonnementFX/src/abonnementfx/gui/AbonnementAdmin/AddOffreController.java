/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementAdmin;

import abonnementFX.Entities.OffreAbonnement;
import abonnementFX.Entities.TypeAbonnement;
import abonnementFX.Services.OffreService;
import abonnementfx.Util.Variables;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddOffreController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField reduction;
    @FXML
    private ComboBox<String> type;
    @FXML
    private DatePicker dateD;
    @FXML
    private TextArea description;
    private OffreService os=new OffreService();
    @FXML
    private DatePicker dateF;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setValue(Variables.offre.getType().toString());
    }    

    @FXML
    private void addOffre(ActionEvent event) {
        OffreAbonnement offreNew = new OffreAbonnement();
        offreNew.setNom(nom.getText());
        offreNew.setDescription(description.getText());
        offreNew.setReduction(Double.parseDouble(reduction.getText()) );
        offreNew.setType(TypeAbonnement.valueOf( type.getValue()));
        offreNew.setDate_debut(java.sql.Date.valueOf(dateD.getValue()));
        offreNew.setDate_fin(java.sql.Date.valueOf(dateF.getValue()));
        os.ajouter_offre(offreNew);
        
        //AffichageOffreController aoc = new AffichageOffreController();
        //aoc.displayPaneOffres();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes!");
        alert.setContentText("Offre added successfuly");
        alert.show();
        Stage primaryStage = (Stage) nom.getScene().getWindow();
        primaryStage.close(); 
    }

    
}
