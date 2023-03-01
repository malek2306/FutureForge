/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterCovController implements Initializable {

    @FXML
    private AnchorPane VBOX;
    @FXML
    private Button ajooouut;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_pnt;
    @FXML
    private Button reeeetouuuur;
    @FXML
    private TextArea tf_distination;
    @FXML
    private TextArea tf_date;
    @FXML
    private TextArea tf_nbr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btAjouter(ActionEvent event) {
    }

    @FXML
    private void btclean(ActionEvent event) {
    }

    @FXML
    private void btnretour(MouseEvent event) {
    }
    
}
