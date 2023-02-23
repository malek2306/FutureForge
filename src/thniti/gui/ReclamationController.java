/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import thniti.entities.Reclamation;
import thniti.services.ServiceReclamation;

public class ReclamationController implements Initializable {

    @FXML
    private ListView<Reclamation> RecLV;
    
    ServiceReclamation sec = new ServiceReclamation();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Reclamation> data = FXCollections.observableArrayList(sec.getAll());
        RecLV.setItems(data);
        
        RecLV.setCellFactory(param -> new ListCell<Reclamation>() {
            @Override
            protected void updateItem(Reclamation item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%d | %s | %s | %s | %s | %s", 
                            item.getId_R(), item.getTypeR(), item.getDescriptionR(),
                            item.getObjet(), item.getDateR(), item.getEtat()));
                }
            }
        });
    }
}
