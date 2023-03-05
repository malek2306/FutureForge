/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author farah
 */
public class BackController implements Initializable {
    
    private BorderPane BorderPane;
    @FXML
    private Button Evenement;
    @FXML
    private Button Categorie;

    /**
     * Initializes the controller class.
     */
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

    @FXML
    private void Evenement(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));
            //Scene scene = new Scene(root, 1100, 650);
            Scene scene = new Scene(root);//fhemtha faza edhyka imchi hajet tefha le:p hhh
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            System.out.println("ok");
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @FXML
    private void Categorie(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierCategorie.fxml"));
            //Scene scene = new Scene(root, 1100, 650);
            Scene scene = new Scene(root);//fhemtha faza edhyka imchi hajet tefha le:p hhh
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            System.out.println("ok");
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    
}
