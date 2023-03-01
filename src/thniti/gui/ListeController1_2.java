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
import thniti.entities.Reclamation;
import thniti.services.ServiceReclamation;

public class ListeController1_2 implements Initializable {


    @FXML
    private ListView<Reclamation> usersListView;


    @FXML
    private Label userCountLabel;

    @FXML
   private TextField searchField;

    private ServiceReclamation userService;

    private ObservableList<Reclamation> userListObservable;
    @FXML
    private Button traitter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceReclamation ps = new ServiceReclamation();
        userListObservable = FXCollections.observableArrayList(ps.getAll());
        usersListView.setItems(userListObservable);
        userCountLabel.setText("Total des reclamations : " + userListObservable.size());
    }

private void handleRefresh() {
    ServiceReclamation ps = new ServiceReclamation();
    userListObservable.setAll(ps.getAll());
    userCountLabel.setText("Total des reclamations : " + userListObservable.size());
}

private void handleButtonAction(ActionEvent event) throws IOException {
    Parent destinationParent = FXMLLoader.load(getClass().getResource("Third.fxml"));
    Scene destinationScene = new Scene(destinationParent);
   Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(destinationScene);
    window.show();
}
   

    private void Supprimerprod(ActionEvent event) {
        if (!usersListView.getSelectionModel().isEmpty()) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer la réclamation d'objet " + usersListView.getSelectionModel().getSelectedItem().getObjet()+ " ?", ButtonType.YES, ButtonType.NO);
alert1.showAndWait();
        
         Reclamation selectedSERVICE =  usersListView.getSelectionModel().getSelectedItem();

         ServiceReclamation ps= new ServiceReclamation();
         ps.supprimer(selectedSERVICE);
        if (alert1.getResult() == ButtonType.YES) {
         ps.supprimer(selectedSERVICE);
    };
    
        Alert alert2 = new Alert(AlertType.INFORMATION, "Reclamation Supprimée.", ButtonType.OK);
    alert2.showAndWait();
   
        
    }
    }

    @FXML
    private void searchField(ActionEvent event) {
        userListObservable.clear();
        ServiceReclamation ps = new ServiceReclamation();
        userListObservable.addAll(ps.getAll().stream().filter((art)
                -> art.getTypeR().toLowerCase().contains(searchField.getText().toLowerCase())
                || art.getObjet().toLowerCase().contains(searchField.getText().toLowerCase())
               
               
        //                || Integer.toString(art.getPrixAchat()).equals(searchTF.getText())
        //                || Integer.toString(art.getPrixVente()).equals(searchTF.getText())

        ).collect(Collectors.toList()));
        userCountLabel.setText("Total des reclamations : " + userListObservable.size());
    }

    private void modif(ActionEvent event) throws IOException {
        
        Reclamation selectedUser = usersListView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
        // Afficher un message d'erreur pour informer l'utilisateur qu'il doit sélectionner un utilisateur avant de pouvoir le modifier
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un etat à modifier.");
        alert.showAndWait();
        return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifier.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ModifierController updateUserController = fxmlLoader.getController();
        updateUserController.initData(selectedUser);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Rafraîchir la liste des utilisateurs après la mise à jour
        handleRefresh();
    }

    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouter_rec.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AjouterRecController updateUserController = fxmlLoader.getController();
       

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

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

    private void trier(ActionEvent event) {
        ServiceReclamation ps = new ServiceReclamation();
    userListObservable.setAll(ps.TRI());
    userCountLabel.setText("Total des rec : " + userListObservable.size());
    }

    @FXML
    private void traitter(ActionEvent event) {
                 Reclamation selectedUser = usersListView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
        // Afficher un message d'erreur pour informer l'utilisateur qu'il doit sélectionner un utilisateur avant de pouvoir le modifier
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une candidature à accepter.");
        alert.showAndWait();
        return;
        }
 

          ServiceReclamation s = new ServiceReclamation();

        // update the user in the database
        s.Traiter(selectedUser);

        // show a success message to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification réussie");
        alert.setHeaderText(null);
        alert.setContentText("La candidature a été confirmée avec succès !");
        alert.showAndWait();
        
            userListObservable.setAll(s.getAll());
    }

}


