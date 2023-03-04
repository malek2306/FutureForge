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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class AjouterEvenementController implements Initializable {

     @FXML private TextField tfcatevent;
    @FXML private Button addcatevent;
    @FXML private TextField tfnom;
    @FXML private TextField tfdescription;
    @FXML private TextField tfphoto;
    @FXML private DatePicker tfdate;
    @FXML private TextField tftype;
    @FXML private ChoiceBox<Categories> categ_id;

    private List<Categories> categories;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categories = setCategories();
        categ_id.getItems().addAll(categories);
    
    }

    /**
     * Retrieves all the categories from the database
     */
    private List<Categories> setCategories() {
        // Replace this with actual code to retrieve categories from the database
        List<Categories> categories = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/farah");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM categories")) {
        while (rs.next()) {
            Categories c = new Categories(rs.getInt(1), rs.getString("nom"), rs.getString("description"),rs.getBytes("photo"));
            categories.add(c);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return categories;

    }

    @FXML
    private void addcatevent(ActionEvent event) throws SQLException {
        String noms = tfnom.getText();
        String type = tftype.getText();
        String description = tfdescription.getText();
        LocalDate date = tfdate.getValue();
        Categories categ = categ_id.getValue();
     List<Categories> categories = setCategories();
    boolean categorieExiste = categories.contains(categ);
       


        Evenement ce = new Evenement(noms, type, description, date, categ);
        Serviceevenement ces = new Serviceevenement();
        ces.ajouter(ce);
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Votre evenement est ajouté");
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

   private void categ_id(ActionEvent event) throws SQLException {
    Categories category = categ_id.getValue();
    if (category != null) {
        tfcatevent.setText(String.valueOf(category.getId()));
    }
    }}

    
    
    
