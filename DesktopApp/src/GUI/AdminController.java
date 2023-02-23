/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.ServiceUser;
import util.Data;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Name
 */
public class AdminController implements Initializable {

    private ListView<User> listview;
    @FXML
    private ListView<String> list = new ListView<String>();
    ;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private PasswordField mdp;
    @FXML
    private TextField uname;
    @FXML
    private Button delete;

    //ObservableList<String> list = FXCollections.observableArrayList("s","ss");
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //listview.setItems(list);
        int id=0;
        ServiceUser sp = new ServiceUser();
        List arr = sp.getAll();
        ObservableList<User> UserList = FXCollections.observableArrayList(arr);
        for (User user : UserList) {
            list.getItems().add(user.getUsername());
        }
        /* element
        System.out.println("Element  " + u.getNom());
        System.out.println("List: " + arr);
        System.out.println("observe: " + UserList);
        
        list.getItems().add(u.getNom());
        list.getItems().add("Second Item");
        list.getItems().add("Third Item");
        list.getItems().add("Fourth Item");
        list.getItems().add("Fifth Item");
         */
        // TODO
    }

    @FXML
    private void OnMouseClick(MouseEvent event) {
        ServiceUser sp = new ServiceUser();
        String un = list.getSelectionModel().getSelectedItem();
        User u = sp.getOneByUsername(un);
        int id=u.getId();
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        email.setText(u.getEmail());
        tel.setText(u.getTel());
        mdp.setText(u.getMdp());
        uname.setText(u.getUsername());
    }

    @FXML
    private void DeleteUser(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Delete user  " + uname.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            ServiceUser sp = new ServiceUser();
            sp.supprimerusername(uname.getText());
            list.refresh();
        }
    }

}
