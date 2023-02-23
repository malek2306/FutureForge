/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementAdmin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class BareOptionController implements Initializable {

    @FXML
    private Button offrebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherOffre(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageOffre.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,1920, 1080);  
            Stage primaryStage = (Stage) offrebtn.getScene().getWindow();;
            primaryStage.setScene(scene);  
            primaryStage.setMaximized(true);
            primaryStage.show(); 
    }

    @FXML
    private void afficherAbonnement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageAbonnement.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,1920, 1080);  
            Stage primaryStage = (Stage) offrebtn.getScene().getWindow();;
            primaryStage.setScene(scene);  
            primaryStage.setMaximized(true);
            primaryStage.show();
    }
    
}
