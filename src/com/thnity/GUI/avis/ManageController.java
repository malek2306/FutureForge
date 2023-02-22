package com.thnity.GUI.avis;

import com.thnity.GUI.MainWindowController;
import com.thnity.MainApp;
import com.thnity.entities.Avis;
import com.thnity.services.AvisService;
import com.thnity.utils.AlertUtils;
import com.thnity.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    Avis currentAvis;
    int rating = 0;

    @FXML
    private Button star1;
    @FXML
    private Button star2;
    @FXML
    private Button star3;
    @FXML
    private Button star4;
    @FXML
    private Button star5;
    @FXML
    public TextField descriptionAvisTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentAvis = ShowController.currentAvis;

        if (currentAvis != null) {
            topText.setText("Modifier avis");
            btnAjout.setText("Modifier");

            try {
                rating = currentAvis.getRate();
                setStars(rating);

                descriptionAvisTF.setText(currentAvis.getDescriptionAvis());
            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            setStars(0);

            topText.setText("Ajouter avis");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {

            Avis avis = new Avis(
                    ShowController.selectedOffre.getId(),
                    MainApp.getSession().getId(),
                    rating,
                    descriptionAvisTF.getText()
            );

            if (currentAvis == null) {
                if (AvisService.getInstance().add(avis)) {
                    AlertUtils.makeInformation("Avis ajouté avec succés");
                    MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_AVIS);
                } else {
                    AlertUtils.makeError("Erreur d'ajout");
                }
            } else {
                avis.setId(currentAvis.getId());
                if (AvisService.getInstance().edit(avis)) {
                    AlertUtils.makeInformation("Avis modifié avec succés");
                    ShowController.currentAvis = null;
                    MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_AVIS);
                } else {
                    AlertUtils.makeError("Erreur de modification");
                }
            }

        }
    }

    @FXML
    private void setStar1(ActionEvent event) {
        setStars(1);
    }

    @FXML
    private void setStar2(ActionEvent event) {
        setStars(2);
    }

    @FXML
    private void setStar3(ActionEvent event) {
        setStars(3);
    }

    @FXML
    private void setStar4(ActionEvent event) {
        setStars(4);
    }

    @FXML
    private void setStar5(ActionEvent event) {
        setStars(5);
    }

    private void setStars(int nbEtoilesLocal) {
        rating = nbEtoilesLocal;
        Button[] stars = {star1, star2, star3, star4, star5};
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                stars[i].setGraphic(new ImageView("com/thnity/images/mdi/star.png"));
            } else {
                stars[i].setGraphic(new ImageView("com/thnity/images/mdi/star-outline.png"));
            }
        }
    }

    private boolean controleDeSaisie() {

        if (rating == 0) {
            AlertUtils.makeInformation("Donner une note");
            return false;
        }

        if (descriptionAvisTF.getText().isEmpty()) {
            AlertUtils.makeInformation("Description ne doit pas etre vide");
            return false;
        }

        return true;
    }
}