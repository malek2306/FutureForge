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
import thniti.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class SupprimerController implements Initializable {

    @FXML
    private Button supprimer;
    @FXML
    private TextField id_R;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimer(ActionEvent event) {
        int id = Integer.parseInt(id_R.getText());
        supprimerReclamation(id);
        // autres opérations après la suppression
    }

    private void supprimerReclamation(int id) {
        ServiceReclamation commentaireService = new ServiceReclamation();
        commentaireService.supprimer(id);}
    }
    

