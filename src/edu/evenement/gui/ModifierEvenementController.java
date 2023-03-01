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
public class ModifierEvenementController implements Initializable {
 
   
    private TextField txtEtat;
    @FXML
    private Button Modifier;
private Categories etat;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdescription;
   

private Evenement ev ;
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtdate;

 public void initData(Evenement ev) {
    this.ev = ev;
    if (ev != null) {
        txtnom.setText(ev.getNom());
        txttype.setText(ev.getType());
        txtdescription.setText(ev.getDescription());
        txtdate.setText(ev.getDate());
     
        }}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Serviceevenement s = new Serviceevenement ();
        initData(ev);
        // TODO
    }    

    @FXML
    private void BtnModifierEtat(ActionEvent event) {
       ev.setNom(txtnom.getText());
        ev.setType(txttype.getText());
       ev.setDescription(txtdescription.getText());
         ev.setDate(txtdate.getText());

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
 
    
    
}  

