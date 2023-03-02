package com.thnityzz.gui.front.offre;

import com.thnityzz.gui.front.MainWindowController;
import com.thnityzz.entities.Offre;
import com.thnityzz.services.OffreService;
import com.thnityzz.utils.AlertUtils;
import com.thnityzz.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

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
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_OFFRE)));

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
            
            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierOffre(offre));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerOffre(offre));
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    
    @FXML
    private void ajouterOffre(ActionEvent event) {
        currentOffre = null;
        MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_MANAGE_OFFRE);
    }

    private void modifierOffre(Offre offre) {
        currentOffre = offre;
        MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_MANAGE_OFFRE);
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
                MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_DISPLAY_ALL_OFFRE);
            } else {
                AlertUtils.makeError("Could not delete offre");
            }
        }
    }
    
    
    private void specialAction(Offre offre) {
        
    }
}
