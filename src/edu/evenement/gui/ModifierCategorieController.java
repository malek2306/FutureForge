/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.gui;


import edu.evenement.entities.Categories;
import edu.evenement.services.Servicecategories;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author FARAH
 */
public class ModifierCategorieController implements Initializable {
 
   
    private TextField txtEtat;
    @FXML
    private Button Modifier;
private Categories etat;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtphoto;

private Categories cat;

 public void initData(Categories cat) {
    this.cat = cat;
    if (cat != null) {
        txtnom.setText(cat.getNom());
        txtdescription.setText(cat.getDescription());
        txtphoto.setText(cat.getPhoto());
     
        }}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Servicecategories s = new Servicecategories ();
        initData(etat);
        // TODO
    }    

    @FXML
    private void BtnModifierEtat(ActionEvent event) {
       cat.setNom(txtnom.getText());
       cat.setDescription(txtdescription.getText());
         cat.setPhoto(txtphoto.getText());

          Servicecategories s = new Servicecategories();

        // update the user in the database
        s.modifier(cat);

        // show a success message to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification réussie");
        alert.setHeaderText(null);
        alert.setContentText("L'etat a été modifié avec succès !");
        alert.showAndWait();
    }
 
    
    
}  

