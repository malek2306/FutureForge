/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Reservation_bus;
//import java.awt.Insets;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceReservationBus;
import utiles.DataSource;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class TableViewController implements Initializable {

    @FXML
    private TableView<Reservation_bus> table;
    @FXML
    private TableColumn<Reservation_bus, String> idcolumn;
    @FXML
    private TableColumn<Reservation_bus, String> nomcolumn;
    @FXML
    private TableColumn<Reservation_bus, String> prenomcolumn;
    @FXML
    private TableColumn<Reservation_bus, String> placecolumn;
    @FXML
    private TableColumn<Reservation_bus, String> editcolumn;
    
    String query = null;
    //Parent fxml ;
    Connection cnx = DataSource.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reservation_bus reservation_bus = null ;
    
    
    ObservableList<Reservation_bus>  busList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane VBOX;
    @FXML
    private Button add1;
    @FXML
    private TableColumn<Reservation_bus, String> datecolumn;
    @FXML
    private TableColumn<Reservation_bus, String> emailcolumn;
    @FXML
    private TableColumn<Reservation_bus, String> destinationcolumn;
 ObservableList<Reservation_bus>recList;
    @FXML
    private TextArea rechtxt;
    @FXML
    private Button handlerecherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();
        
    }
        // TODO
     
    

    @FXML
    private void btrefresh() {
        
        try {
            busList.clear();
            
            query = "SELECT * FROM `reservation_bus`";
        
            preparedStatement = cnx.prepareStatement(query);
        
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                busList.add(new  Reservation_bus(
                        resultSet.getInt("id_reservation_bus"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getInt("num_place"),
                        resultSet.getString("date"),
                        resultSet.getString("email"),
                        resultSet.getString("destination")   
                                
                                
                        ));
                table.setItems(busList);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
  
    }
    
    private void loadDate() {
        
       // Connection cnx = DataSource.getInstance().getCnx();
        btrefresh();
        
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id_reservation_bus"));
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcolumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        placecolumn.setCellValueFactory(new PropertyValueFactory<>("num_place"));
        datecolumn.setCellValueFactory( new PropertyValueFactory<>("date"));
           emailcolumn.setCellValueFactory( new PropertyValueFactory<>("email"));
              destinationcolumn.setCellValueFactory( new PropertyValueFactory<>("destination"));
        
        
        
        //add cell of button edit 
         Callback<TableColumn<Reservation_bus,String>, TableCell<Reservation_bus, String>> cellFoctory = (TableColumn<Reservation_bus, String> param) -> {
            // make cell containing buttons
             TableCell<Reservation_bus, String> cell ;
            cell = new TableCell<Reservation_bus, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                reservation_bus = table.getSelectionModel().getSelectedItem();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Warning");
        alert.setContentText("Voulez-vous supprimer cette ligne");
        
       
        Optional<ButtonType>result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
                                
                                
                                
                                query = "DELETE FROM `reservation_bus` WHERE id_reservation_bus ="+reservation_bus.getId_reservation_bus();
                                //connection = DbConnect.getConnect();
                                preparedStatement = cnx.prepareStatement(query);
                                preparedStatement.execute();
                               btrefresh();
        }
                            } catch (SQLException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                            
                            
                            
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            reservation_bus = table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AjouterBusM.fxml"));
                          
                          
                            
                            try {
                                
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                           reservation_bus = loader.getController();
                            reservation_bus.setUpdate(true);
                            reservation_bus.setTextField(reservation_bus.getId_reservation_bus(), reservation_bus.getNom(), 
                                    reservation_bus.getPrenom(),reservation_bus.getNum_place());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                           
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }
                
            };

            return cell 
                    ;
        };
         editcolumn.setCellFactory(cellFoctory);
         table.setItems(busList);
         
         
    }

    @FXML
    private void cov(ActionEvent event) throws IOException {
         VBOX.getScene().getWindow().hide();
        Stage Affiche = new Stage();
        Parent fxml = FXMLLoader.load(getClass().getResource("tableViewCovoiturage.fxml"));
        Scene scene = new Scene(fxml);
        Affiche.setScene(scene);
        Affiche.show();
        
    }

    private void addBTN(ActionEvent event)  {
  /*       try{
        Parent page1 = FXMLLoader.load(getClass().getResource("ajouterBUS.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterBUSController.class.getName()).log(Level.SEVERE, null, ex);
            }
     */
  
  
    }

    @FXML
    private void addbtnn(MouseEvent event) {
          VBOX.getScene().getWindow().hide();
        Stage Affiche = new Stage();
        Parent fxml;
        try {
            fxml = FXMLLoader.load(getClass().getResource("AjouterBusM.fxml"));
      
        
        Scene scene = new Scene(fxml);
        Affiche.setScene(scene);
        Affiche.show();
          } catch (IOException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

        
    

 private void search() {      
        
        // récupérer le texte de recherche à partir du champ de recherche
    String searchText = rechtxt.getText();

    // créer un objet FilteredList en utilisant la liste d'origine
    FilteredList<Reservation_bus> filteredData = new FilteredList<>(busList, p -> true);

    // définir le prédicat de filtre pour vérifier si la recherche correspond à un nom ou prénom
    filteredData.setPredicate(reservation_bus -> {
        // Si le texte de recherche est vide, afficher toutes les réservations
        if (searchText == null || searchText.isEmpty()) {
            return true;
        }

        // convertir la chaîne de recherche en minuscules pour une recherche insensible à la casse
        String lowerCaseSearchText = searchText.toLowerCase();

        // vérifier si le texte de recherche correspond à un nom ou prénom
        if (reservation_bus.getNom().toLowerCase().contains(lowerCaseSearchText)) {
            return true; // Correspondance du nom
        } else if (reservation_bus.getPrenom().toLowerCase().contains(lowerCaseSearchText)) {
            return true; // Correspondance du prénom
        }
        return false; // Pas de correspondance
    });

    // envelopper la liste filtrée avec SortedList pour activer le tri
    SortedList<Reservation_bus> sortedData = new SortedList<>(filteredData);

    // lier la SortedList à la table
    sortedData.comparatorProperty().bind(table.comparatorProperty());
    table.setItems(sortedData);
       
    }

    @FXML
    private void rech(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
        search();
        
    }
    
   
    
    }
}
        


    

