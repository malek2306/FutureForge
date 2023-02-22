package com.thnity;

import com.thnity.entities.Etudiant;
import com.thnity.utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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
                300
        );
    }

    public void loadFront() {
        loadScene(
                Constants.FXML_MAIN_WINDOW,
                "",
                900
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

            URL destination = getClass().getResource(fxmlLink);

            if (destination != null) {
                Scene scene = new Scene(FXMLLoader.load(destination));
                scene.setFill(Color.TRANSPARENT);

                primaryStage.setTitle(title);
                primaryStage.setWidth(width);
                primaryStage.setHeight(700);
                primaryStage.setMinWidth(width);
                primaryStage.setMinHeight(700);
                primaryStage.setScene(scene);
                primaryStage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - (width / 2.0));
                primaryStage.setY((Screen.getPrimary().getBounds().getHeight() / 2) - (700 / 2.0));
                primaryStage.show();
            } else {
                System.out.println("Destination (" + fxmlLink + ") is null");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
