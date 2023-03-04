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
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author FARAH
 */
public class ModifierEvenementController implements Initializable {
 
   
    private TextField txtEtat;
    @FXML
    private Button Modifier;

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdescription;
   

private Evenement ev ;
    @FXML
    private TextField txttype;
    @FXML
    private DatePicker txtdate;
    @FXML
    private ChoiceBox<Categories> categ_id;
    

 public void initData(Evenement ev) {
    this.ev = ev;
    if (ev != null) {
        txtnom.setText(ev.getNom());
        txttype.setText(ev.getType());
        txtdescription.setText(ev.getDescription());
          txtdate.setValue(ev.getDate());
           Categories categ = categ_id.getValue();
    }
     
        }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Categories> categories = setCategories();
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
    private void BtnModifierEtat(ActionEvent event) {
       ev.setNom(txtnom.getText());
        ev.setType(txttype.getText());
       ev.setDescription(txtdescription.getText());
       ev.setDate(txtdate.getValue());
         Categories categ = categ_id.getValue();
     List<Categories> categories = setCategories();
     
        

          Serviceevenement s = new Serviceevenement();

        // update the user in the database
        s.modifier(ev);

        // show a success message to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification réussie");
        alert.setHeaderText(null);
        alert.setContentText("L'etat a été modifié avec succès !");
        alert.showAndWait();
    }
 
   
   private void categ_id(ActionEvent event) throws SQLException {
    Categories category = categ_id.getValue();
    if (category != null) {
        tfcatevent.setText(String.valueOf(category.getId()));
    }
    } 
    
}  

