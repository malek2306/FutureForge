/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import services.ServiceReservationBus;
import entities.Reservation_bus;
import java.io.IOException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Stage;
import utiles.DataSource;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterBUSController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_num;
    @FXML
    private AnchorPane VBOX;
    //private AnchorPane VBOX;
   // private Parent fxml;
    private boolean update;
    ServiceReservationBus sp = new ServiceReservationBus();
    int x = Integer.parseInt(tf_num.getText());
    Reservation_bus rb = new Reservation_bus(tf_nom.getText(), tf_prenom.getText(), x);
   

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void btAjouter(ActionEvent event) {
        Connection cnx = DataSource.getInstance().getCnx();
        
            Alert alert;
            if (tf_nom.getText().isEmpty() || tf_prenom.getText().isEmpty() || tf_num.getText().isEmpty() ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("fill all blank fields");
                alert.showAndWait();
            } else {
            try {
                //try {
                String checkDATA = " SELECT nom FROM reservation_bus WHERE nom = '"
                        + tf_nom.getText() + "'";
                PreparedStatement prepare = cnx.prepareStatement(checkDATA);
                ResultSet result = prepare.executeQuery();
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(tf_nom.getText() + "  IS ALREADY TAKEN ! ");
                    alert.showAndWait();
                } else if ( update == false ){
                
                
                sp.ajouter(rb);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Reservation ADDED !", ButtonType.OK);
                a.showAndWait();
                } sp.modifier1(rb);
                } catch (SQLException ex) {
                Logger.getLogger(AjouterBUSController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            

        
    }

    @FXML
    private void btclean(ActionEvent event) {
        tf_nom.setText(null);
        tf_prenom.setText(null);
        tf_num.setText(null);
        
        
    }

    void setUpdate(boolean b) {
         this.update = b;
    }

    void setTextField(int id_reservation_bus, String nom, String prenom, int num_place) {
        int id_bus = id_reservation_bus;
        tf_nom.setText(nom);
        tf_prenom.setText(prenom);
        x= Integer.parseInt(tf_num.getText());
        //tf_num.();
        
    }
}


    /* public ObservableList<Reservation_bus>  BUSlistdata(){

        ObservableList<Reservation_bus> listbus = FXCollections.observableArrayList();
        Connection cnx = DataSource.getInstance().getCnx();
        String selectdata ="SELECT * FROM reservation_bus";
        
        //PreparedStatement prepare = cnx.prepareStatement(cnx);
        //ResultSet result = prepare.executeQuery();
        try {
            PreparedStatement prepare = cnx.prepareStatement(selectdata);
            ResultSet rs = prepare.executeQuery();
            Reservation_bus rbs;
            {
                while (rs.next()) {
                    rbs = new Reservation_bus(rs.getInt("id_reservation_bus"),
                            rs.getString("nom"), rs.getString("prenom"),
                            rs.getInt("num_place"));
                    listbus.add(rbs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterBUSController.class.getName()).log(Level.SEVERE, null, ex);
        }return listbus;
    }
private ObservableList<Reservation_bus> reservation_bus;
public void busshowdata()
{
    reservation_bus = BUSlistdata();
    
    idcolumn.setCellValueFactory(new PropertyValueFactory<>("id_reservation_bus"));
    nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenomcolumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    numcolumn.setCellValueFactory(new PropertyValueFactory<>("num_place"));
    table.setItems(reservation_bus);
}
    @FXML
    private void busselecteddata() 
    {
         Reservation_bus rbus = table.getSelectionModel().getSelectedItem();
         int num = table.getSelectionModel().getSelectedIndex();
         
         if ((num-1) < -1) return;
         idcolumn.setText(String.valueOf(rbus.getId_reservation_bus()));
         nomcolumn.setText(rbus.getNom());
         prenomcolumn.setText(rbus.getPrenom());
         numcolumn.setText(String.valueOf(rbus.getNum_place()));
        
    }
    @FXML
    private void btAfficher(ActionEvent event) {
    }

    @FXML
    private void btUpdate(ActionEvent event) {
    }

    @FXML
    private void btSupprimer(ActionEvent event) {
    }
}
    private void btAfficher(ActionEvent event) throws IOException {

        VBOX.getScene().getWindow().hide();
        Stage Affiche = new Stage();
        fxml = FXMLLoader.load(getClass().getResource("/gui/AffichageBUS.fxml"));
        Scene scene = new Scene(fxml);
        Affiche.setScene(scene);
        Affiche.show();

    }

    private void btUpdate(ActionEvent event) throws IOException {
        Connection cnx = DataSource.getInstance().getCnx();
        if (tf_nom.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("error MESSAGE");
            alert.setHeaderText(null);
            alert.setContentText("fill all blank fields");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("confirmation MESSAGE");
            alert.setHeaderText(null);
            alert.setContentText(" Are you SURE !!");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
              

            }else {
                alert = new Alert(AlertType.ERROR);
            alert.setTitle("information MESSAGE");
            alert.setHeaderText(null);
            alert.setContentText("CANCELED ");
            alert.showAndWait();
                
            }

        }
    }

    private void btSupprimer(ActionEvent event) throws IOException {
        VBOX.getScene().getWindow().hide();
        Stage Affiche = new Stage();
        fxml = FXMLLoader.load(getClass().getResource("/gui/supprimerBus.fxml"));
        Scene scene = new Scene(fxml);
        Affiche.setScene(scene);
        Affiche.show();
    }}**/
