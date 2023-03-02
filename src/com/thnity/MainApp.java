import entities.Etudiant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utils.Constants;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    public static Stage mainStage;
    private static MainApp instance;
    private static Etudiant session;

    public static void main(String[] args) {
        launch(args);
    }

    public static MainApp getInstance() {
        if (instance == null) {
            instance = new MainApp();
        }
        return instance;
    }

    public static Etudiant getSession() {
        return session;
    }

    public static void setSession(Etudiant session) {
        MainApp.session = session;
    }

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        loadLogin();
    }

    public void loadLogin() {
        loadScene(
                Constants.FXML_LOGIN,
                "Connexion",
                500
        );
    }

    public void loadFront() {
        loadScene(
                Constants.FXML_FRONT_MAIN_WINDOW,
                "",
                800
        );
    }

    public void login(Etudiant etudiant) {
        MainApp.setSession(etudiant);
        loadFront();
    }

    public void logout() {
        session = null;

        System.out.println("Deconnexion ..");
        loadLogin();
    }

    private void loadScene(String fxmlLink, String title, int width) {
        try {
            Stage primaryStage = mainStage;
            primaryStage.close();

            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlLink))));
            scene.setFill(Color.TRANSPARENT);

            primaryStage.setTitle(title);
            primaryStage.setWidth(width);
            primaryStage.setHeight(600);
            primaryStage.setMinWidth(width);
            primaryStage.setMinHeight(600);
            primaryStage.setScene(scene);
            primaryStage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - (width / 2.0));
            primaryStage.setY((Screen.getPrimary().getBounds().getHeight() / 2) - (600 / 2.0));

            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
