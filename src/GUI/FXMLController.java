package GUI;

import entities.User;
import java.io.IOException;
import service.ServiceUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @ayachi zakaria
 */
public class FXMLController implements Initializable {

    @FXML
    private Button admin;
    @FXML
    private ImageView car;

    @FXML
    private AnchorPane scene;

    private static final double CAR_STEP = 20.0;
    @FXML
    private AnchorPane og;
    @FXML
    private Label message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        og.setOnKeyPressed(event -> {
            double currentX = car.getLayoutX();
            double currentY = car.getLayoutY();
            switch (event.getCode()) {
                case LEFT:
                    car.setLayoutX(currentX - CAR_STEP);
                    break;
                case RIGHT:
                    car.setLayoutX(currentX + CAR_STEP);
                    System.out.println(currentX);
                    break;
                default:
                    break;
            }
            if (currentX == 554.0) {
                try {
                    Parent homPage = FXMLLoader.load(getClass().getResource("login.fxml"));
                    Scene homaepageScene = new Scene(homPage);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(homaepageScene);
                    appStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (currentX < -150.0) {
                    message.setText("tu vas dans le mauvais sens");     
            }
            else 
                if (currentX > -150.0) {
                    message.setText("utilisez la voiture pour atteindre votre objectif");     
            }
        });
    }

    private void goToSignup(ActionEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

    @FXML
    private void goToAdmin(ActionEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

    @FXML
    private void GoNext(MouseEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

}
