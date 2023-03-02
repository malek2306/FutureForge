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
import abonnementFX.Util.MaConnexion;
import abonnementfx.Util.Variables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichageAbonnementController implements Initializable {

    private TableView<Abonnement> tableAbonnement;
    private Pane paneOption;
    private ImageView imgEtudiant;
private static int id_Clicked = 0;
    /**
     * Initializes the controller class.
     */
        private AbonnementService as=new AbonnementService();
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane6;
    @FXML
    private Pane pane7;
    @FXML
    private Pane pane8;
    @FXML
    private Pane pane9;
    @FXML
    private ComboBox<String> comboPage;
    @FXML
    private ImageView imageEtu1;
    @FXML
    private ImageView imageEtu2;
    @FXML
    private ImageView imageEtu4;
    @FXML
    private ImageView imageEtu5;
    @FXML
    private ImageView imageEtu3;
    @FXML
    private ImageView imageEtu6;
    @FXML
    private ImageView imageEtu7;
    @FXML
    private ImageView imageEtu8;
    @FXML
    private ImageView imageEtu9;
    @FXML
    private ToggleGroup TOUS;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*Parent paneParent;
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
    tableAbonnement.setItems(FXCollections.observableList(abonnements));*/
        remplirComboPage();
        try {
            affichageAbonnement();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AffichageAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

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

    @FXML
    private void offresBTN(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageOffre.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,1200, 800);  
            Stage primaryStage = (Stage) pane1.getScene().getWindow();;
            primaryStage.setScene(scene);  
            primaryStage.show(); 
    }
public void remplirComboPage(){
    List<Abonnement> abonnements= as.getall_abonnements();
    ObservableList<String> list=FXCollections.observableArrayList();
    
        if (abonnements.size()%9==0){
            for (int i=1;i<abonnements.size()/9+1;i++)
            {
                list.add(Integer.toString(i));
            }
        }
        else {
            for (int i=1;i<abonnements.size()/9+2;i++)
            {
                
                list.add(Integer.toString(i));
            }
        }
    comboPage.setItems(list);
    comboPage.setValue("1");
    }
    public void affichageAbonnement() throws FileNotFoundException{
        try {
            List<Pane> panes=new ArrayList<>();
            panes.add(pane1);panes.add(pane2);panes.add(pane3);panes.add(pane4);panes.add(pane5);panes.add(pane6);panes.add(pane7);panes.add(pane8);panes.add(pane9);
            List<ImageView> imageEvent=new ArrayList<>();
            imageEvent.add(imageEtu1);imageEvent.add(imageEtu2);imageEvent.add(imageEtu3);imageEvent.add(imageEtu4);
            imageEvent.add(imageEtu5);imageEvent.add(imageEtu6);imageEvent.add(imageEtu7);imageEvent.add(imageEtu8);imageEvent.add(imageEtu9);
            
            
            for (Pane p : panes){
                p.setVisible(false);
            }
              Connection conn=MaConnexion.getInstance().getCnx();
            PreparedStatement ste;
            int a=(Integer.parseInt(comboPage.getValue())-1)*9;
            int b=Integer.parseInt(comboPage.getValue())*9;
            System.out.println("a="+a+"     b="+b);
            String sql=null;
            sql = "select nom,prenom,identifiant,type,dateD,dateF,image from abonnement limit "+a+","+b;
            ste = conn.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);
            int i=0;
            while(rs.next()){
                panes.get(i).setVisible(true);
                if (rs.getString(7)!=null){
                    InputStream stream2 = new FileInputStream(rs.getString(7));
                    Image image2 = new Image(stream2);
                    imageEvent.get(i).setImage(image2);
                }
                else {
                    imageEvent.get(i).setImage(null);
                }            
                
                

                
                ((Text)panes.get(i).getChildren().get(16)).setText(rs.getString(1));
                ((Text)panes.get(i).getChildren().get(15)).setText(rs.getString(2));
                ((Text)panes.get(i).getChildren().get(14)).setText(rs.getString(3));
                ((Text)panes.get(i).getChildren().get(13)).setText(rs.getString(4));
                ((Text)panes.get(i).getChildren().get(10)).setText(rs.getString(5));
                ((Text)panes.get(i).getChildren().get(12)).setText(rs.getString(6));
                i++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        String identifiant = ((Text)((Pane)event.getSource()).getChildren().get(14)).getText();
        Variables.abonnementSelected = as.get_abonnementByIdentifiant(identifiant);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DetailAbonnement.fxml"));
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
    private void byIdentifiant(MouseEvent event) {
        
    }


    @FXML
    private void byType(MouseEvent event) {
    }

    public void affichage_Filtrer_NomDate_Event(String type) throws SQLException, FileNotFoundException, ParseException{
        try {
            Connection conn = MaConnexion.getInstance().getCnx();
            PreparedStatement ste;
            String sql = "select count(*) from abonnement where type ='"+type+"'";
            ste = conn.prepareStatement(sql);
            ResultSet rs = ste.executeQuery(sql);
   ObservableList<String> list=FXCollections.observableArrayList();  
            while(rs.next()){
                if (Integer.parseInt(rs.getString(1))%9==0){
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/9+1;i++)
                    {
                        list.add(Integer.toString(i));
                    }
                }
                else {
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/9+2;i++)
                    {
                        
                        list.add(Integer.toString(i));
                    }
                }
            }
            comboPage.setItems(list);
    comboPage.setValue("1");
        } catch (SQLException ex) {
            Logger.getLogger(AffichageAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            List<Pane> panes=new ArrayList<>();
            panes.add(pane1);panes.add(pane2);panes.add(pane3);panes.add(pane4);panes.add(pane5);panes.add(pane6);panes.add(pane7);panes.add(pane8);panes.add(pane9);
            List<ImageView> imageEvent=new ArrayList<>();
            imageEvent.add(imageEtu1);imageEvent.add(imageEtu2);imageEvent.add(imageEtu3);imageEvent.add(imageEtu4);
            imageEvent.add(imageEtu5);imageEvent.add(imageEtu6);imageEvent.add(imageEtu7);imageEvent.add(imageEtu8);imageEvent.add(imageEtu9);
            
            
            for (Pane p : panes){
                p.setVisible(false);
            }
              Connection conn=MaConnexion.getInstance().getCnx();
            PreparedStatement ste;
            int a=(Integer.parseInt(comboPage.getValue())-1)*9;
            int b=Integer.parseInt(comboPage.getValue())*9;
            System.out.println("a="+a+"     b="+b);
            String sql=null;
            sql = "select nom,prenom,identifiant,type,dateD,dateF,image from abonnement where type = '"+type+"' limit "+a+","+b;
            ste = conn.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);
            int i=0;
            while(rs.next()){
                panes.get(i).setVisible(true);
                if (rs.getString(7)!=null){
                    InputStream stream2 = new FileInputStream(rs.getString(7));
                    Image image2 = new Image(stream2);
                    imageEvent.get(i).setImage(image2);
                }
                else {
                    imageEvent.get(i).setImage(null);
                }
                ((Text)panes.get(i).getChildren().get(16)).setText(rs.getString(1));
                ((Text)panes.get(i).getChildren().get(15)).setText(rs.getString(2));
                ((Text)panes.get(i).getChildren().get(14)).setText(rs.getString(3));
                ((Text)panes.get(i).getChildren().get(13)).setText(rs.getString(4));
                ((Text)panes.get(i).getChildren().get(10)).setText(rs.getString(5));
                ((Text)panes.get(i).getChildren().get(12)).setText(rs.getString(6));
                i++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void changeType(ActionEvent event) throws SQLException, FileNotFoundException, FileNotFoundException, ParseException, ParseException, ParseException, ParseException {
        if (((RadioButton)event.getSource()).getText().equals("Tous")){
            remplirComboPage();
            try {
                affichageAbonnement();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AffichageAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (((RadioButton)event.getSource()).getText().equals("Mensuel")){
            affichage_Filtrer_NomDate_Event("MENSUEL");
        }
        else if (((RadioButton)event.getSource()).getText().equals("Semestriel")){
            affichage_Filtrer_NomDate_Event("SEMESTRILLE");
        }
        else if (((RadioButton)event.getSource()).getText().equals("Annuel")){
            affichage_Filtrer_NomDate_Event("ANNUELLE");
        }
    }

    @FXML
    private void COMBO(ActionEvent event) throws FileNotFoundException {
        affichageAbonnement();
    }
    
}
