/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Admin;
import entities.Etudiantm;
import entities.User;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.ServiceUser;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static sun.security.krb5.Confounder.bytes;

/**
 * FXML Controller class
 *
 * @author Name
 */
public class SignupController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private Button submit;
    @FXML
    private Label nom_wrong;
    @FXML
    private Label prenom_wrong;
    @FXML
    private Label email_wrong;
    @FXML
    private Label tel_wrong;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField cmdp;
    @FXML
    private TextField uname;
    @FXML
    private Label mdp_wrong;
    @FXML
    private Label uname_wrong;
    @FXML
    private Label cmdp_wrong;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Button GoBack;
    @FXML
    private TextField mat;
    @FXML
    private Label mat_wrong;
    @FXML
    private ImageView imageView;
    private Label Blob;
    private Label Blobres;
    private FileInputStream fis;
    private Connection connection;
    private InputStream respfp;
    @FXML
    private TextField pfp;

    File f = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mat.setVisible(false);

    }

    @FXML
    private void Pfp(ActionEvent event) throws IOException, SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(image);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    @FXML
    private void submit(ActionEvent event) throws NoSuchAlgorithmException, IOException, SQLException {
        //controle nom
        int ok = 1;
        String n = nom.getText();
        String p = prenom.getText();
        String u = uname.getText();
        String e = email.getText();
        String t = tel.getText();
        String m = mdp.getText();
        String cm = cmdp.getText();
        String role = "";
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        String hashedPassword = "";
        ServiceUser sut = new ServiceUser();
        //contorle nom
        if (n.isEmpty()) {
            nom.setStyle("-fx-border-color: red;");
            nom_wrong.setText("Insérez votre nom ici!!");
            ok = 0;
        } else if (!n.matches("^[a-zA-Z]*$")) {
            nom.setStyle("-fx-border-color: red;");
            nom_wrong.setText("Le nom doit contenir des lettres alphabétiques!!");
            ok = 0;
            // Display an error message or take any other action
        } else {
            ok++;
            nom.setStyle("");
            nom_wrong.setText("");
        }

        //controle prenom
        if (p.isEmpty()) {
            prenom.setStyle("-fx-border-color: red;");
            prenom_wrong.setText("Insérez votre prenom ici!!");
            ok = 0;
        } else if (!p.matches("^[a-zA-Z]*$")) {
            prenom.setStyle("-fx-border-color: red;");
            prenom_wrong.setText("Le prenom doit contenir des lettres alphabétiques!!");
            ok = 0;
            // Display an error message or take any other action
        } else {
            ok++;
            prenom.setStyle("");
            prenom_wrong.setText("");
        }
        //controle username
        if (u.isEmpty()) {
            uname.setStyle("-fx-border-color: red;");
            uname_wrong.setText("Insérez votre username ici!!");
            ok = 0;
        } else if (!u.matches("^[a-zA-Z0-9]*$")) {
            uname.setStyle("-fx-border-color: red;");
            uname_wrong.setText("Le username doit contenir des lettres alphabétiques!!");
            ok = 0;
            // Display an error message or take any other action
        } else if (sut.isUsernameTaken(u)) {
            uname.setStyle("-fx-border-color: red;");
            uname_wrong.setText("Le nom d'utilisateur est pris!!");
            ok = 0;
        } else {
            ok++;
            uname.setStyle("");
            uname_wrong.setText("");
        }
        //controle email
        if (e.isEmpty()) {
            email.setStyle("-fx-border-color: red;");
            email_wrong.setText("Insérez votre e-mail ici!!");
            ok = 0;
        } else if (!e.matches(regexPattern)) {
            email.setStyle("-fx-border-color: red;");
            email_wrong.setText("E-mail non valide!!");
            ok = 0;
            // Display an error message or take any other action
        } else if (sut.isEmailTaken(e)) {
            email.setStyle("-fx-border-color: red;");
            email_wrong.setText("Le e-mail est pris!!");
            ok = 0;
        } else {
            ok++;
            email.setStyle("");
            email_wrong.setText("");
        }
        //controle tel
        if (t.isEmpty()) {
            tel.setStyle("-fx-border-color: red;");
            tel_wrong.setText("Insérez votre numéro de téléphone ici!!");
            ok = 0;
        } else if (!t.matches("^[0-9]*$")) {
            tel.setStyle("-fx-border-color: red;");
            tel_wrong.setText("Numéro de téléphone non valide!!");
            ok = 0;
            // Display an error message or take any other action
        } else {
            ok++;
            tel.setStyle("");
            tel_wrong.setText("");
        }
        //mot de passe
        if (m.isEmpty()) {
            mdp.setStyle("-fx-border-color: red;");
            mdp_wrong.setText("Insérez votre mot de passe ici!!");
            ok = 0;
        } else {
            mdp.setStyle("");
            mdp_wrong.setText("");
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            String saltString = Base64.getEncoder().encodeToString(salt);
            String passwordAndSalt = m + saltString;

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(passwordAndSalt.getBytes());
            hashedPassword = Base64.getEncoder().encodeToString(hash);
            ok++;
        }
        //confirm
        if (cm.isEmpty()) {
            cmdp.setStyle("-fx-border-color: red;");
            cmdp_wrong.setText("Confirmer votre mot de passe ici!!");
            ok = 0;
        } else if (!cm.matches(m)) {
            cmdp.setStyle("-fx-border-color: red;");
            cmdp_wrong.setText("Vous devez faire correspondre le mot de passe!!");
            ok = 0;
        } else {
            cmdp.setStyle("");
            cmdp_wrong.setText("");
            ok++;
        }
        if (checkBox.isSelected()) {
            if (mat.getText().isEmpty()) {
                mat.setStyle("-fx-border-color: red;");
                mat_wrong.setText("Insérez matricule de votre voiture ici!!");
            }
        }
        System.out.println(ok);
        if (ok == 8) {
            //pfp
            javafx.scene.image.Image image = imageView.getImage();
            if (image != null) {
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    ImageIO.write(bufferedImage, "png", baos);
                    respfp = new ByteArrayInputStream(baos.toByteArray());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println(pfp.getText());
            System.out.println(respfp);
            if (checkBox.isSelected()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "User created", ButtonType.OK);
                Etudiantm u1 = new Etudiantm(nom.getText(), prenom.getText(), uname.getText(), email.getText(), tel.getText(), mdp.getText(), role, respfp, mat.getText());
                ServiceUser su = new ServiceUser();
                su.ajouter(u1);
                a.show();
                System.out.println("car");
            } else {
                System.out.println("KAKAKAKAK");
                Alert a = new Alert(Alert.AlertType.INFORMATION, "User created", ButtonType.OK);
                User u1 = new User(nom.getText(), prenom.getText(), uname.getText(), email.getText(), tel.getText(), hashedPassword, role, respfp, mat.getText());
                ServiceUser su = new ServiceUser();
                su.ajouter(u1);
                a.show();
                System.out.println("normal");
            }
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
    private void ShowMat(ActionEvent event) {

        if (checkBox.isSelected()) {
            mat.setVisible(true);
        } else {
            mat.setVisible(false);
        }

    }

    @FXML
    private void GoLogin(ActionEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

    @FXML
    private void GoBackHome(MouseEvent event) throws IOException {
        Parent homPage = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene homaepageScene = new Scene(homPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homaepageScene);
        appStage.show();
    }

}
