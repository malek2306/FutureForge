/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.gui;


import edu.evenement.entities.Categories;
import edu.evenement.services.Servicecategories;
import edu.evenement.services.UploadServices;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class AjouterCategorieController implements Initializable {

    private TextField tfcatevent;
    @FXML
    private Button addcatevent;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfphoto;
    
        UploadServices uploadservices= new UploadServices();
    @FXML
    private Button upload;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void AjouterCategorieController (ActionEvent event) throws SQLException {
         String noms = tfnom.getText();
         String description = tfdescription.getText();
         String photo = tfphoto.getText();
            String FilenameInserver = uploadservices.upload(tfphoto.getText());
        System.out.println("fins"+FilenameInserver);
        Categories ce =new Categories(noms,description,photo);
        Servicecategories ces =new Servicecategories();
        ces.ajouter(ce);
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Votre categorie est ajouté");
alert1.showAndWait();
    }

    @FXML
    private void upload(ActionEvent event) {
           FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getName();
           tfphoto.setText(imageFile);
        
    }

    }

    @FXML
    private void addcatevent(ActionEvent event)throws SQLException {
        String noms = tfnom.getText();
         String description = tfdescription.getText();
         String photo = tfphoto.getText();
            String FilenameInserver = uploadservices.upload(tfphoto.getText());
        System.out.println("fins"+FilenameInserver);
        Categories ce =new Categories(noms,description,photo);
        Servicecategories ces =new Servicecategories();
        ces.ajouter(ce);
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Votre categorie est ajouté");
alert1.showAndWait();
    }

    

   
    
}
