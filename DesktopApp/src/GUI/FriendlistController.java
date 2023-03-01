/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Name
 */
public class FriendlistController implements Initializable {

    @FXML
    private ListView<?> friend_list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceUser sp = new ServiceUser();
            List arr = sp.getAll();
            ObservableList<User> UserList = FXCollections.observableArrayList(arr);
            for (User user : UserList) {
                list.getItems().add(user.getUsername());
            }
    }    
    
}
