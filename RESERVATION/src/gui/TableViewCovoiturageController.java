/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Reservation_covoiturage;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import utiles.DataSource;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class TableViewCovoiturageController implements Initializable {

    @FXML
    private AnchorPane VBOX;
    @FXML
    private TableView<Reservation_covoiturage> table;
    @FXML
    private TableColumn<Reservation_covoiturage, String> idcolumn;
    @FXML
    private TableColumn<Reservation_covoiturage, String> nomcolumn;
    @FXML
    private TableColumn<Reservation_covoiturage, String> prenom;
    @FXML
    private TableColumn<Reservation_covoiturage, String> pntcolumn;
    @FXML
    private TableColumn<Reservation_covoiturage, String> discolumn;
    @FXML
    private TableColumn<Reservation_covoiturage, String> nbrcolumn;
    @FXML
    private TableColumn<Reservation_covoiturage, String> datecolumn;
    @FXML
    private TableColumn<Reservation_covoiturage, String> editcolumn;

    String query = null;
    //Parent fxml ;
    Connection cnx = DataSource.getInstance().getCnx();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Reservation_covoiturage reservation_covoiturage = null;
    ObservableList<Reservation_covoiturage> covList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }

    @FXML
    private void btadd(ActionEvent event) {
        try {
            VBOX.getScene().getWindow().hide();
            Stage Affiche = new Stage();
            Parent fxml;
            
            fxml = FXMLLoader.load(getClass().getResource("AjouterCov.fxml"));
            
            
            Scene scene = new Scene(fxml);
            Affiche.setScene(scene);
            Affiche.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
          } 
        
    

    @FXML
    private void btrefresh() {

        try {
            covList.clear();

            query = "SELECT * FROM `reservation_covoiturage`";

            preparedStatement = cnx.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                covList.add(new Reservation_covoiturage(
                        resultSet.getInt("id_reservation"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("pnt_rencontre"),
                        resultSet.getString("distination"),
                        resultSet.getInt("nbr_place"),
                        resultSet.getString("date")
                ));
                table.setItems(covList);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TableViewCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDate() {

       // Connection cnx = DataSource.getInstance().getCnx();
        btrefresh();

        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        pntcolumn.setCellValueFactory(new PropertyValueFactory<>("pnt_rencontre"));
        discolumn.setCellValueFactory(new PropertyValueFactory<>("distination"));
        nbrcolumn.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        //add cell of button edit 
        Callback<TableColumn<Reservation_covoiturage, String>, TableCell<Reservation_covoiturage, String>> cellFoctory = (TableColumn<Reservation_covoiturage, String> param) -> {
            // make cell containing buttons
            TableCell<Reservation_covoiturage, String> cell;
            cell = new TableCell<Reservation_covoiturage, String>() {
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
                                Reservation_covoiturage reservation_covoiturage = table.getSelectionModel().getSelectedItem();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setHeaderText("Warning");
                                alert.setContentText("Voulez-vous supprimer cette ligne");

                                Optional<ButtonType> result = alert.showAndWait();

                                if (result.get() == ButtonType.OK) {

                                    query = "DELETE FROM `reservation_covoiturage` WHERE id_reservation =" + reservation_covoiturage.getId_reservation();
                                    //connection = DbConnect.getConnect();
                                    preparedStatement = cnx.prepareStatement(query);
                                    preparedStatement.execute();
                                    btrefresh();
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(TableViewCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            reservation_covoiturage = table.getSelectionModel().getSelectedItem();
                          
                          FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("modifierCOV.fxml")); 
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                           ModifierCOVController modifierCOVController = loader.getController();
                           // modifierBUSController.setUpdate(true);
                            modifierCOVController.setTextField(reservation_covoiturage.getNom(), 
                                    reservation_covoiturage.getPrenom(),reservation_covoiturage.getPnt_rencontre(),reservation_covoiturage.getDistination(),reservation_covoiturage.getNbr_place(),reservation_covoiturage.getDate());
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

            return cell;
        };
        editcolumn.setCellFactory(cellFoctory);
        table.setItems(covList);

    }

    @FXML
    private void btback(ActionEvent event) {
        try {
            VBOX.getScene().getWindow().hide();
            Stage Affiche = new Stage();
            Parent fxml;
            
            fxml = FXMLLoader.load(getClass().getResource("tableView.fxml"));
            
            
            Scene scene = new Scene(fxml);
            Affiche.setScene(scene);
            Affiche.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
          } 
    }

