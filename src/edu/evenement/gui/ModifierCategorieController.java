/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.gui;


import edu.evenement.entities.Categories;
import edu.evenement.services.Servicecategories;
import edu.evenement.services.UploadServices;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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
    private ImageView txtphoto;

private Categories cat;
 UploadServices uploadservices= new UploadServices();
    @FXML
    private Button upload;

 public void initData(Categories cat) {
    this.cat = cat;
    if (cat != null) {
        txtnom.setText(cat.getNom());
        txtdescription.setText(cat.getDescription());
          if (cat.getPhoto() != null) {
            Image image = new Image(new ByteArrayInputStream(cat.getPhoto()));
            txtphoto.setImage(image);
        }
    }
}
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
          Image image = txtphoto.getImage();
    byte[] photo = null;
    if (image != null) {
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        byte[] buffer = new byte[width * height * 4];
        image.getPixelReader().getPixels(0, 0, width, height, javafx.scene.image.PixelFormat.getByteBgraInstance(), buffer, 0, width * 4);
        photo = buffer;
    }
    cat.setPhoto(photo);
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
      @FXML
    private void upload(ActionEvent event) {
             FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getName();
            Image image = new Image(f.toURI().toString());
            txtphoto.setImage(image);
        }
    }
 
    
    
}  

