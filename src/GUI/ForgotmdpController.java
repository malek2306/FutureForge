/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.ServiceUser;
import util.Login;

/**
 * FXML Controller class
 *
 * @author Name
 */
public class ForgotmdpController implements Initializable {
    
    @FXML
    private TextField old;
    @FXML
    private TextField newmdp;
    @FXML
    private TextField confirm;
    @FXML
    private Label message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void ConfirmChange(ActionEvent event) {
        ServiceUser sut = new ServiceUser();
        Login login = Login.getInstance();
        if (old.getText().equals(login.getPassword())) {
            message.setText("le mot de passe ne peut pas être le même que l'ancien mot de passe");
        } else if (newmdp.getText().isEmpty()) {
            message.setText("inserer nouveau mot de pasee");
        } else if (confirm.getText().isEmpty()) {
            message.setText("confirmer nouveau mot de passe");
        } else if (newmdp.getText().equals(confirm.getText())) {
            message.setText("cest pas le meme mot de passe");
        }
        
    }
    
}
