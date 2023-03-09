/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.gui.AbonnementEtudent;

import abonnementFX.Entities.Abonnement;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichageOffre2Controller implements Initializable {

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
    private Pane pane4;
    @FXML
    private Text prixR4;
    @FXML
    private Text remis4;
    @FXML
    private Text prixSR4;
    @FXML
    private Text desc4;
    @FXML
    private Pane pane5;
    @FXML
    private Text prixR5;
    @FXML
    private Text remis5;
    @FXML
    private Text prixSR5;
    @FXML
    private Text desc5;
    @FXML
    private Pane pane6;
    @FXML
    private Text prixR6;
    @FXML
    private Text remis6;
    @FXML
    private Text prixSR6;
    @FXML
    private Text desc6;
private OffreService os=new OffreService();
private AbonnementService as=new AbonnementService();
    @FXML
    private Text cds;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayPaneOffres();
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
    private void abonnementsBTN(ActionEvent event) {
        try {
                    Parent root = FXMLLoader.load(getClass().getResource("detailAbonnement.fxml"));
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
    private void getSelectedOffre(MouseEvent event) {
        Abonnement ab = new Abonnement();
        ab=as.get_abonnementByIdentifiantDate(Variables.identifiantUserConnecte);
        if (ab==null){
                List<OffreAbonnement> offres= os.allOffres();
                java.sql.Date today = new java.sql.Date(System.currentTimeMillis()); 

            List<OffreAbonnement> offresDisponibles = offres.stream()
            .filter(offre -> today.after(offre.getDate_debut()) && today.before(offre.getDate_fin()))
            .collect(Collectors.toList());
                if (((Pane)event.getSource()).getId().equals("pane4")){
                    Variables.offreSelected = offresDisponibles.stream()
                        .filter(o -> o.getType().equals(TypeAbonnement.MENSUEL))
                    .findFirst().orElse(null);
                }
                else if (((Pane)event.getSource()).getId().equals("pane5")){
                    Variables.offreSelected = offresDisponibles.stream()
                        .filter(o -> o.getType().equals(TypeAbonnement.SEMESTRILLE))
                    .findFirst().orElse(null);
                }
                else if (((Pane)event.getSource()).getId().equals("pane6")){
                    Variables.offreSelected = offresDisponibles.stream()
                        .filter(o -> o.getType().equals(TypeAbonnement.ANNUELLE))
                    .findFirst().orElse(null);
                }
                else if (((Pane)event.getSource()).getId().equals("pane1")){
                    Variables.offreSelected.setType(TypeAbonnement.MENSUEL);
                }
                else if (((Pane)event.getSource()).getId().equals("pane2")){
                    Variables.offreSelected.setType(TypeAbonnement.SEMESTRILLE);
                }
                else if (((Pane)event.getSource()).getId().equals("pane3")){
                    Variables.offreSelected.setType(TypeAbonnement.ANNUELLE);
                }
                cds.setVisible(false);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("ajouterAbonnement.fxml"));
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
        else {
            cds.setVisible(true);
        }
    }
}
