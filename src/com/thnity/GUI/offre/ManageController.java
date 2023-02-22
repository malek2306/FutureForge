package com.thnity.GUI.offre;

import com.thnity.GUI.MainWindowController;
import com.thnity.MainApp;
import com.thnity.entities.Offre;
import com.thnity.services.OffreService;
import com.thnity.utils.AlertUtils;
import com.thnity.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    @FXML
    public ImageView imageIV;
    @FXML
    public TextField prenomChauffTF;
    @FXML
    public TextField numChauffTF;
    @FXML
    public DatePicker dateOffreDP;
    @FXML
    public TextField heureTF;
    @FXML
    public TextField prixOffreTF;
    @FXML
    public TextField departTF;
    @FXML
    public TextField destinationTF;
    @FXML
    public TextField placesDispoTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Offre currentOffre;
    Path selectedImagePath;
    boolean imageEdited;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        currentOffre = ShowAllController.currentOffre;

        if (currentOffre != null) {
            topText.setText("Modifier offre");
            btnAjout.setText("Modifier");

            try {
                selectedImagePath = FileSystems.getDefault().getPath(currentOffre.getImageVehicule());
                if (selectedImagePath.toFile().exists()) {
                    imageIV.setImage(new Image(selectedImagePath.toUri().toString()));
                }
                prenomChauffTF.setText(currentOffre.getPrenomChauff());
                numChauffTF.setText(currentOffre.getNumChauff());
                dateOffreDP.setValue(currentOffre.getDateOffre());
                heureTF.setText(currentOffre.getHeure());
                prixOffreTF.setText(String.valueOf(currentOffre.getPrixOffre()));
                departTF.setText(currentOffre.getDepart());
                destinationTF.setText(currentOffre.getDestination());
                placesDispoTF.setText(String.valueOf(currentOffre.getPlacesDispo()));

            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter offre");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {

            String imagePath;
            if (imageEdited) {
                imagePath = currentOffre.getImageVehicule();
            } else {
                createImageFile();
                imagePath = selectedImagePath.toString();
            }

            Offre offre = new Offre(
                    MainApp.getSession().getId(),
                    imagePath,
                    prenomChauffTF.getText(),
                    numChauffTF.getText(),
                    dateOffreDP.getValue(),
                    heureTF.getText(),
                    Integer.parseInt(prixOffreTF.getText()),
                    departTF.getText(),
                    destinationTF.getText(),
                    Integer.parseInt(placesDispoTF.getText())
            );

            if (currentOffre == null) {
                if (OffreService.getInstance().add(offre)) {
                    AlertUtils.makeInformation("Offre ajouté avec succés");
                    MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_OFFRE);
                } else {
                    AlertUtils.makeError("Erreur d'ajout");
                }
            } else {
                offre.setId(currentOffre.getId());
                if (OffreService.getInstance().edit(offre)) {
                    AlertUtils.makeInformation("Offre modifié avec succés");
                    ShowAllController.currentOffre = null;
                    MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_OFFRE);
                } else {
                    AlertUtils.makeError("Erreur de modification");
                }
            }

            if (selectedImagePath != null) {
                createImageFile();
            }
        }
    }

    @FXML
    public void chooseImage(ActionEvent actionEvent) {

        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(MainApp.mainStage);
        if (file != null) {
            selectedImagePath = Paths.get(file.getPath());
            imageIV.setImage(new Image(file.toURI().toString()));
        }
    }

    public void createImageFile() {
        try {
            Path newPath = FileSystems.getDefault().getPath("src/com/thnity/images/uploads/" + selectedImagePath.getFileName());
            Files.copy(selectedImagePath, newPath, StandardCopyOption.REPLACE_EXISTING);
            selectedImagePath = newPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean controleDeSaisie() {

        if (selectedImagePath == null) {
            AlertUtils.makeInformation("Veuillez choisir une image");
            return false;
        }

        if (prenomChauffTF.getText().isEmpty()) {
            AlertUtils.makeInformation("prenomChauff ne doit pas etre vide");
            return false;
        }

        if (numChauffTF.getText().isEmpty()) {
            AlertUtils.makeInformation("numChauff ne doit pas etre vide");
            return false;
        }

        try {
            Integer.parseInt(numChauffTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("numChauff doit etre un nombre");
            return false;
        }

        if (numChauffTF.getText().length() != 8){
            AlertUtils.makeInformation("numChauff doit etre un nombre de 8 chiffres");
            return false;
        }

        if (dateOffreDP.getValue() == null) {
            AlertUtils.makeInformation("Choisir une date pour dateOffre");
            return false;
        }

        if (heureTF.getText().isEmpty()) {
            AlertUtils.makeInformation("heure ne doit pas etre vide");
            return false;
        }

        if (prixOffreTF.getText().isEmpty()) {
            AlertUtils.makeInformation("prixOffre ne doit pas etre vide");
            return false;
        }

        try {
            Integer.parseInt(prixOffreTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("prixOffre doit etre un nombre");
            return false;
        }

        if (departTF.getText().isEmpty()) {
            AlertUtils.makeInformation("depart ne doit pas etre vide");
            return false;
        }

        if (destinationTF.getText().isEmpty()) {
            AlertUtils.makeInformation("destination ne doit pas etre vide");
            return false;
        }

        if (placesDispoTF.getText().isEmpty()) {
            AlertUtils.makeInformation("placesDispo ne doit pas etre vide");
            return false;
        }

        try {
            Integer.parseInt(placesDispoTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("placesDispo doit etre un nombre");
            return false;
        }

        return true;
    }
}