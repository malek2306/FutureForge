package GUI;

import MainApp;
import entities.Etudiant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.EtudiantService;
import utils.AlertUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public TextField inputEmail;
    @FXML
    public PasswordField inputPassword;
    @FXML
    public Button btnSignup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    public void signUp(ActionEvent actionEvent) {
        MainApp.getInstance().loadSignup();
    }

    public void login(ActionEvent actionEvent) {
        Etudiant etudiant = EtudiantService.getInstance().checkUser(inputEmail.getText(), inputPassword.getText());

        if (etudiant != null) {
            MainApp.getInstance().login(etudiant);
        } else {
            AlertUtils.makeError("Identifiants invalides");
        }
    }

    @FXML
    public void backend(ActionEvent actionEvent) {
        MainApp.getInstance().loadBack();
    }
}
