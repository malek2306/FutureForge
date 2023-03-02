/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementAdmin;

import abonnementFX.Entities.OffreAbonnement;
import abonnementFX.Services.OffreService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HistoriqueOffreController implements Initializable {

    @FXML
    private TableView<OffreAbonnement> tableOffre;
private OffreService os=new OffreService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    
}
