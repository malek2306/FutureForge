/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;


/**
 *
 * @author 21692
 */
public class login extends Application {
    
    @Override
    public void start(Stage stage)throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("discussion.fxml"));

       Scene scene = new Scene(root);
       
       stage.initStyle(StageStyle.TRANSPARENT);
       scene.setFill(Color.TRANSPARENT);
       scene.getStylesheets().add("thniti.gui/style.css");
       
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Hello World!");
        //primaryStage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
