/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Name
 */
public class AdminController implements Initializable {

    private ListView<User> listview;
    @FXML
    private ListView<String> list = new ListView<String>();
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    private PasswordField mdp;
    @FXML
    private TextField uname;
    @FXML
    private Button delete;
    @FXML
    private Button modify;
    @FXML
    private TextField nom1;
    private TextField id;
    @FXML
    private TextField prenom1;
    @FXML
    private TextField uname1;
    @FXML
    private TextField email1;
    @FXML
    private TextField tel1;
    @FXML
    private Button GoBack;
    @FXML
    private TextField role;
    @FXML
    private TextField role1;
    @FXML
    private Label nbr_users;
    @FXML
    private ImageView pfp;
    @FXML
    private Label nbr_motor;
    @FXML
    private TableView<User> table = new TableView<>();

    @FXML
    private TableColumn<User, String> nameColumn = new TableColumn<>("nom");
    @FXML
    private TableColumn<User, String> prenomColumn = new TableColumn<>("prenom");
    @FXML
    private TableColumn<User, String> usernameColumn = new TableColumn<>("username");
    @FXML
    private TableColumn<User, String> emailColumn = new TableColumn<>("email");
    @FXML
    private TableColumn<User, String> telColumn = new TableColumn<>("tel");
    @FXML
    private TableColumn<User, String> roleColumn = new TableColumn<>("role");
    @FXML
    private TableColumn<User, String> matColumn = new TableColumn<>("mat");
    @FXML
    private AnchorPane listpanel;
    @FXML
    private AnchorPane tablepanel;
    @FXML
    private ImageView tablev;
    @FXML
    private ImageView listv;
    @FXML
    private TextField searchbar;
    @FXML
    private TextField searchbar1;

    //ObservableList<String> list = FXCollections.observableArrayList("s","ss");
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablepanel.setVisible(false);
        try {
            int id = 0;
            ServiceUser sp = new ServiceUser();
            List arr = sp.getAll();
            ObservableList<User> UserList = FXCollections.observableArrayList(arr);
            for (User user : UserList) {
                list.getItems().add(user.getUsername());
            }
            searchbar.textProperty().addListener((observable, oldValue, newValue) -> {
                list.getItems().clear();
                for (User user : UserList) {
                    if (user.getUsername().toLowerCase().contains(newValue.toLowerCase())) {
                        list.getItems().add(user.getUsername());
                    }
                }
            });
            int n = sp.countEtudiantUsers();
            nbr_users.setText(Integer.toString(n));
            int nm = sp.countEtudiantmUsers();
            nbr_motor.setText(Integer.toString(nm));
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //table
        ServiceUser sp = new ServiceUser();
        List<User> userList = sp.getAll();
        ObservableList<User> observableList = FXCollections.observableList(userList);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        matColumn.setCellValueFactory(new PropertyValueFactory<>("mat"));
        table.getColumns().addAll(nameColumn, prenomColumn, usernameColumn, emailColumn, telColumn, roleColumn, matColumn);
        table.setItems(observableList);
    }

    @FXML
    private void OnMouseClick(MouseEvent event) throws SQLException, IOException {
        ServiceUser sp = new ServiceUser();
        String un = list.getSelectionModel().getSelectedItem();
        User u = sp.getOneByUsername(un);
        nom.setText(u.getNom());
        nom1.setText(u.getNom());

        prenom.setText(u.getPrenom());
        prenom1.setText(u.getPrenom());

        email.setText(u.getEmail());
        email1.setText(u.getEmail());

        tel.setText(u.getTel());
        tel1.setText(u.getTel());

        /*uname.setText(u.getUsername());
        uname1.setText(u.getUsername());*/
        role.setText(u.getRole());
        role1.setText(u.getRole());

        System.out.println(id);
        //pfp
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

    @FXML
    private void ModifyUser(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
        ServiceUser su = new ServiceUser();
        System.out.println(nom.getText());
        System.out.println(nom1.getText());
        if (!nom.getText().equals(nom1.getText())) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "tu es sûr de vouloir changer le nom de " + uname.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                User um = new User(Integer.parseInt(id.getText()), nom1.getText(), prenom.getText(), uname.getText(), email.getText(), tel.getText(), mdp.getText(), role.getText());
                System.out.println(um);
                su.modifier(um);
                a.show();
            }
        }
        if (!prenom.getText().equals(prenom1.getText())) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "tu es sûr de vouloir changer le prenom de " + uname.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                User um = new User(Integer.parseInt(id.getText()), nom.getText(), prenom1.getText(), uname.getText(), email.getText(), tel.getText(), mdp.getText(), role.getText());
                System.out.println(um);
                su.modifier(um);
                a.show();
            }
        }
        if (!email.getText().equals(email1.getText())) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "tu es sûr de vouloir changer l' email de " + uname.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                User um = new User(Integer.parseInt(id.getText()), nom.getText(), prenom.getText(), uname.getText(), email1.getText(), tel.getText(), mdp.getText(), role.getText());
                System.out.println(um);
                su.modifier(um);
                a.show();
            }
        }
        if (!tel.getText().equals(tel1.getText())) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "tu es sûr de vouloir changer le tel de " + uname.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                User um = new User(Integer.parseInt(id.getText()), nom.getText(), prenom.getText(), uname.getText(), email.getText(), tel1.getText(), mdp.getText(), role.getText());
                System.out.println(um);
                su.modifier(um);
                a.show();
            }
        }
        if (!uname.getText().equals(uname1.getText()) && su.isUsernameTaken(uname1.getText())) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "tu es sûr de vouloir changer le username de " + uname.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                User um = new User(Integer.parseInt(id.getText()), nom.getText(), prenom.getText(), uname1.getText(), email.getText(), tel.getText(), mdp.getText(), role.getText());
                System.out.println(um);
                su.modifier(um);
                a.show();
            }
        } else {
            uname.setStyle("-fx-border-color: red;");
        }
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
    private void ShowTable(MouseEvent event) {
        listpanel.setVisible(false);
        tablepanel.setVisible(true);
    }

    @FXML
    private void ShowList(MouseEvent event) {
        listpanel.setVisible(true);
        tablepanel.setVisible(false);
    }

}
