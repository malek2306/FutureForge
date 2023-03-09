/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Friend;
import entities.User;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ServiceFriend;
import service.ServiceUser;
import util.Login;
import javafx.scene.shape.Circle;

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
    private Label uname;
    @FXML
    private Label role;
    @FXML
    private Label not_nb;
    @FXML
    private ImageView friend_list;
    @FXML
    private ImageView friend_list1;
    @FXML
    private ListView<String> is_friend = new ListView<String>();
    @FXML
    private TextField friendtodelete;
    @FXML
    private AnchorPane slider;
    @FXML
    private ImageView Menu;
    @FXML
    private ImageView MenuClose;
    @FXML
    private ImageView user_pfp;
    @FXML
    private ImageView session_user_pfp;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private AnchorPane friendpanel;
    @FXML
    private ImageView welcome_pfp;
    @FXML
    private Label welcome_name;
    private AnchorPane welcome;
    @FXML
    private ImageView Friend_pfp;
    @FXML
    private Label friend_role;
    @FXML
    private Label friend_name;
    @FXML
    private Label friend_prenom;
    private Label test;
    @FXML
    private Label session_user;
    @FXML
    private Label session_user1;
    @FXML
    private Label user1;
    @FXML
    private ImageView user_add_pfp;
    @FXML
    private AnchorPane background;
    @FXML
    private AnchorPane panel;
    @FXML
    private AnchorPane panel1;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label5;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private AnchorPane panetest;
    @FXML
    private ImageView darkmode;
    @FXML
    private ImageView lightmode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panel1.setVisible(false);
        panel.setVisible(false);
        lightmode.setVisible(false);
        //slide
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        }
        );
        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-176);
            slide.play();
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        }
        );
        //slide
        ServiceUser su = new ServiceUser();
        Login login = Login.getInstance();
        user.setText(login.getUsername());
        session_user.setText(login.getUsername());
        session_user1.setText(login.getUsername());
        welcome_name.setText(login.getUsername());
        try {
            su.displayUserImage(login.getUserid(), session_user_pfp);
            Circle clip = new Circle();
            clip.setCenterX(session_user_pfp.getFitWidth() / 2);
            clip.setCenterY(session_user_pfp.getFitHeight() / 2);
            clip.setRadius(Math.min(session_user_pfp.getFitWidth(), session_user_pfp.getFitHeight()) / 2);
            session_user_pfp.setClip(clip);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //friend list
        ServiceFriend sp = new ServiceFriend();
        List arr = sp.IsFriend(login.getUsername());
        ObservableList<Friend> FriendList = FXCollections.observableArrayList(arr);
        for (Friend friend : FriendList) {
            if (friend.getUser1().equals(login.getUsername())) {
                is_friend.getItems().add(friend.getUser2());
            } else {
                is_friend.getItems().add(friend.getUser1());
            }

        }
        User u = su.getOneByUsername(login.getUsername());
        System.out.println("sadasdas" + login.getUsername());

        System.out.println(u.getPrenom());
        setting1.setVisible(false);
        ServiceFriend sf = new ServiceFriend();
        String s = String.valueOf(sf.notification(login.getUsername()));
        sf.notification(login.getUsername());
        not_nb.setText(s);
        //panels
        friendpanel.setVisible(true);
        try {
            su.displayUserImage(login.getUserid(), welcome_pfp);
            welcome_name.setText(login.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        Login login = Login.getInstance();
        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("");
        myWriter.close();
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

    private void FindUser(ActionEvent event) throws SQLException, IOException {
        Login login = Login.getInstance();
        String r = recherche.getText();
        ServiceUser su = new ServiceUser();
        User u = su.getOneByUsername(r);
        ServiceFriend sf = new ServiceFriend();
        /*
        
        List f = sf.IsFriend(u.getUsername());*/
        if (r.isEmpty()) {
            System.out.println("relax");
            recherche.setStyle("-fx-border-color: red;");
            uname.setText("Insérez username!!");
            role.setText("...");
        } else if (u == null) {
            recherche.setStyle("-fx-border-color: red;");
            uname.setText("l'utilisateur n'existe pas!!");
            role.setText("...");
        } else if (u.getUsername().equals(login.getUsername())) {
            recherche.setStyle("-fx-border-color: red;");
            uname.setText("pas d'amis?!!");
            role.setText("...");
        } else if (sf.wehomies(login.getUsername(), r)) {
            recherche.setStyle("-fx-border-color: red;");
            uname.setText("Déjà amis!!");
            role.setText("...");
        } else {
            recherche.setStyle("");
            uname.setText(u.getUsername());
            role.setText(u.getRole());
            su.displayUserImage(u.getId(), Friend_pfp);
            su.displayUserImage(u.getId(), user_add_pfp);
        }
    }

    @FXML
    private void AddFriend(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Friend added", ButtonType.OK);

        ServiceFriend sf = new ServiceFriend();
        if (!sf.wehomies(user.getText(), uname.getText())) {
            Friend f = new Friend(user.getText(), uname.getText(), 0);
            sf.ajouter(f);
            a.show();
            System.out.println("frend");
        } else {
            System.out.println("Deja amis 2");
        }

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("friendlist.fxml"));
        Parent root = loader.load();
        Stage modalStage = new Stage();
        modalStage.setTitle("Modal Window");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        Scene modalScene = new Scene(root);
        modalStage.setScene(modalScene);
        modalStage.showAndWait();
    }

    @FXML
    private void DeleteFriendList(MouseEvent event) throws SQLException, IOException {
        ServiceFriend sf = new ServiceFriend();
        ServiceUser su = new ServiceUser();
        String un = is_friend.getSelectionModel().getSelectedItem();
        friendtodelete.setText(un);
        User fr = su.getOneByUsername(un);
        friend_name.setText(fr.getNom());
        friend_prenom.setText(fr.getPrenom());
        friend_role.setText(fr.getRole());
        su.displayUserImage(fr.getId(), Friend_pfp);
    }

    @FXML
    private void DeleteFriendButton(ActionEvent event) {
        Login login = Login.getInstance();
        ServiceFriend sf = new ServiceFriend();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "tu es sûr de vouloir supprimer " + friendtodelete.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            sf.supprimer(login.getUsername(), friendtodelete.getText());
            List<Friend> friendList = sf.IsFriend(login.getUsername());
            ObservableList<Friend> updatedFriendList = FXCollections.observableArrayList(friendList);
            is_friend.getItems().clear();
            for (Friend friend : updatedFriendList) {
                if (friend.getUser1().equals(login.getUsername())) {
                    is_friend.getItems().add(friend.getUser2());
                } else {
                    is_friend.getItems().add(friend.getUser1());
                }
            }

        }

    }

    @FXML
    private void GoSetting(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("setting.fxml"));
        Parent root = loader.load();
        Stage modalStage = new Stage();
        modalStage.setTitle("Modal Window");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        Scene modalScene = new Scene(root);
        modalStage.setScene(modalScene);
        modalStage.showAndWait();
    }

    @FXML
    private void SearchMouseClick(MouseEvent event) throws SQLException, IOException {
        Login login = Login.getInstance();
        String r = recherche.getText();
        ServiceUser su = new ServiceUser();
        User u = su.getOneByUsername(r);
        ServiceFriend sf = new ServiceFriend();
        /*
        
        List f = sf.IsFriend(u.getUsername());*/
        if (r.isEmpty()) {
            System.out.println("relax");
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
        } else if (sf.wehomies(login.getUsername(), r)) {
            uname.setText("Déjà amis!!");
            role.setText("...");
        } else {
            recherche.setStyle("");
            uname.setText(u.getUsername());
            role.setText(u.getRole());
            Image image = new Image(u.getPfp());
            int userId = u.getId();
            su.displayUserImage(userId, user_pfp);
            Circle clip = new Circle();
            clip.setCenterX(user_pfp.getFitWidth() / 2);
            clip.setCenterY(user_pfp.getFitHeight() / 2);
            clip.setRadius(Math.min(user_pfp.getFitWidth(), user_pfp.getFitHeight()) / 2);
            user_pfp.setClip(clip);

        }
    }

    @FXML
    private void GoHome(ActionEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

    private void FriendList(ActionEvent event) {
        friendpanel.setVisible(true);
        welcome.setVisible(false);
    }

    @FXML
    private void GoOffre(ActionEvent event) throws IOException {

    }

    @FXML
    private void GoDarkMode(MouseEvent event) {
        lightmode.setVisible(true);
        darkmode.setVisible(false);
        background.setStyle("-fx-background-color: #0A2647");
        panetest.setStyle("-fx-background-color: #0A2647");
        slider.setStyle("-fx-background-color: #144272");
        recherche.setStyle("-fx-text-color: #205295");
        recherche.setStyle("-fx-background-color: #2C74B3");
        uname.setTextFill(Color.web("#2C74B3"));
        role.setTextFill(Color.web("#2C74B3"));
        friend_name.setTextFill(Color.web("#2C74B3"));
        friend_prenom.setTextFill(Color.web("#2C74B3"));
        friend_role.setTextFill(Color.web("#2C74B3"));
        session_user.setTextFill(Color.web("#2C74B3"));
        session_user1.setTextFill(Color.web("#2C74B3"));
        label.setTextFill(Color.web("#2C74B3"));
        label1.setTextFill(Color.web("#2C74B3"));
        label2.setTextFill(Color.web("#2C74B3"));
        label3.setTextFill(Color.web("#2C74B3"));
        label4.setTextFill(Color.web("#2C74B3"));
        label5.setTextFill(Color.web("#2C74B3"));
        is_friend.setStyle("-fx-background-color: #2C74B3");
        is_friend.setStyle("-fx-text-color: #205295");
        session_user1.setStyle("-fx-text-color: white");

    }

    @FXML
    private void GoToAddFriend(MouseEvent event) {
        panel.setVisible(true);
        panel1.setVisible(false);
    }

    @FXML
    private void GoToFriendList(MouseEvent event) {
        panel.setVisible(false);
        panel1.setVisible(true);
    }

    @FXML
    private void GoLightMode(MouseEvent event) {
        lightmode.setVisible(false);
        darkmode.setVisible(true);
        background.setStyle("");
        slider.setStyle("");
        recherche.setStyle("");
        recherche.setStyle("");
        panetest.setStyle("");
        uname.setTextFill(Color.web("#000000"));
        role.setTextFill(Color.web("#000000"));
        friend_name.setTextFill(Color.web(""));
        friend_prenom.setTextFill(Color.web("#000000"));
        friend_role.setTextFill(Color.web("#000000"));
        session_user.setTextFill(Color.web("#000000"));
        label.setTextFill(Color.web("#000000"));
        label1.setTextFill(Color.web("#000000"));
        label2.setTextFill(Color.web("#000000"));
        label3.setTextFill(Color.web("#000000"));
        label4.setTextFill(Color.web("#000000"));
        label5.setTextFill(Color.web("#000000"));
        is_friend.setStyle("");
        is_friend.setStyle("");
        session_user1.setStyle("");
    }

}
