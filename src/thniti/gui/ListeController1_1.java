//messagerieeeeeeeeeeeeeee
package thniti.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import thniti.entities.Messagerie;
import thniti.entities.Reclamation;
import thniti.services.ServiceMessagerie;
import thniti.services.ServiceReclamation;

public class ListeController1_1 implements Initializable {


    @FXML
    private ListView<Messagerie> usersListView;


    @FXML
    private Label userCountLabel;

    @FXML
   private TextField searchField;

    private ServiceMessagerie userService;

    private ObservableList<Messagerie> userListObservable;
    @FXML
    private Button trier;
    @FXML
    private Button Supprimerprod;
    @FXML
    private Button stat;
    @FXML
    private Button ajouter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceMessagerie ps = new ServiceMessagerie();
        userListObservable = FXCollections.observableArrayList(ps.getAll());
        usersListView.setItems(userListObservable);
        userCountLabel.setText("Total des messages : " + userListObservable.size());
    }

    @FXML
private void handleRefresh() {
    ServiceMessagerie ps = new ServiceMessagerie();
    userListObservable.setAll(ps.getAll());
    userCountLabel.setText("Total des messages : " + userListObservable.size());
}

private void handleButtonAction(ActionEvent event) throws IOException {
    Parent destinationParent = FXMLLoader.load(getClass().getResource("Third.fxml"));
    Scene destinationScene = new Scene(destinationParent);
   Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(destinationScene);
    window.show();
}
   

    @FXML
    private void Supprimerprod(ActionEvent event) {
      if (!usersListView.getSelectionModel().isEmpty()) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer la réclamation d'objet " + usersListView.getSelectionModel().getSelectedItem().getContenuM()+ " ?", ButtonType.YES, ButtonType.NO);
alert1.showAndWait();
        
         Messagerie selectedSERVICE =  usersListView.getSelectionModel().getSelectedItem();
         

         ServiceMessagerie ps= new ServiceMessagerie();
         ps.supprimer(selectedSERVICE);
        if (alert1.getResult() == ButtonType.YES) {
         ps.supprimer(selectedSERVICE);
    };
    
        Alert alert2 = new Alert(AlertType.INFORMATION, "msg Supprimée.", ButtonType.OK);
    alert2.showAndWait();
   
        
    }
    }

    @FXML
    private void searchField(ActionEvent event) {
     userListObservable.clear();
        ServiceMessagerie ps = new ServiceMessagerie();
        userListObservable.addAll(ps.getAll().stream().filter((art)
                -> art.getContenuM().toLowerCase().contains(searchField.getText().toLowerCase())
                || art.getEtat().toLowerCase().contains(searchField.getText().toLowerCase())
        ).collect(Collectors.toList()));
        userCountLabel.setText("Total des messages : " + userListObservable.size());
        
    }


    

    @FXML
    private void stat(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BarChart.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    /*private void btnsupp(ActionEvent event) {
      if (!usersListView.getSelectionModel().isEmpty()) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le msg d'objet " + usersListView.getSelectionModel().getSelectedItem().getId_M()+ " ?", ButtonType.YES, ButtonType.NO);
alert1.showAndWait();
        
         Messagerie selectedSERVICE =  usersListView.getSelectionModel().getSelectedItem();

         ServiceMessagerie ps= new ServiceMessagerie();
         ps.supprimer(selectedSERVICE);
        if (alert1.getResult() == ButtonType.YES) {
         ps.supprimer(selectedSERVICE);
    };
    
        Alert alert2 = new Alert(AlertType.INFORMATION, "msg Supprimée.", ButtonType.OK);
    alert2.showAndWait();
   
        
    }        
   
        
    }*/

    @FXML
    private void trier(ActionEvent event) {
        ServiceMessagerie ps = new ServiceMessagerie();
    userListObservable.setAll(ps.TRI());
    userCountLabel.setText("Total des messages : " + userListObservable.size());
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouter_msg.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AjoutMessageController updateUserController = fxmlLoader.getController();
       

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    
    
    
    }
        
        
    

}


