/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.gui;


import edu.evenement.entities.Categories;
import edu.evenement.entities.Evenement;
import edu.evenement.services.Servicecategories;
import edu.evenement.services.Serviceevenement;
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
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class AjouterEvenementController implements Initializable {

    private TextField tfcatevent;
    @FXML
    private Button addcatevent;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdescription;
    private TextField tfphoto;
        UploadServices uploadservices= new UploadServices();
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tftype;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addcatevent(ActionEvent event) throws SQLException {
         String noms = tfnom.getText();
        String type = tftype.getText();
        String description = tfdescription.getText();
        String date = tfdate.getText();
        
          Categories categ = new Categories("test","test","test");
    categ.setId (6);
        Evenement ce =new Evenement(noms,type,description,date,categ);
        Serviceevenement ces =new Serviceevenement();
        ces.ajouter(ce);
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Votre etat est ajouté");
alert1.showAndWait();
    }

    private void upload(ActionEvent event) {
           FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getName();
           tfphoto.setText(imageFile);
        
    }

    }
    
}
