/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementAdmin;

import abonnementFX.Services.AbonnementService;
import abonnementfx.Util.Variables;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailAbonnementController implements Initializable {
        private AbonnementService as=new AbonnementService();

    @FXML
    private Pane pane5;
    @FXML
    private Text dateD;
    @FXML
    private Text dateF;
    @FXML
    private Text identifiant;
    @FXML
    private Text prenom;
    @FXML
    private Text nom;
    @FXML
    private Text type;
    @FXML
    private Text CIN;
    @FXML
    private Text Gmail;
    @FXML
    private ImageView imageEtu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dateD.setText(Variables.abonnementSelected.getDateDebut().toString());
                dateF.setText(Variables.abonnementSelected.getDateFin().toString());
        type.setText(Variables.abonnementSelected.getType().toString());
        nom.setText(Variables.abonnementSelected.getNom());
        prenom.setText(Variables.abonnementSelected.getPrenom());
        identifiant.setText(Variables.abonnementSelected.getIdentifiant());
        CIN.setText(Variables.abonnementSelected.getCin());
        Gmail.setText(Variables.abonnementSelected.getEmail());
        //imageEtu.setText(Variables.abonnementSelected.getCin());
    }    

    @FXML
    private void deleteAbonnement(ActionEvent event) throws IOException {
        as.annuler_abonnement(Variables.abonnementSelected.getId_ab());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageAbonnement.fxml"));
            Parent root = loader.load();
            Stage primaryStage = (Stage) pane5.getScene().getWindow();
            primaryStage.close();
    }
    
}
