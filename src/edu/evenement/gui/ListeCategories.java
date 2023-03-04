package edu.evenement.gui;




import edu.evenement.entities.Categories;
import edu.evenement.entities.Evenement;
import edu.evenement.services.Servicecategories;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListeCategories implements Initializable {

    

    @FXML
    private ListView<Categories> usersListView;


    @FXML
    private Label userCountLabel;

    @FXML
   private TextField searchField;

    private Servicecategories userService;

    private ObservableList<Categories> userListObservable;
    @FXML
    private Button Supprimerprod;
    private BorderPane border;
    @FXML
    private Button modif;
    @FXML
    private Button Action;

    @Override
   public void initialize(URL url, ResourceBundle rb) {
       Servicecategories ps = new Servicecategories();
    userListObservable = FXCollections.observableArrayList(ps.getAll());

    // Configurez la cellule ListView pour afficher tous les attributs de la catégorie
    usersListView.setCellFactory(lv -> new ListCell<Categories>() {
        @Override
        public void updateItem(Categories item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            } else {
                setText(String.format(" Nom: %s, Description: %s, Photos: %s", item.getNom(), item.getDescription(), item.getPhoto()));
                ImageView imageView = new ImageView();
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                imageView.setImage(new Image(new ByteArrayInputStream(item.getPhoto())));
                setGraphic(imageView);
            }
        }
    });


    usersListView.setItems(userListObservable);
    userCountLabel.setText("Total des categories : " + userListObservable.size()); }
    @FXML
private void handleRefresh() {
    Servicecategories ps = new Servicecategories();
    userListObservable.setAll(ps.getAll());
    userCountLabel.setText("Total des categories : " + userListObservable.size());
}

private void handleButtonAction(ActionEvent event) throws IOException {
    
}
 //   @FXML
   // private void handleSearch() {
     //   String searchQuery = searchField.getText().trim().toLowerCase();
       // if (searchQuery.isEmpty()) {
       //     usersListView.setItems(userListObservable);
       // } else {
        //    ObservableList<Users> filteredList = FXCollections.observableArrayList();
          //  for (Users user : userListObservable) {
            //    if (user.getNom().toLowerCase().contains(searchQuery)
              //          || user.getPrenom().toLowerCase().contains(searchQuery)
                //        || user.getEmail().toLowerCase().contains(searchQuery)) {
                 //   filteredList.add(user);
               // }
            //}
            //usersListView.setItems(filteredList);
        //}
        //userCountLabel.setText("Total des utilisateurs : " + usersListView.getItems().size());
    //}

    @FXML
    private void Supprimerprod(ActionEvent event) {
   if (!usersListView.getSelectionModel().isEmpty()) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer l'etat d'id num " + usersListView.getSelectionModel().getSelectedItem().getNom()+ " ?", ButtonType.YES, ButtonType.NO);
alert1.showAndWait();
        
         Categories selectedSERVICE =  usersListView.getSelectionModel().getSelectedItem();

         Servicecategories es= new Servicecategories();
         es.delete(selectedSERVICE);
        if (alert1.getResult() == ButtonType.YES) {
         es.delete(selectedSERVICE);
    }
    
        Alert alert2 = new Alert(AlertType.INFORMATION, "etat Supprimé.", ButtonType.OK);
    alert2.showAndWait();
   
        
    }
    }



    @FXML
    private void modif(ActionEvent event) throws IOException {
        Categories selectedUser = usersListView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
        // Afficher un message d'erreur pour informer l'utilisateur qu'il doit sélectionner un utilisateur avant de pouvoir le modifier
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un etat à modifier.");
        alert.showAndWait();
        return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierCategorie.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ModifierCategorieController updateUserController = fxmlLoader.getController();
        updateUserController.initData(selectedUser);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Rafraîchir la liste des utilisateurs après la mise à jour
        handleRefresh();

     
    
    }

    @FXML
    private void rechercher(ActionEvent event) {
        
        
        userListObservable.clear();
        Servicecategories ps = new Servicecategories();
        userListObservable.addAll(ps.getAll().stream().filter((art) 
                -> art.getNom().toLowerCase().contains(searchField.getText().toLowerCase())
                
                
                
        //                || Integer.toString(art.getPrixAchat()).equals(searchTF.getText())
        //                || Integer.toString(art.getPrixVente()).equals(searchTF.getText())

        ).collect(Collectors.toList()));
    }

    
    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjouterCategorie.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AjouterCategorieController updateUserController = fxmlLoader.getController();
       

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

     
    @FXML
    private void lissst(ListView.EditEvent<Categories> event) {
    }
    

}

    
 
   
    

    




