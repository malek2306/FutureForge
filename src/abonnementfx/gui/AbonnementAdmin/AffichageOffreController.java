/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementAdmin;

import abonnementFX.Entities.OffreAbonnement;
import abonnementFX.Entities.TypeAbonnement;
import abonnementFX.Services.AbonnementService;
import abonnementFX.Services.OffreService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class AffichageOffreController implements Initializable {

    @FXML
    private TableView<OffreAbonnement> tableOffre;
    @FXML
    private Pane paneOption;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField reduction;
    @FXML
    private ComboBox<String> type;
    @FXML
    private DatePicker dateD;
    @FXML
    private DatePicker dateF;
    private static int id_Clicked = 0;
    /**
     * Initializes the controller class.
     */
    private OffreService os=new OffreService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.getItems().add("MENSUEL");
                type.getItems().add("SEMESTRILLE");
                        type.getItems().add("ANNUELLE");
        Parent paneParent;
        try {
            paneParent = FXMLLoader.load(getClass().getResource("bareOption.fxml"));
            paneOption.getChildren().addAll(paneParent.getChildrenUnmodifiable());
        } catch (IOException ex) {
            Logger.getLogger(AffichageOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<OffreAbonnement> offres= os.allOffres();
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis()); 

    List<OffreAbonnement> offresDisponibles = offres.stream()
    .filter(offre -> today.after(offre.getDate_debut()) && today.before(offre.getDate_fin()))
    .collect(Collectors.toList());
    List<OffreAbonnement> offresExpirer = offres.stream()
    .filter(offre -> today.after(offre.getDate_fin()))
    .collect(Collectors.toList());
    offresDisponibles.addAll(offresExpirer);
    
    TableColumn<OffreAbonnement, String> nomCol = new TableColumn<>("Nom");
    nomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
    
    TableColumn<OffreAbonnement, String> descCol = new TableColumn<>("Description");
    descCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

    TableColumn<OffreAbonnement, String> redCol = new TableColumn<>("Reduction (%)");
    redCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getReduction())));
    
    TableColumn<OffreAbonnement, String> typeCol = new TableColumn<>("type ");
    typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));
    
    TableColumn<OffreAbonnement, String> dateDCol = new TableColumn<>("Date Debut");
    dateDCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate_debut().toString()));
    
    TableColumn<OffreAbonnement, String> dateFCol = new TableColumn<>("Date Fin");
    dateFCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate_fin().toString()));
    
    tableOffre.getColumns().addAll(nomCol, descCol, redCol, typeCol,dateDCol,dateFCol);
    tableOffre.setItems(FXCollections.observableList(offresDisponibles));
    }    

    @FXML
    private void getDetailOffer(MouseEvent event) {
         
         OffreAbonnement selectedItem = tableOffre.getSelectionModel().getSelectedItem();
         id_Clicked =selectedItem.getId_ofab();
         nom.setText(selectedItem.getNom());
         description.setText(selectedItem.getDescription());
         reduction.setText(String.valueOf(selectedItem.getReduction()));
         type.setValue(selectedItem.getType().toString());
         dateD.setValue(selectedItem.getDate_debut().toLocalDate());
         dateF.setValue(selectedItem.getDate_fin().toLocalDate());

    }

    @FXML
    private void modifierOffer(ActionEvent event) {
        System.out.println(id_Clicked);
        OffreAbonnement offreNew = new OffreAbonnement();
        OffreAbonnement offre = os.findOffre(id_Clicked);
        offreNew.setNom(nom.getText());
        offreNew.setDescription(description.getText());
        offreNew.setReduction(Double.parseDouble(reduction.getText()) );
        offreNew.setType(TypeAbonnement.valueOf( type.getValue()));
        offreNew.setDate_debut(java.sql.Date.valueOf(dateD.getValue()));
        offreNew.setDate_fin(java.sql.Date.valueOf(dateF.getValue()));
        os.modifier_offre(id_Clicked, offreNew);
        List<OffreAbonnement> allOffres = tableOffre.getItems();

// Find the Offre object with the specified ID and update it
        allOffres.stream()
            .filter(o -> o.getId_ofab()== id_Clicked)
            .findFirst()
            .ifPresent(o -> {
                int index = allOffres.indexOf(o);
                allOffres.set(index, offreNew);
            });
        tableOffre.setItems(FXCollections.observableList(allOffres));
    }

    @FXML
    private void deleteOffre(ActionEvent event) {
        os.annuler_offre(id_Clicked);
        List<OffreAbonnement> allOffres = tableOffre.getItems();

// Find the Offre object with the specified ID and update it
        allOffres.stream()
            .filter(o -> o.getId_ofab()== id_Clicked)
            .findFirst()
            .ifPresent(o -> {
                allOffres.remove(o);
            });
        tableOffre.setItems(FXCollections.observableList(allOffres));
    }

    @FXML
    private void addOffre(ActionEvent event) {
        OffreAbonnement offreNew = new OffreAbonnement();
        offreNew.setNom(nom.getText());
        offreNew.setDescription(description.getText());
        offreNew.setReduction(Double.parseDouble(reduction.getText()) );
        offreNew.setType(TypeAbonnement.valueOf( type.getValue()));
        offreNew.setDate_debut(java.sql.Date.valueOf(dateD.getValue()));
        offreNew.setDate_fin(java.sql.Date.valueOf(dateF.getValue()));
        os.ajouter_offre(offreNew);
        List<OffreAbonnement> allOffres = tableOffre.getItems();
        List<OffreAbonnement> updatedOffres = IntStream.range(0, allOffres.size() + 1)
    .mapToObj(i -> i == 0 ? offreNew : allOffres.get(i < 0 ? i : i - 1))
    .collect(Collectors.toList());
        tableOffre.setItems(FXCollections.observableList(updatedOffres));
    }

    
}
