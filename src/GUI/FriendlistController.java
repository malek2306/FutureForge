/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Friend;
import entities.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.ServiceFriend;
import service.ServiceUser;
import util.Login;

/**
 * FXML Controller class
 *
 * @author Name
 */
public class FriendlistController implements Initializable {

    private ListView<Friend> listview;
    @FXML
    private ListView<String> friend_list = new ListView<String>();
    @FXML
    private TextField friend;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Login login = Login.getInstance();
        ServiceFriend sp = new ServiceFriend();
        List arr = sp.getAll(login.getUsername());
        ObservableList<Friend> FriendList = FXCollections.observableArrayList(arr);
        for (Friend l : FriendList) {
            System.out.println(l);

            if (!sp.wehomies(login.getUsername(), l.getUser1())) {
                friend_list.getItems().add(l.getUser1());
            }
        }
    }

    @FXML
    private void OnMouseClick(MouseEvent event) {
        ServiceFriend sf = new ServiceFriend();
        String un = friend_list.getSelectionModel().getSelectedItem();
        Friend f = sf.getOneByUsername2(un);
        friend.setText(f.getUser1());
        System.out.println(f);
    }

    @FXML
    private void AcceptInv(ActionEvent event) {
        ServiceFriend sf = new ServiceFriend();
        Login login = Login.getInstance();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ajouter" + friend.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        System.out.println("SESSION");
        System.out.println(login.getUsername());
        if (alert.getResult() == ButtonType.YES) {
            sf.accept(login.getUsername(), friend.getText());
            List arr = sf.getAll(login.getUsername());
        ObservableList<Friend> FriendList = FXCollections.observableArrayList(arr);
        friend_list.getItems().clear();
        for (Friend l : FriendList) {
            System.out.println(l);

            if (!sf.wehomies(login.getUsername(), l.getUser1())) {
                friend_list.getItems().add(l.getUser1());
            }
        }
        }
        friend_list.refresh();

    }

}
