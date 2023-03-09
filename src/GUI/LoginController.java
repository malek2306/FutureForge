/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import util.Login;
import entities.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import service.ServiceUser;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Name
 */
public class LoginController implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private Button login;
    @FXML
    private PasswordField mdp;
    @FXML
    private Label worng_mdp;
    @FXML
    private Label wrong_user;
    @FXML
    private ImageView arrow;
    @FXML
    private ImageView arrow1;
    @FXML
    private CheckBox remember;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File myObj = new File("filename.txt");
        try {
            if (myObj.length() != 0) {
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    user.setText(data);
                    System.out.println(data);
                    remember.setSelected(true);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        arrow1.setVisible(false);
        // TODO
    }

    private void GoHome(ActionEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        Login login = Login.getInstance();
        ServiceUser su = new ServiceUser();
        User u = su.getOneByUsername(user.getText());
        if (user.getText().isEmpty()) {
            wrong_user.setText("Donner Username!!");
        } else if (mdp.getText().isEmpty()) {
            wrong_user.setText("Donner mot de passe!!");
        } else if (su.isUsernameTaken(user.getText())) {
            if (u.getMdp().equals(mdp.getText())) {
                if (!su.isBlocked(user.getText())) {
                    System.out.println(u.getRole());
                    if (u.getRole().equals("Admin")) {
                        login.setUsername(u.getUsername());
                        login.setPassword(u.getMdp());
                        login.setUserid(u.getId());
                        Parent homPage = FXMLLoader.load(getClass().getResource("admin.fxml"));
                        Scene homaepageScene = new Scene(homPage);
                        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        appStage.setScene(homaepageScene);
                        appStage.show();
                    } else if (u.getRole().equals("Etudiant") || u.getRole().equals("Etudiant_m")) {
                        login.setUsername(u.getUsername());
                        login.setPassword(u.getMdp());
                        login.setUserid(u.getId());
                        //file
                        if (remember.isSelected()) {
                            FileWriter myWriter = new FileWriter("filename.txt");
                            myWriter.write(login.getUsername());
                            myWriter.close();
                            System.out.println("Successfully wrote to the file.");
                        } else {
                            FileWriter myWriter = new FileWriter("filename.txt");
                            myWriter.write("");
                            myWriter.close();
                        }
                        //file
                        Parent homPage = FXMLLoader.load(getClass().getResource("profile.fxml"));
                        Scene homaepageScene = new Scene(homPage);
                        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        appStage.setScene(homaepageScene);
                        appStage.show();
                    }
                }
                else{wrong_user.setText("votre compte est bloqué, veuillez contacter un administrateur ou écrire un ticket pour faire appel");}
            }
            else{wrong_user.setText("mauvais mot de passe réessayer");}
        } else {
            wrong_user.setText("User nexiste pas!!");
        }
    }

    @FXML
    private void ArrowHover(MouseEvent event) {
        arrow1.setVisible(true);
        arrow.setVisible(false);
    }

    @FXML
    private void ArrowHoverLeave(MouseEvent event) {
        arrow1.setVisible(false);
        arrow.setVisible(true);
    }

    @FXML
    private void GoHomeLogin(MouseEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

    @FXML
    private void GoSignUp(ActionEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

    @FXML
    private void GoForgotPassword(ActionEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("forgotmdp.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }
}
