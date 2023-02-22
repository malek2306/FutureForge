package com.thnity.GUI;

import com.thnity.MainApp;
import com.thnity.entities.Etudiant;
import com.thnity.services.EtudiantService;
import com.thnity.utils.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public TextField inputUsername;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inputUsername.setText("test");
    }

    public void login(ActionEvent actionEvent) {
        Etudiant etudiant = EtudiantService.getInstance().login(inputUsername.getText());
        if (etudiant != null) {
            MainApp.getInstance().login(etudiant);
        } else {
            AlertUtils.makeError("Etudiant n'existe pas");
        }
    }
}
