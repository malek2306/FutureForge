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
import abonnementfx.Util.Variables;
import static abonnementfx.Util.Variables.prixAnnuelle;
import static abonnementfx.Util.Variables.prixMensuel;
import static abonnementfx.Util.Variables.prixSemestrille;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichageOffreController implements Initializable {

    private TableView<OffreAbonnement> tableOffre;
    private TextField nom;
    private TextField description;
    private TextField reduction;
    private ComboBox<String> type;
    private DatePicker dateD;
    private DatePicker dateF;
    private static int id_Clicked = 0;
    /**
     * Initializes the controller class.
     */
    private OffreService os=new OffreService();
    @FXML
    private Pane pane1;
    @FXML
    private Text prix1;
    @FXML
    private Pane pane2;
    @FXML
    private Text prix2;
    @FXML
    private Pane pane3;
    @FXML
    private Text prix3;
    @FXML
    private Pane pane6;
    @FXML
    private Text prixR4;
    @FXML
    private Text remis4;
    @FXML
    private Text prixSR4;
    @FXML
    private Pane pane5;
    @FXML
    private Text prixR5;
    @FXML
    private Text remis5;
    @FXML
    private Text prixSR5;
    @FXML
    private Pane pane4;
    @FXML
    private Text prixR6;
    @FXML
    private Text remis6;
    @FXML
    private Text prixSR6;
    @FXML
    private Text desc4;
    @FXML
    private Text desc5;
    @FXML
    private Text desc6;
    @FXML
    private Button addbtn1;
    @FXML
    private Button addbtn2;
    @FXML
    private Button addbtn3;
    @FXML
    private Button editbtn4;
    @FXML
    private Button deletebtn4;
    @FXML
    private Button editbtn5;
    @FXML
    private Button deletebtn5;
    @FXML
    private Button editbtn6;
    @FXML
    private Button deletebtn6;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*type.getItems().add("MENSUEL");
                type.getItems().add("SEMESTRILLE");
                        type.getItems().add("ANNUELLE");
        Parent paneParent;
        try {
            paneParent = FXMLLoader.load(getClass().getResource("bareOption.fxml"));
            paneOption.getChildren().addAll(paneParent.getChildrenUnmodifiable());
        } catch (IOException ex) {
            Logger.getLogger(AffichageOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
    /*List<OffreAbonnement> offresExpirer = offres.stream()
    .filter(offre -> today.after(offre.getDate_fin()))
    .collect(Collectors.toList());
    offresDisponibles.addAll(offresExpirer);*/
    displayPaneOffres();
    
    }    

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
        /*OffreAbonnement offreNew = new OffreAbonnement();
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
    */
        String idBTN = ((Button)event.getSource()).getId();
        Variables v = new Variables();
        if (idBTN.equals("addbtn1")){
            Variables.offre.setType(TypeAbonnement.MENSUEL);
        }
        else if (idBTN.equals("addbtn2")){
            Variables.offre.setType(TypeAbonnement.SEMESTRILLE);
        }
        else if (idBTN.equals("addbtn3")){
            Variables.offre.setType(TypeAbonnement.ANNUELLE);
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addOffre.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage2 = new Stage();
            primaryStage2.setTitle("Offre");
            primaryStage2.setScene(scene);
            
            primaryStage2.setScene(scene);
            primaryStage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}

    @FXML
    private void historiqueOffre(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("historiqueOffre.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage2 = new Stage();
            primaryStage2.setTitle("historiqueOffre");
            primaryStage2.setScene(scene);
            
        primaryStage2.setScene(scene);
        primaryStage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void displayPaneOffres() {
        List<OffreAbonnement> offres= os.allOffres();
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis()); 

    List<OffreAbonnement> offresDisponibles = offres.stream()
    .filter(offre -> today.after(offre.getDate_debut()) && today.before(offre.getDate_fin()))
    .collect(Collectors.toList());
        OffreAbonnement offreMensuel = offresDisponibles.stream()
                .filter(o -> o.getType().equals(TypeAbonnement.MENSUEL))
            .findFirst().orElse(null);
        OffreAbonnement offreSemestrille = offresDisponibles.stream()
                .filter(o -> o.getType().equals(TypeAbonnement.SEMESTRILLE))
            .findFirst().orElse(null);
        OffreAbonnement offreAnnuelle = offresDisponibles.stream()
                .filter(o -> o.getType().equals(TypeAbonnement.ANNUELLE))
            .findFirst().orElse(null);
        if (offreMensuel!=null){
            pane1.setVisible(false);
            pane4.setVisible(true);
            displayDetailPaneO(offreMensuel,prixMensuel,remis4,prixR4,prixSR4,desc4);
        }
        else {
            pane4.setVisible(false);
            pane1.setVisible(true);
            displayDetailPane(offreMensuel,prixMensuel,prix1);
        }
        if (offreSemestrille!=null){
            pane2.setVisible(false);
            pane5.setVisible(true);
            displayDetailPaneO(offreSemestrille,prixSemestrille,remis5,prixR5,prixSR5,desc5);
        }
        else {
            pane5.setVisible(false);
            pane2.setVisible(true);
            displayDetailPane(offreSemestrille,prixSemestrille,prix2);
        }
        if (offreAnnuelle!=null){
            pane3.setVisible(false);
            pane6.setVisible(true);
            displayDetailPaneO(offreAnnuelle,prixAnnuelle,remis6,prixR6,prixSR6,desc6);
        }
        else {
            pane6.setVisible(false);
            pane3.setVisible(true);
            displayDetailPane(offreAnnuelle,prixAnnuelle,prix3);
        }
        
    }

    @FXML
    private void abonnementsBTN(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageAbonnement.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,1200, 800);  
            Stage primaryStage = (Stage) pane1.getScene().getWindow();;
            primaryStage.setScene(scene);  
            primaryStage.show(); 
    }

    private void displayDetailPane(OffreAbonnement offre, double prix, Text prixS) {
        prixS.setText(String.valueOf(prix)+" DT");
        
    }

    private void displayDetailPaneO(OffreAbonnement offre, double prix, Text remis, Text prixR, Text prixSR, Text desc) {
        remis.setText(String.valueOf(offre.getReduction())+"%");
        prixR.setText(String.valueOf((100-offre.getReduction())*prix/100)+" DT");
        prixSR.setText(String.valueOf(prix)+" DT");
        desc.setText(offre.getDescription());
    }

    @FXML
    private void editOffre(ActionEvent event) {
        List<OffreAbonnement> offres= os.allOffres();
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis()); 

    List<OffreAbonnement> offresDisponibles = offres.stream()
    .filter(offre -> today.after(offre.getDate_debut()) && today.before(offre.getDate_fin()))
    .collect(Collectors.toList());
        if (((Button)event.getSource()).getId().equals("editbtn4")){
            Variables.offreSelected = offresDisponibles.stream()
                .filter(o -> o.getType().equals(TypeAbonnement.MENSUEL))
            .findFirst().orElse(null);
        }
        else if (((Button)event.getSource()).getId().equals("editbtn5")){
            Variables.offreSelected = offresDisponibles.stream()
                .filter(o -> o.getType().equals(TypeAbonnement.SEMESTRILLE))
            .findFirst().orElse(null);
        }
        else if (((Button)event.getSource()).getId().equals("editbtn6")){
            Variables.offreSelected = offresDisponibles.stream()
                .filter(o -> o.getType().equals(TypeAbonnement.ANNUELLE))
            .findFirst().orElse(null);
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("editOffre.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage2 = new Stage();
            primaryStage2.setTitle("update Offre");
            primaryStage2.setScene(scene);
            
        primaryStage2.setScene(scene);
        primaryStage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void annulerOffre(ActionEvent event) {
        List<OffreAbonnement> offres= os.allOffres();
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis()); 

    List<OffreAbonnement> offresDisponibles = offres.stream()
    .filter(offre -> today.after(offre.getDate_debut()) && today.before(offre.getDate_fin()))
    .collect(Collectors.toList());
        if (((Button)event.getSource()).getId().equals("deletebtn4")){
            Variables.offreSelected = offresDisponibles.stream()
                .filter(o -> o.getType().equals(TypeAbonnement.MENSUEL))
            .findFirst().orElse(null);
        }
        else if (((Button)event.getSource()).getId().equals("deletebtn5")){
            Variables.offreSelected = offresDisponibles.stream()
                .filter(o -> o.getType().equals(TypeAbonnement.SEMESTRILLE))
            .findFirst().orElse(null);
        }
        else if (((Button)event.getSource()).getId().equals("deletebtn6")){
            Variables.offreSelected = offresDisponibles.stream()
                .filter(o -> o.getType().equals(TypeAbonnement.ANNUELLE))
            .findFirst().orElse(null);
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("annulerOffre.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage2 = new Stage();
            primaryStage2.setTitle("Offre");
            primaryStage2.setScene(scene);
            
        primaryStage2.setScene(scene);
        primaryStage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
