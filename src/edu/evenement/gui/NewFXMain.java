/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author FARAH
 */
public class NewFXMain extends Application {
    private AnchorPane root ;
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("listeCategories.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("la list");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
