/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import util.Login;
import entities.User;
import service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private Button gohome;
    @FXML
    private Button login;
    @FXML
    private PasswordField mdp;
    @FXML
    private Label worng_mdp;
    @FXML
    private Label wrong_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
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
                System.out.println(u.getRole());
                if (u.getRole().equals("Admin")) {
                    login.setUsername(u.getUsername());
                    login.setPassword(u.getMdp());
                    System.out.println(login.getPassword());
                    System.out.println(login.getUsername());
                    Parent homPage = FXMLLoader.load(getClass().getResource("admin.fxml"));
                    Scene homaepageScene = new Scene(homPage);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(homaepageScene);
                    appStage.show();
                }
            } else {
                wrong_user.setText("User nexiste pas!!");
            }
        }
    }
}
