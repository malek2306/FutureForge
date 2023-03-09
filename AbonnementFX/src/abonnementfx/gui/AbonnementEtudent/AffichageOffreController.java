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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import static java.time.LocalDate.MAX;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichageOffreController implements Initializable {

    @FXML
    private TableView<OffreAbonnement> tableOffre;
    @FXML
    private ImageView imgEtudiant;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField identifiant;
    @FXML
    private TextField cin;
    @FXML
    private ComboBox<String> type;
    @FXML
    private DatePicker dateD;
    @FXML
    private DatePicker dateF;
    @FXML
    private TextField idOffre;
    @FXML
    private Text prixSR;
    @FXML
    private Text reduction;
    @FXML
    private Text prixAR;

    /**
     * Initializes the controller class.
     */
    private final int prixMensuel = 120 ; 
    private final int prixSemestrille = 600 ;
    private final int prixAnnuelle = 1000 ;

    private OffreService os=new OffreService();
    private AbonnementService as=new AbonnementService();
    private static OffreAbonnement offreChoisir=null;
    private static String imgPath = "";
    @FXML
    private Button addBTN;
    @FXML
    private TextField identifiantModifier;
    @FXML
    private Button modifierBTN;
    @FXML
    private Button annulerBTN;
    @FXML
    private ListView<OffreAbonnement> listview;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.getItems().add("MENSUEL");
                type.getItems().add("SEMESTRILLE");
                        type.getItems().add("ANNUELLE");
        List<OffreAbonnement> offres= os.allOffres();
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis()); 

    List<OffreAbonnement> offresDisponibles = offres.stream()
    .filter(offre -> today.after(offre.getDate_debut()) && today.before(offre.getDate_fin()))
    .collect(Collectors.toList());
    /*
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
    */
    ObservableList<OffreAbonnement> offerss = FXCollections.observableArrayList(
            offres
    );
    listview.setItems(offerss);
        Label selectedOfferLabel = new Label();

        Button addButton = new Button("Add Offer");
        addButton.setOnAction(event -> {
            // Prompt user to enter offer details
            String offer = "New Offer"; // Replace with user input
            //offers.add(offer);
        });

        Button removeButton = new Button("Remove Offer");
        removeButton.setOnAction(event -> {
            
        });

        listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //selectedOfferLabel.setText(newValue);
        });
    }    

    @FXML
    private void choisirOffre(MouseEvent event) {
        OffreAbonnement selectedItem = tableOffre.getSelectionModel().getSelectedItem();
        offreChoisir = selectedItem;
        idOffre.setText(String.valueOf(selectedItem.getId_ofab()));
        type.setValue(selectedItem.getType().toString());
        type.setDisable(true);
        if (selectedItem.getType().equals(TypeAbonnement.MENSUEL))
            prixSR.setText(String.valueOf(prixMensuel));
        else if (selectedItem.getType().equals(TypeAbonnement.SEMESTRILLE))
            prixSR.setText(String.valueOf(prixSemestrille));
        else if (selectedItem.getType().equals(TypeAbonnement.ANNUELLE))
            prixSR.setText(String.valueOf(prixAnnuelle));
        prixAR.setText(String.valueOf(Double.parseDouble(prixSR.getText())*(100-selectedItem.getReduction())/100));
        reduction.setText(String.valueOf(Double.parseDouble(prixSR.getText())-Double.parseDouble(prixAR.getText())));
        
        idOffre.setDisable(true);
    }


    @FXML
    private void ajouterImage(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        String extension = null;
        if(selected !=null )
        {
             extension= selected.getAbsolutePath().substring(selected.getAbsolutePath().length()-3,selected.getAbsolutePath().length());
            System.out.println(extension);
            if(!extension.equals( "jpg") && !extension.equals("png"))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid picture");
        
        alert.setContentText("Invalid picture format (only jgp/png available)"); 
     
        alert.showAndWait();
        }else
            imgEtudiant.setImage(null);
        }
        InputStream stream = new FileInputStream(selected.getAbsolutePath());
      Image image = new Image(stream);
        imgEtudiant.setImage(image);
            imgPath=selected.getAbsolutePath();
    }

    @FXML
    private void sansOffre(ActionEvent event) {
        offreChoisir = null;
        type.setDisable(false);
        type.setValue("");
        prixSR.setText("");
        prixAR.setText("");
        reduction.setText("");
        idOffre.setDisable(true);
    }

    @FXML
    private void addAbonnement(ActionEvent event) {
        boolean valide = true;
        Abonnement abonnement = new Abonnement();
        abonnement.setNom(nom.getText());
        abonnement.setPrenom(prenom.getText());
        
        String pattern ="^\\S+\\..*@esprit\\.tn$";
        Pattern emailRegex = Pattern.compile(pattern);
        Matcher matcher = emailRegex.matcher(email.getText());
        if(matcher.matches())    abonnement.setEmail(email.getText());
        else valide = false;
        pattern = "\\d{3}(JMT|JFT)\\d{4}";
        Pattern regex = Pattern.compile(pattern);
        matcher = regex.matcher(identifiant.getText());
        if (matcher.matches()){
            abonnement.setIdentifiant(identifiant.getText());
        }
        else valide=false;
        pattern = "\\d{8}";        
        regex = Pattern.compile(pattern);
        matcher = regex.matcher(cin.getText());
        if (matcher.matches()){
            abonnement.setCin(cin.getText());
        }
        else valide=false;
        abonnement.setDateDebut(java.sql.Date.valueOf(dateD.getValue()));
        abonnement.setDateFin(java.sql.Date.valueOf(dateF.getValue()));
        abonnement.setPrix(Double.parseDouble(prixAR.getText()));
        abonnement.setType(TypeAbonnement.valueOf(type.getValue()));
        abonnement.setImg(imgPath);
        if (valide==true)
        as.ajouter_abonnement(abonnement);
    }

    @FXML
    private void typeChangerd(ActionEvent event) {
        if (((ComboBox<String>)event.getSource()).getValue().equals(TypeAbonnement.MENSUEL.toString()))
            prixSR.setText(String.valueOf(prixMensuel));
        else if (((ComboBox<String>)event.getSource()).getValue().equals(TypeAbonnement.SEMESTRILLE.toString()))
            prixSR.setText(String.valueOf(prixSemestrille));
        else if (((ComboBox<String>)event.getSource()).getValue().equals(TypeAbonnement.ANNUELLE.toString()))
            prixSR.setText(String.valueOf(prixAnnuelle));
        prixAR.setText(prixSR.getText());
    }

    @FXML
    private void searchAbonnement(ActionEvent event) {
        Abonnement abonnement = as.get_abonnementByIdentifiant(identifiantModifier.getText());
        nom.setText(abonnement.getNom());
        prenom.setText(abonnement.getPrenom());
        identifiant.setText(abonnement.getIdentifiant());
        email.setText(abonnement.getEmail());
        cin.setText(abonnement.getCin());
        type.setDisable(true);
        dateD.setDisable(true);
        dateF.setDisable(true);
        idOffre.setDisable(true);
        type.setValue(abonnement.getType().toString());
        dateD.setValue(abonnement.getDateDebut().toLocalDate());
        dateF.setValue(abonnement.getDateFin().toLocalDate());
        idOffre.setText(String.valueOf(abonnement.getId_offre()));
        addBTN.setVisible(false);
        modifierBTN.setVisible(true);
        annulerBTN.setVisible(true);
    }

    @FXML
    private void ModifierAbonnement(ActionEvent event) {
        boolean valide = true;
        Abonnement abonnement = new Abonnement();
        abonnement.setNom(nom.getText());
        abonnement.setPrenom(prenom.getText());
        
        String pattern ="^\\S+\\..*@esprit\\.tn$";
        Pattern emailRegex = Pattern.compile(pattern);
        Matcher matcher = emailRegex.matcher(email.getText());
        if(matcher.matches())    abonnement.setEmail(email.getText());
        else valide = false;
        pattern = "\\d{3}(JMT|JFT)\\d{4}";
        Pattern regex = Pattern.compile(pattern);
        matcher = regex.matcher(identifiant.getText());
        if (matcher.matches()){
            abonnement.setIdentifiant(identifiant.getText());
        }
        else valide=false;
        pattern = "\\d{8}";        
        regex = Pattern.compile(pattern);
        matcher = regex.matcher(cin.getText());
        if (matcher.matches()){
            abonnement.setCin(cin.getText());
        }
        else valide=false;
        abonnement.setDateDebut(java.sql.Date.valueOf(dateD.getValue()));
        abonnement.setDateFin(java.sql.Date.valueOf(dateF.getValue()));
        abonnement.setPrix(Double.parseDouble(prixAR.getText()));
        abonnement.setType(TypeAbonnement.valueOf(type.getValue()));
        abonnement.setImg(imgPath);
        if (valide==true)
            as.modifier_abonnement(identifiantModifier.getText() , abonnement);
        
        nom.setText("");
        prenom.setText("");
        identifiant.setText("");
        email.setText("");
        cin.setText("");
        type.setDisable(false);
        dateD.setDisable(false);
        dateF.setDisable(false);
        idOffre.setDisable(false);
        type.setValue("");
        dateD.setValue(null);
        dateF.setValue(null);
        idOffre.setText("");
        addBTN.setVisible(true);
        modifierBTN.setVisible(false);
        annulerBTN.setVisible(false);
    }

    @FXML
    private void annulerAbonnement(ActionEvent event) {
        nom.setText("");
        prenom.setText("");
        identifiant.setText("");
        email.setText("");
        cin.setText("");
        type.setDisable(false);
        dateD.setDisable(false);
        dateF.setDisable(false);
        idOffre.setDisable(false);
        type.setValue("");
        dateD.setValue(null);
        dateF.setValue(null);
        idOffre.setText("");
        addBTN.setVisible(true);
        modifierBTN.setVisible(false);
        annulerBTN.setVisible(false);
    }
    
}
