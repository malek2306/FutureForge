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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import thniti.services.ServiceMessagerie;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class SuppMessageController implements Initializable {

    @FXML
    private TextField id_M;
    @FXML
    private Button suppM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void suppM(ActionEvent event) {
        int id = Integer.parseInt(id_M.getText());
        supprimerMessagerie(id);
    }
    private void supprimerMessagerie(int id) {
        ServiceMessagerie commentaireService = new ServiceMessagerie();
        commentaireService.supprimer(id);}
    }
    

