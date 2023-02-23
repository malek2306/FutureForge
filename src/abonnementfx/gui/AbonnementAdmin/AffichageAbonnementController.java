/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementAdmin;

import abonnementFX.Entities.Abonnement;
import abonnementFX.Entities.OffreAbonnement;
import abonnementFX.Services.AbonnementService;
import abonnementFX.Services.OffreService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class AffichageAbonnementController implements Initializable {

    @FXML
    private TableView<Abonnement> tableAbonnement;
    @FXML
    private Pane paneOption;
    @FXML
    private ImageView imgEtudiant;
private static int id_Clicked = 0;
    /**
     * Initializes the controller class.
     */
        private AbonnementService as=new AbonnementService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Parent paneParent;
        try {
            paneParent = FXMLLoader.load(getClass().getResource("bareOption.fxml"));
            paneOption.getChildren().addAll(paneParent.getChildrenUnmodifiable());
        } catch (IOException ex) {
            Logger.getLogger(AffichageOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Abonnement> abonnements= as.getall_abonnements();
    
    TableColumn<Abonnement, String> nomCol = new TableColumn<>("Nom");
    nomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
    
    TableColumn<Abonnement, String> preCol = new TableColumn<>("Prenom");
    preCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));

    TableColumn<Abonnement, String> emailCol = new TableColumn<>("Email");
    emailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
    
    TableColumn<Abonnement, String> idenCol = new TableColumn<>("Identifiant ");
    idenCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentifiant()));
    
    TableColumn<Abonnement, String> cinCol = new TableColumn<>("CIN");
    cinCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCin()));
    
    TableColumn<Abonnement, String> typeCol = new TableColumn<>("Type");
    typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));
    
    TableColumn<Abonnement, String> dateDCol = new TableColumn<>("Date Debut");
    dateDCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateDebut().toString()));
    
    TableColumn<Abonnement, String> dateFCol = new TableColumn<>("Date Fin");
    dateFCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateFin().toString()));
    
    TableColumn<Abonnement, String> prixCol = new TableColumn<>("Prix Payer");
    prixCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrix())));
    
    TableColumn<Abonnement, String> idOffreCol = new TableColumn<>("Id offre");
    idOffreCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId_offre())));
    tableAbonnement.getColumns().addAll(nomCol, preCol, emailCol, idenCol,cinCol,typeCol,dateDCol,dateFCol,prixCol,idOffreCol);
    tableAbonnement.setItems(FXCollections.observableList(abonnements));
    }    

    @FXML
    private void deleteAbonnement(ActionEvent event) {
        as.annuler_abonnement(id_Clicked);
        List<Abonnement> allAbonnements = tableAbonnement.getItems();

// Find the Offre object with the specified ID and update it
        allAbonnements.stream()
            .filter(o -> o.getId_ab()== id_Clicked)
            .findFirst()
            .ifPresent(o -> {
                allAbonnements.remove(o);
            });
        tableAbonnement.setItems(FXCollections.observableList(allAbonnements));
    }

    @FXML
    private void afficherImgEtudent(MouseEvent event) throws FileNotFoundException {
        Abonnement selectedItem = tableAbonnement.getSelectionModel().getSelectedItem();
        System.out.println("0");
        id_Clicked =selectedItem.getId_ab();
                System.out.println("1");

        InputStream stream=null;
        if (selectedItem.getImg()!=""){
            stream = new FileInputStream(selectedItem.getImg());
            Image image = new Image(stream);
            imgEtudiant.setImage(image);
        }
        else 
            imgEtudiant.setImage(null);    
                    System.out.println("2");
                
        
    }

}
