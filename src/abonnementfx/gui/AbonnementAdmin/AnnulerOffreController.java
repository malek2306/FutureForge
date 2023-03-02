/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementAdmin;

import abonnementFX.Services.OffreService;
import abonnementfx.Util.Variables;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AnnulerOffreController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private OffreService os=new OffreService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void annulerOffre(ActionEvent event) {
        os.annuler_offre(Variables.offreSelected.getId_ofab());
        Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        primaryStage.close(); 
    }

    @FXML
    private void cancelOffre(ActionEvent event) {
        Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        primaryStage.close(); 
    }
    
}
