/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Etudiantm;
import entities.Friend;
import entities.User;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import service.ServiceFriend;
import service.ServiceUser;
import util.Login;

/**
 * FXML Controller class
 *
 * @author Name
 */
public class ProfileController implements Initializable {

    @FXML
    private Label user;
    @FXML
    private Hyperlink logout;
    @FXML
    private ImageView setting;
    @FXML
    private ImageView setting1;
    @FXML
    private TextField recherche;
    @FXML
    private Button search;
    @FXML
    private Label uname;
    @FXML
    private Label role;
    @FXML
    private Button add;
    @FXML
    private Label not_nb;
    @FXML
    private ImageView friend_list;
    @FXML
    private ImageView friend_list1;
    @FXML
    private ListView<String> is_friend = new ListView<String>();
    @FXML
    private Button delete;
    @FXML
    private TextField friendtodelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceUser su = new ServiceUser();
        Login login = Login.getInstance();
        //friend list
        ServiceFriend sp = new ServiceFriend();
        List arr = sp.IsFriend(login.getUsername());
        ObservableList<Friend> FriendList = FXCollections.observableArrayList(arr);
        for (Friend friend : FriendList) {
            System.out.println(friend);
            if (friend.getUser1().equals(login.getUsername())) {
                is_friend.getItems().add(friend.getUser2());
            } else {
                is_friend.getItems().add(friend.getUser1());
            }

        }
        User u = su.getOneByUsername(login.getUsername());
        user.setText(login.getUsername());
        System.out.println(u.getPrenom());
        setting1.setVisible(false);
        //pfp

        // NOTIFICATION
        ServiceFriend sf = new ServiceFriend();
        String s = String.valueOf(sf.notification(login.getUsername()));
        sf.notification(login.getUsername());
        not_nb.setText(s);

    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        Login login = Login.getInstance();
        Parent homPage = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

    @FXML
    private void SettingHover(MouseEvent event) {
        setting1.setVisible(true);
        setting.setVisible(false);
    }

    @FXML
    private void SettingHoverOut(MouseEvent event) {
        setting1.setVisible(false);
        setting.setVisible(true);
    }

    @FXML
    private void FindUser(ActionEvent event) {
        String r = recherche.getText();
        ServiceUser su = new ServiceUser();
        ServiceFriend sf = new ServiceFriend();
        Login login = Login.getInstance();
        User u = su.getOneByUsername(r);
        List f = sf.IsFriend(u.getUsername());
        if (r.isEmpty()) {
            recherche.setStyle("-fx-border-color: red;");
            uname.setText("Insérez username!!");
            role.setText("...");
        } else if (u == null) {
            recherche.setStyle("-fx-border-color: red;");
            uname.setText("l'utilisateur n'existe pas!!");
            role.setText("...");
        } else if (u.getUsername().equals(login.getUsername())) {
            uname.setText("pas d'amis?!!");
            role.setText("...");
        } else 
        {
            recherche.setStyle("");
            uname.setText(u.getUsername());
            role.setText(u.getRole());
        }
    }

    @FXML
    private void AddFriend(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Friend added", ButtonType.OK);
        Friend f = new Friend(user.getText(), uname.getText(), 0);
        ServiceFriend sf = new ServiceFriend();
        sf.ajouter(f);
        a.show();
        System.out.println("frend");
    }

    @FXML
    private void FriendHover(MouseEvent event) {
        friend_list1.setVisible(true);
        friend_list.setVisible(false);
    }

    @FXML
    private void FriendHoverExit(MouseEvent event) {
        friend_list1.setVisible(false);
        friend_list.setVisible(true);
    }

    @FXML
    private void GoFriendList(MouseEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("friendlist.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

    @FXML
    private void DeleteFriendList(MouseEvent event) {
        ServiceFriend sf = new ServiceFriend();
        String un = is_friend.getSelectionModel().getSelectedItem();
        Friend f = sf.getOneByUsername(un);
        friendtodelete.setText(f.getUser1());
        System.out.println(f);
    }

    @FXML
    private void DeleteFriendButton(ActionEvent event) {
        ServiceFriend sf = new ServiceFriend();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "tu es sûr de vouloir supprimer " + friendtodelete.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            sf.supprimer(friendtodelete.getText());
        }
    }

}
