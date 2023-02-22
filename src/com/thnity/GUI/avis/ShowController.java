package com.thnity.GUI.avis;

import com.thnity.GUI.MainWindowController;
import com.thnity.MainApp;
import com.thnity.entities.Avis;
import com.thnity.entities.Offre;
import com.thnity.services.AvisService;
import com.thnity.utils.AlertUtils;
import com.thnity.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ShowController implements Initializable {

    public static Offre selectedOffre;
    public static Avis currentAvis;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;


    List<Avis> listAvis;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listAvis = AvisService.getInstance().getByOffre(selectedOffre.getId());

        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();

        Collections.reverse(listAvis);

        if (!listAvis.isEmpty()) {
            for (Avis avis : listAvis) {

                mainVBox.getChildren().add(makeAvisModel(avis));

            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeAvisModel(Avis avis) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_AVIS)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#descriptionAvisText")).setText(avis.getDescriptionAvis());

            for (int i = 1; i <= 5; i++) {
                ImageView star = ((ImageView) innerContainer.lookup("#star" + i));

                if (avis.getRate() >= i) {
                    star.setImage(new Image("com/thnity/images/mdi/star.png"));
                } else {
                    star.setImage(new Image("com/thnity/images/mdi/star-outline.png"));
                }
            }

            if (avis.getIdEtudiant() == MainApp.getSession().getId()) {
                ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierAvis(avis));
                ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerAvis(avis));
            } else {
                innerContainer.lookup("#editButton").setVisible(false);
                innerContainer.lookup("#deleteButton").setVisible(false);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterAvis(ActionEvent event) {
        currentAvis = null;
        MainWindowController.getInstance().loadInterface(Constants.FXML_MANAGE_AVIS);
    }

    private void modifierAvis(Avis avis) {
        currentAvis = avis;
        MainWindowController.getInstance().loadInterface(Constants.FXML_MANAGE_AVIS);
    }

    private void supprimerAvis(Avis avis) {
        currentAvis = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer avis ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (AvisService.getInstance().delete(avis.getId())) {
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_OFFRE);
            } else {
                AlertUtils.makeError("Could not delete avis");
            }
        }
    }


  /*  private void specialAction(Avis avis) {

    }*/
}
