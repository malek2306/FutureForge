package com.thnity.GUI.offre;

import com.thnity.GUI.MainWindowController;
import com.thnity.GUI.avis.ShowController;
import com.thnity.MainApp;
import com.thnity.entities.Offre;
import com.thnity.services.OffreService;
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
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class ShowAllController implements Initializable {

    public static Offre currentOffre;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;

    List<Offre> listOffre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listOffre = OffreService.getInstance().getAll();

        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();

        Collections.reverse(listOffre);

        if (!listOffre.isEmpty()) {
            for (Offre offre : listOffre) {

                mainVBox.getChildren().add(makeOffreModel(offre));

            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeOffreModel(
            Offre offre
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_OFFRE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));

            ((Text) innerContainer.lookup("#prenomChauffText")).setText("PrenomChauff : " + offre.getPrenomChauff());
            ((Text) innerContainer.lookup("#numChauffText")).setText("NumChauff : " + offre.getNumChauff());
            ((Text) innerContainer.lookup("#dateOffreText")).setText("DateOffre : " + offre.getDateOffre());
            ((Text) innerContainer.lookup("#heureText")).setText("Heure : " + offre.getHeure());
            ((Text) innerContainer.lookup("#prixOffreText")).setText("PrixOffre : " + offre.getPrixOffre());
            ((Text) innerContainer.lookup("#departText")).setText("Depart : " + offre.getDepart());
            ((Text) innerContainer.lookup("#destinationText")).setText("Destination : " + offre.getDestination());
            ((Text) innerContainer.lookup("#placesDispoText")).setText("PlacesDispo : " + offre.getPlacesDispo());
            Path selectedImagePath = FileSystems.getDefault().getPath(offre.getImageVehicule());
            if (selectedImagePath.toFile().exists()) {
                ((ImageView) innerContainer.lookup("#imageIV")).setImage(new Image(selectedImagePath.toUri().toString()));
            }

            if (offre.getIdEtudiant() == MainApp.getSession().getId()) {
                ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierOffre(offre));
                ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerOffre(offre));
            } else {
                innerContainer.lookup("#editButton").setVisible(false);
                innerContainer.lookup("#deleteButton").setVisible(false);
            }

            ((Button) innerContainer.lookup("#avisButton")).setOnAction((event) -> afficherAvis(offre));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterOffre(ActionEvent event) {
        currentOffre = null;
        MainWindowController.getInstance().loadInterface(Constants.FXML_MANAGE_OFFRE);
    }

    private void modifierOffre(Offre offre) {
        currentOffre = offre;
        MainWindowController.getInstance().loadInterface(Constants.FXML_MANAGE_OFFRE);
    }

    private void supprimerOffre(Offre offre) {
        currentOffre = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer offre ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (OffreService.getInstance().delete(offre.getId())) {
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_OFFRE);
            } else {
                AlertUtils.makeError("Could not delete offre");
            }
        }
    }

    private void afficherAvis(Offre offre) {
        ShowController.selectedOffre = offre;
        MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_AVIS);
    }
}
