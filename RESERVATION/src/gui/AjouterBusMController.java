/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import API.SendSMS;
//import API.sendEMAIL;
import entities.Reservation_bus;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.Iservice;
import services.ServiceReservationBus;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.DataSource;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterBusMController implements Initializable {

    @FXML
    private AnchorPane VBOX;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_num;
    @FXML
    private Button ajooouut;
    @FXML
    private Button reeeetouuuur;
    @FXML
    private TextArea tf_date;
    @FXML
    private TextArea tf_destination;
    @FXML
    private TextArea tf_email;
    
    
        public static boolean isValidDate(String dateString) {
    String[] dateParts = dateString.split("/");
    if (dateParts.length != 3) {
      return false;
    }
    
    int day;
    int month;
    int year;
    try {
      day = Integer.parseInt(dateParts[0]);
      month = Integer.parseInt(dateParts[1]);
      year = Integer.parseInt(dateParts[2]);
    } catch (NumberFormatException e) {
      return false;
    }
    
    if (month < 1 || month > 12) {
      return false;
    }
    if (year <2023){return false;}
    
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int maxDays = daysInMonth[month - 1];
    if (month == 2 && isLeapYear(year)) {
      maxDays = 29;
    }
    
    if (day < 1 || day > maxDays) {
      return false;
    }
    
    return true;
  }
  
  public static boolean isLeapYear(int year) {
    if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
      return true;
    }
    return false;
  }
    


    
    public static boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$";
    Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}

 private boolean checkUserExistence(String nom, String prenom) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thnity(1)", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM reservation_bus WHERE nom='" + nom + "' AND prenom='" + prenom + "'");
        if (rs.next()) {
            return true;
        }
        conn.close();
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la vérification de l'existence de l'utilisateur : " + ex.getMessage());
    }
    return false;
}
 
 private void sendEmail() {
        String from = "maleksoukeh24062001@gmail.com" ;
        String password = "123456789malouka";
        String to = tf_email.getText();
        String subject = "Création de votre réservation";
        String message = "Cher mongi,\"\n" +
"            + \"\\n\\n votre reservation a été effectée avec succé ";
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email Sent");
            alert.setHeaderText(null);
            alert.setContentText("Your email has been sent successfully.");
            alert.showAndWait();
            //stage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Error");
            alert.setHeaderText(null);
            alert.setContentText("There was an error sending your email. Please try again later.");
            alert.showAndWait();
        }
    }
    
    
    
    
    
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    @FXML
    private void btAjouter(ActionEvent event) throws SQLException  {
        String nom = tf_nom.getText();
        String prenom = tf_prenom.getText();
        String num = tf_num.getText();
        String dat = tf_date.getText();
        String destination = tf_destination.getText();
        String email = tf_email.getText();
        
        
        if (nom.isEmpty() || prenom.isEmpty()|| num.isEmpty() || dat.isEmpty() || destination.isEmpty() || email.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Veuiller Remplir Les champs!!"); // controle de saisie
             alert.showAndWait(); }
       
      else if(checkUserExistence(nom, prenom)==true){
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Nom et Prenom deja existent"); // controle de saisie
             alert.showAndWait(); 
        }
        else if (isValidDate(dat)==false){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Date non valide"); // controle de saisie
             alert.showAndWait(); 
        
        
        }else if (isValidEmail(email)==false){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("email non valide"); // controle de saisie
             alert.showAndWait(); 
        
        
        }
        else {
        ServiceReservationBus is = new ServiceReservationBus();
            Reservation_bus r = new Reservation_bus(1,nom, prenom, Integer.parseInt(num),dat,email,destination);
        is.ajouter(r);
        //CheckBox checkBox = new CheckBox("Enable Feature");
        //checkBox.setOnAction(new EventHandler<ActionEvent>() {
    //@Override
    //public void handle(ActionEvent event) {
      //  if (sms.isSelected()) {
           // SendSMS sm = new SendSMS();
            //sm.sendSMS(r);
        //} else if (emailllll.isSelected()){
          //  sendEmail();
          String title = "Reservation";
          String message = "Réservation ajoutée avec succès" ;
          TrayNotification tray = new TrayNotification();
          AnimationType type = AnimationType.POPUP;
          
          tray.setAnimationType(type);
          tray.setTitle(title);
          tray.setMessage(title);
          tray.setNotificationType(NotificationType.SUCCESS);
          tray.showAndDismiss(Duration.millis(3000));
        }
    }

        
        
       
        
            
                    
                    
        
        
        
        
        
        
        
    

    @FXML
    private void btclean(ActionEvent event) {
        tf_nom.setText(null);
                tf_prenom.setText(null);
                tf_num.setText(null);
    }

    @FXML
    private void btnretour(MouseEvent event) {
            VBOX.getScene().getWindow().hide();
        Stage Affiche = new Stage();
        Parent fxml;
        try {
            fxml = FXMLLoader.load(getClass().getResource("tableView.fxml"));
      
        
        Scene scene = new Scene(fxml);
        Affiche.setScene(scene);
        Affiche.show();
          } catch (IOException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
        
    }
    

