/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.ServiceUser;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Name
 */
public class SignupController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private Button submit;
    @FXML
    private Label nom_wrong;
    @FXML
    private Label prenom_wrong;
    @FXML
    private Label email_wrong;
    @FXML
    private Label tel_wrong;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField cmdp;
    @FXML
    private TextField uname;
    @FXML
    private Label mdp_wrong;
    @FXML
    private Label uname_wrong;
    @FXML
    private Label cmdp_wrong;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void submit(ActionEvent event) throws NoSuchAlgorithmException {
        //controle nom
        int ok = 1;
        String n = nom.getText();
        String p = prenom.getText();
        String u = uname.getText();
        String e = email.getText();
        String t = tel.getText();
        String m = mdp.getText();
        String cm = cmdp.getText();
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        String hashedPassword = "";
        ServiceUser sut = new ServiceUser();
        //contorle nom
        if (n.isEmpty()) {
            nom.setStyle("-fx-border-color: red;");
            nom_wrong.setText("Insérez votre nom ici!!");
            ok = 0;
        } else if (!n.matches("^[a-zA-Z]*$")) {
            nom.setStyle("-fx-border-color: red;");
            nom_wrong.setText("Le nom doit contenir des lettres alphabétiques!!");
            ok = 0;
            // Display an error message or take any other action
        } else {
            ok++;
            nom.setStyle("");
            nom_wrong.setText("");
        }

        //controle prenom
        if (p.isEmpty()) {
            prenom.setStyle("-fx-border-color: red;");
            prenom_wrong.setText("Insérez votre prenom ici!!");
            ok = 0;
        } else if (!p.matches("^[a-zA-Z]*$")) {
            prenom.setStyle("-fx-border-color: red;");
            prenom_wrong.setText("Le prenom doit contenir des lettres alphabétiques!!");
            ok = 0;
            // Display an error message or take any other action
        } else {
            ok++;
            prenom.setStyle("");
            prenom_wrong.setText("");
        }
        //controle username
        if (u.isEmpty()) {
            uname.setStyle("-fx-border-color: red;");
            uname_wrong.setText("Insérez votre username ici!!");
            ok = 0;
        } else if (!u.matches("^[a-zA-Z0-9]*$")) {
            uname.setStyle("-fx-border-color: red;");
            uname_wrong.setText("Le username doit contenir des lettres alphabétiques!!");
            ok = 0;
            // Display an error message or take any other action
        } else if (sut.isUsernameTaken(u)) {
            uname.setStyle("-fx-border-color: red;");
            uname_wrong.setText("Le nom d'utilisateur est pris!!");
            ok = 0;
        } else {
            ok++;
            uname.setStyle("");
            uname_wrong.setText("");
        }
        //controle email
        if (e.isEmpty()) {
            email.setStyle("-fx-border-color: red;");
            email_wrong.setText("Insérez votre e-mail ici!!");
            ok = 0;
        } else if (!e.matches(regexPattern)) {
            email.setStyle("-fx-border-color: red;");
            email_wrong.setText("E-mail non valide!!");
            ok = 0;
            // Display an error message or take any other action
        } else if (sut.isEmailTaken(e)) {
            email.setStyle("-fx-border-color: red;");
            email_wrong.setText("Le e-mail est pris!!");
            ok = 0;
        }
        else
        {
            ok++;
            email.setStyle("");
            email_wrong.setText("");
        }
        //controle tel
        if (t.isEmpty()) {
            tel.setStyle("-fx-border-color: red;");
            tel_wrong.setText("Insérez votre numéro de téléphone ici!!");
            ok = 0;
        } else if (!t.matches("^[0-9]*$")) {
            tel.setStyle("-fx-border-color: red;");
            tel_wrong.setText("Numéro de téléphone non valide!!");
            ok = 0;
            // Display an error message or take any other action
        } else {
            ok++;
            tel.setStyle("");
            tel_wrong.setText("");
        }
        //mot de passe
        if (m.isEmpty()) {
            mdp.setStyle("-fx-border-color: red;");
            mdp_wrong.setText("Insérez votre mot de passe ici!!");
            ok = 0;
        } else {
            mdp.setStyle("");
            mdp_wrong.setText("");
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            String saltString = Base64.getEncoder().encodeToString(salt);
            String passwordAndSalt = m + saltString;

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(passwordAndSalt.getBytes());
            hashedPassword = Base64.getEncoder().encodeToString(hash);
            ok++;
        }
        //confirm
        if (cm.isEmpty()) {
            cmdp.setStyle("-fx-border-color: red;");
            cmdp_wrong.setText("Confirmer votre mot de passe ici!!");
            ok = 0;
        } else if (!cm.matches(m)) {
            cmdp.setStyle("-fx-border-color: red;");
            cmdp_wrong.setText("Vous devez faire correspondre le mot de passe!!");
            ok = 0;
        } else {
            cmdp.setStyle("");
            cmdp_wrong.setText("");
            ok++;
        }
        System.out.println(ok);
        if (ok == 8) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "User created", ButtonType.OK);
            User u1 = new User(nom.getText(), prenom.getText(), uname.getText(), email.getText(), tel.getText(), hashedPassword);
            ServiceUser su = new ServiceUser();
            su.ajouter(u1);
            a.show();
            
        }
    }

}
