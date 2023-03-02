/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementEtudent;

import abonnementFX.Entities.Abonnement;
import abonnementFX.Entities.OffreAbonnement;
import abonnementFX.Entities.TypeAbonnement;
import abonnementFX.Services.AbonnementService;
import abonnementfx.Util.Variables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterAbonnementController implements Initializable {

    @FXML
    private ImageView imgEtudiant;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField identifiant;
    @FXML
    private TextField cin;
    @FXML
    private ComboBox<String> type;
    @FXML
    private DatePicker dateD;
    @FXML
    private Text prixSR;
    @FXML
    private Text reduction;
    @FXML
    private Text prixAR;
    @FXML
    private Button addBTN;
private static String imgPath = "";
    /**
     * Initializes the controller class.
     */
private AbonnementService as=new AbonnementService();
    @FXML
    private Text cds1;
    @FXML
    private Text cds2;
    @FXML
    private Text cds3;
    @FXML
    private Text cds4;
    @FXML
    private Text cds5;
    @FXML
    private Text cds6;
    @FXML
    private Text cds;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (Variables.offreSelected.getType().equals(TypeAbonnement.MENSUEL)){
            type.setValue(TypeAbonnement.MENSUEL.toString());
        }
        else if (Variables.offreSelected.getType().equals(TypeAbonnement.SEMESTRILLE)){
            type.setValue(TypeAbonnement.SEMESTRILLE.toString());
        }
        else if (Variables.offreSelected.getType().equals(TypeAbonnement.ANNUELLE)){
            type.setValue(TypeAbonnement.ANNUELLE.toString());
        }
        if (!Variables.offreSelected.getNom().equals(null)){
            if (Variables.offreSelected.getType().equals(TypeAbonnement.MENSUEL))
                prixSR.setText(String.valueOf(Variables.prixMensuel));
            else if (Variables.offreSelected.getType().equals(TypeAbonnement.SEMESTRILLE))
                prixSR.setText(String.valueOf(Variables.prixSemestrille));
            else if (Variables.offreSelected.getType().equals(TypeAbonnement.ANNUELLE))
                prixSR.setText(String.valueOf(Variables.prixAnnuelle));
            prixAR.setText(String.valueOf(Double.parseDouble(prixSR.getText())*(100-Variables.offreSelected.getReduction())/100));
            reduction.setText(String.valueOf(Double.parseDouble(prixSR.getText())-Double.parseDouble(prixAR.getText())));
        }
    }    


    @FXML
    private void addAbonnement(ActionEvent event) throws MessagingException {
        boolean valide = true;
        Abonnement abonnement = new Abonnement();
        abonnement.setNom(nom.getText());
        abonnement.setPrenom(prenom.getText());
        
        String pattern ="^\\S+\\..*@esprit\\.tn$";
        Pattern emailRegex = Pattern.compile(pattern);
        Matcher matcher = emailRegex.matcher(email.getText());
        if(matcher.matches())  {
            abonnement.setEmail(email.getText());
            cds3.setVisible(false);
        }
        else {
            valide = false;
            cds3.setVisible(true);
        }
        pattern = "\\d{3}(JMT|JFT)\\d{4}";
        Pattern regex = Pattern.compile(pattern);
        matcher = regex.matcher(identifiant.getText());
        if (matcher.matches()){
            abonnement.setIdentifiant(identifiant.getText());
            cds4.setVisible(false);
        }
        else {
            valide = false;
            cds4.setVisible(true);
        }
        pattern = "\\d{8}";        
        regex = Pattern.compile(pattern);
        matcher = regex.matcher(cin.getText());
        if (matcher.matches()){
            abonnement.setCin(cin.getText());
            cds5.setVisible(false);
        }
        else {
            valide = false;
            cds5.setVisible(true);
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
        java.util.Calendar calendard = java.util.Calendar.getInstance();
        calendard.setTime(java.sql.Date.valueOf(dateD.getValue()));
        java.util.Calendar calendarP = java.util.Calendar.getInstance();
        if (calendard.compareTo(calendarP)<0){
            valide = false;
            cds6.setVisible(true);
        }
        else cds6.setVisible(false);
        abonnement.setDateDebut(java.sql.Date.valueOf(dateD.getValue()));
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(java.sql.Date.valueOf(dateD.getValue()));
        if (Variables.offreSelected.getType().equals(TypeAbonnement.MENSUEL)){
            calendar.add(java.util.Calendar.MONTH, 1);
            abonnement.setType(TypeAbonnement.valueOf(Variables.offreSelected.getType().toString()));
        }
        else if (Variables.offreSelected.getType().equals(TypeAbonnement.SEMESTRILLE)){
            calendar.add(java.util.Calendar.MONTH, 6);
            abonnement.setType(TypeAbonnement.valueOf(Variables.offreSelected.getType().toString()));
        }
        else if (Variables.offreSelected.getType().equals(TypeAbonnement.ANNUELLE)){
            calendar.add(java.util.Calendar.YEAR, 1);
            abonnement.setType(TypeAbonnement.valueOf(Variables.offreSelected.getType().toString()));
        }
        abonnement.setDateFin(new java.sql.Date(calendar.getTime().getTime()));
        abonnement.setPrix(Double.parseDouble(prixAR.getText()));
        
        abonnement.setImg(imgPath);
        abonnement.setId_offre(Variables.offreSelected.getId_ofab());
        if (valide==true){
            as.ajouter_abonnement(abonnement);
            sendMail(email.getText());}
        else {
            cds.setVisible(true);
        }
        Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        primaryStage.close(); 
    }

    @FXML
    private void ajouterImage(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        String extension = null;
        if(selected !=null )
        {
             extension= selected.getAbsolutePath().substring(selected.getAbsolutePath().length()-3,selected.getAbsolutePath().length());
            System.out.println(extension);
            if(!extension.equals( "jpg") && !extension.equals("png"))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid picture");
        
        alert.setContentText("Invalid picture format (only jgp/png available)"); 
     
        alert.showAndWait();
        }else
            imgEtudiant.setImage(null);
        }
        InputStream stream = new FileInputStream(selected.getAbsolutePath());
      Image image = new Image(stream);
        imgEtudiant.setImage(image);
            imgPath=selected.getAbsolutePath();
    }
public void sendMail(String recipient){
        System.out.println(recipient);
        
        String host="ghada.bensaidmeddeb@esprit.tn";  //← my email address
        final String user="ghada.bensaidmeddeb@esprit.tn";//← my email address
        final String password="223AFT2183";//change accordingly   //← my email password

        String to=recipient;//→ the EMAIL i want to send TO →
        Properties props = new Properties (); 
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        
       Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //My message :
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(" Notification de reponse de Réclamation !!! ");
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
            message.setContent(
                    "<h1 style \"color:red\" >Ajout une abonnement avec succée </h1> <br/> <img width=\"50%\" height=\"50%\" src=https://i.imgur.com/iYcBkOf.png>",
                    "text/html");

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {e.printStackTrace();}

    }

}
