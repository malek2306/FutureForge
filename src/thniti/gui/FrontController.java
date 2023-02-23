/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.gui;

import java.io.IOException;
import thniti.entities.Reclamation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class FrontController implements Initializable {

    @FXML
    private BorderPane BorderPane;
    @FXML
    private AnchorPane ajouterButtton;
    @FXML
    private Button ajouterButtton1;
    @FXML
    private Button modif;
    @FXML
    private Button supp;
    @FXML
    private Button ajouterMessage;
    @FXML
    private Button suppM;
    
    

    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     private void loadUi(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        BorderPane.setCenter(root); 
    }
    private void reclamation(ActionEvent event) {
        loadUi("reclamation");
        System.out.println("ok");
    }
//private void ajouter(ActionEvent event) {
    // Code to handle button click here
//}

    @FXML
    private void ajouterButtton1(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter.fxml"));
            Parent root = loader.load();
            AjouterController pc = loader.getController();
            
            ajouterButtton1.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void modif(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifier.fxml"));
        Parent root = loader.load();
        ModifierController pc = loader.getController();
        modif.getScene().setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
}

    @FXML
    private void supp(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("supprimer.fxml"));
        Parent root = loader.load();
        SupprimerController pc = loader.getController();
        supp.getScene().setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @FXML
    private void ajouterMessage(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutMessage.fxml"));
        Parent root = loader.load();
        AjoutMessageController pc = loader.getController();
        ajouterMessage.getScene().setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @FXML
    private void suppM(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("suppMessage.fxml"));
        Parent root = loader.load();
        SuppMessageController pc = loader.getController();
        suppM.getScene().setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
}

