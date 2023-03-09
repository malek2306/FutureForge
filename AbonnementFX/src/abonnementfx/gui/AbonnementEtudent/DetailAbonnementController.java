/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementEtudent;

import abonnementFX.Entities.Abonnement;
import abonnementFX.Entities.TypeAbonnement;
import abonnementFX.Services.AbonnementService;
import abonnementfx.Util.Variables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailAbonnementController implements Initializable {

    @FXML
    private ImageView imageEtu;
    @FXML
    private Text type;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField desc;
    @FXML
    private TextField cin;
    @FXML
    private TextField mail;
    @FXML
    private TextField dateD;
    @FXML
    private TextField dateF;
private AbonnementService as=new AbonnementService();
    @FXML
    private Pane pane;
    @FXML
    private Button button;
    @FXML
    private Text noAbonnement;
    @FXML
    private Text cds1;
    @FXML
    private Text cds2;
    @FXML
    private Text cds4;
    @FXML
    private Text cds5;
    @FXML
    private Text cds3;
    @FXML
    private Text cds;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Abonnement ab = as.get_abonnementByIdentifiantDate(Variables.identifiantUserConnecte);
        if (ab==null){
            pane.setVisible(false);
            button.setDisable(true);
            noAbonnement.setVisible(true);
        }
        else {
            pane.setVisible(true);
            button.setDisable(false);
            noAbonnement.setVisible(false);
            InputStream stream2;
            try {
                if (!ab.getImg().equals(null) && !ab.getImg().equals("")){
                    stream2 = new FileInputStream(ab.getImg());
                    Image image2 = new Image(stream2);
                    imageEtu.setImage(image2);
                }
                else imageEtu.setImage(null);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DetailAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
            }
               nom.setText(ab.getNom());
               prenom.setText(ab.getNom());
               desc.setText(ab.getIdentifiant());
               cin.setText(ab.getCin());
               mail.setText(ab.getNom());
               dateD.setText(ab.getDateDebut().toString());
               dateF.setText(ab.getDateFin().toString());
        }
    }    

    @FXML
    private void editAbonnement(ActionEvent event) {
        boolean valide = true;
        Abonnement abonnement = new Abonnement();
        abonnement.setNom(nom.getText());
        abonnement.setPrenom(prenom.getText());
        
        String pattern ="^\\S+\\..*@esprit\\.tn$";
        Pattern emailRegex = Pattern.compile(pattern);
        Matcher matcher = emailRegex.matcher(mail.getText());
        if(matcher.matches())  {
            abonnement.setEmail(mail.getText());
            cds5.setVisible(false);
        }
        else {
            valide = false;
            cds5.setVisible(true);
        }
        pattern = "\\d{3}(JMT|JFT)\\d{4}";
        Pattern regex = Pattern.compile(pattern);
        matcher = regex.matcher(desc.getText());
        if (matcher.matches()){
            abonnement.setIdentifiant(desc.getText());
            cds3.setVisible(false);
        }
        else {
            valide = false;
            cds3.setVisible(true);
        }
        pattern = "\\d{8}";        
        regex = Pattern.compile(pattern);
        matcher = regex.matcher(cin.getText());
        if (matcher.matches()){
            abonnement.setCin(cin.getText());
            cds4.setVisible(false);
        }
        else {
            valide = false;
            cds4.setVisible(true);
        }
        if (nom.getText().equals("")){
            valide = false;
            cds1.setVisible(true);
        }
        else cds1.setVisible(false);
        if (prenom.getText().equals("")){
            valide = false;
            cds2.setVisible(true);
        }
        else cds2.setVisible(false);
        
        
        if (valide==true)
            as.ajouter_abonnement(abonnement);
        else {
            cds.setVisible(true);
        }
        Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        primaryStage.close(); 
    }
    
}
