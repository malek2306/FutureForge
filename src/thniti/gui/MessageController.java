/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package thniti.gui;
import com.jfoenix.controls.JFXButton;
import com.mysql.cj.xdevapi.Session;
import thniti.entities.Conversation;
import thniti.entities.Message;
import thniti.entities.Message.TypeMsg;
import thniti.entities.Message.EtatMsg;

import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import resources.Constants;
import thniti.services.CRUDMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import thniti.services.CrudConversation;
import javafx.scene.paint.Color;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import thniti.utils.javaMail;

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author
 */
public class MessageController /*implements WebSocketListener*/ {

    //public JFXButton download;
    public JFXButton statistique;
    @FXML
    private Label messageLabel;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    int size = 25;

    @FXML
    private ListView<String> conversationList;

    @FXML
    private ListView<String> messageList;

    @FXML
    private Button showButton;

    VBox conversationBox = new VBox();

    private final CrudConversation crud = new CrudConversation();

    private Session session;


    public void reload() throws SQLException {
        CRUDMessage crud = new CRUDMessage();
        ObservableList<Message> Message = FXCollections.observableArrayList(crud.afficheMessage(1));

        List<String> Messages = new ArrayList<>();

        for (Message mess : Message) {
            Messages.add("a envoyé: " + mess.getContenu() + "\n à " + mess.getDateEnvoi() + " " + mess.getId());
        }

        //System.out.print(conversationNames);
        ObservableList<String> observableList = FXCollections.observableArrayList(Messages);

        //(Message.size());
        messageList.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        // Create a HBox to hold the left and right strings
                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.setSpacing(10.0);

                        // Create a label for the left string
                        Label leftLabel = new Label(crud.getSenderName(1));
                        leftLabel.setStyle("-fx-font-weight: bold;");

                        // Create a label for the right string
                        Label rightLabel = new Label(item);

                        // Get the index of the last space character in the item string
                        int lastSpaceIndex = item.lastIndexOf(' ');

                        // If a space character was found, make the substring starting from the space character invisible
                        if (lastSpaceIndex >= 0) {
                            String visibleText = item.substring(0, lastSpaceIndex + 1);
                            String invisibleText = item.substring(lastSpaceIndex + 1);
                            rightLabel.setText(visibleText);
                            rightLabel.setStyle("-fx-background-color: transparent;");
                            Label invisibleLabel = new Label(invisibleText);
                            invisibleLabel.setTextFill(Color.TRANSPARENT);
                            hBox.getChildren().addAll(leftLabel, rightLabel, invisibleLabel);
                        } else {
                            hBox.getChildren().addAll(leftLabel, rightLabel);
                        }

                        // Set the HBox as the cell's graphic
                        setGraphic(hBox);
                    } catch (SQLException ex) {
                        Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        messageList.setItems(observableList);
    }

    public void reloadC() throws SQLException {
        // call method from crudConversation to retrieve conversations
        ObservableList<Conversation> conversations = FXCollections.observableArrayList(crud.getConversationsByUserId(1));
        List<String> conversationNames = new ArrayList<>();

        for (Conversation conversation : conversations) {
            conversationNames.add(conversation.getPseudo2());
        }
        // System.out.print(conversationNames);
        ObservableList<String> observableList = FXCollections.observableArrayList(conversationNames);

        conversationList.setItems(observableList);
        conversationList.setCellFactory(param -> new ListCell<String>() {
            private final ImageView imageView = new ImageView();
            private final Label label = new Label();
            private final Button button = new Button("Open");
            private final HBox hbox = new HBox(imageView, label, button);

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    label.setText(item);
               //     imageView.setImage(new Image("img/man.png"));
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);

                    button.setOnAction(event -> {
                        try {

                            showMessage(item);
                        } catch (SQLException ex) {
                            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    setGraphic(hbox);
                }
            }
        });
    }

    @FXML
    public void loadConversations(javafx.scene.input.MouseEvent event) throws SQLException {
        reloadC();
    }

    @FXML
    private void handleDeleteConversation() throws SQLException {
        // Get the selected item from the ListView
        String selectedItem = conversationList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Show a confirmation dialog to ensure the user really wants to delete the conversation
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Supprimer Conversation");
            alert.setHeaderText(null);
            alert.setContentText("Etes vous sûr de supprimer cette conversation?");
            Optional<ButtonType> result = alert.showAndWait();

            // If the user clicked OK, delete the conversation
            if (result.isPresent() && result.get() == ButtonType.OK) {
                CrudConversation crud = new CrudConversation();
                crud.supprimerConversation(selectedItem);
                reload();
            }
        } else {
            // Show an alert if no conversation is selected
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionnez une conversation.");
            alert.showAndWait();
        }
        reloadC();
    }



    @FXML
    public void handleListViewDoubleClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String selectedItem = messageList.getSelectionModel().getSelectedItem();
            messageList.edit(messageList.getSelectionModel().getSelectedIndex());
        }
    }


    @FXML
    private void download() {

        // Show a file chooser dialog to let the user select where to save the file
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(messageList.getScene().getWindow());

        if (file != null) {

            try {
                // Open a FileWriter on the selected file and write the ListView contents to it
                FileWriter writer = new FileWriter(file);
                ObservableList<String> items = messageList.getItems();
                for (String item : items) {
                    writer.write(item + System.lineSeparator());
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void showMessage(String c) throws SQLException {

        reload();

    }


    @FXML
    private void handleEditConversation() throws SQLException {
        // Get the selected item from the ListView
        String selectedItem = conversationList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Create a TextInputDialog to allow the user to edit the message
            TextInputDialog dialog = new TextInputDialog(selectedItem);
            dialog.setTitle("Modifier le pseudonyme");
            dialog.setHeaderText(null);
            dialog.setContentText("Veuillez modifier le pseudonyme:");

            // Show the dialog and wait for the user's input
            Optional<String> result = dialog.showAndWait();

            // If the user clicked OK and the new message is not empty, update the ListView with the new message
            result.ifPresent(newMessage -> {
                if (!newMessage.isEmpty()) {

                    CrudConversation crud = new CrudConversation();
                    try {
                        crud.modifierConversation(selectedItem, newMessage);
                    } catch (SQLException ex) {
                        Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        reloadC();
                    } catch (SQLException ex) {
                        Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    // Show an alert if the new message is empty
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Le message ne doit pas être vide.");
                    alert.showAndWait();
                }
            });
        }
    }

    @FXML
    private void handleEditMessage() throws SQLException {
        // Get the selected item from the ListView
        MultipleSelectionModel<String> selectionModel = messageList.getSelectionModel();
        String selectedItem = selectionModel.getSelectedItem();
        int id = Integer.parseInt(selectedItem.substring(selectedItem.lastIndexOf(' ') + 1));
        if (selectedItem != null) {
            String selectedMessage = selectedItem.substring(0, selectedItem.lastIndexOf(' ') + 1);
            int startIndex = selectedMessage.indexOf(":") + 2; // add 2 to skip the space after colon
            int endIndex = selectedMessage.indexOf(" à");

            String extractedString = selectedMessage.substring(startIndex, endIndex);
            // Create a TextInputDialog to allow the user to edit the message
            TextInputDialog dialog = new TextInputDialog(extractedString);
            dialog.setTitle("Modifier Message");
            dialog.setHeaderText(null);
            dialog.setContentText("Veuillez modifier votre message:");

            // Show the dialog and wait for the user's input
            Optional<String> result = dialog.showAndWait();

            // If the user clicked OK and the new message is not empty, update the ListView with the new message
            result.ifPresent(newMessage -> {
                if (!newMessage.isEmpty()) {
                    CRUDMessage crud = new CRUDMessage();
                    try {
                        crud.modifierMessage(id, newMessage);
                    } catch (SQLException ex) {
                        Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        reload();
                    } catch (SQLException ex) {
                        Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    // Show an alert if the new message is empty
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Message ne doit pas être vide!");
                    alert.showAndWait();
                }
            });
        }
    }

    @FXML
    public void sendMessage(javafx.scene.input.MouseEvent event) throws IOException, SQLException {
        String messageContent = messageField.getText();
        if (messageContent.isEmpty()) {

            // Show an alert if the message field is empty
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Message vide.");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un message.");
            alert.showAndWait();
            return;
        }
        LocalDate currentDate = LocalDate.now();
        Date date = Date.valueOf(currentDate);
        /*modified ici*/
        Message message = new Message(null, EtatMsg.ENVOYE, TypeMsg.TEXTE, messageContent, 1, 1, 2);
        CRUDMessage crud = new CRUDMessage();
        crud.ajouterMessage(message);
        messageField.clear();
        reload();
        
        /*System.out.println("Connexion réussie!");
        String recipient = "khitmiaziz@gmail.com";
        try {
            utils.Mail.envoyer(recipient);
            System.out.println("Le message a été envoyé avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
            e.printStackTrace();
        }*/

    }

    /****
     *
     * SEARCH
     **/


    public void initialize(URL url, ResourceBundle rb) {

    }



    public void mailfonction(javafx.scene.input.MouseEvent event) {
        try {


            String Object = "from your system";
            String Corps = "new message";



            javaMail.sendMail("malek.benabbes1@esprit.tn", Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //music method

    private static AudioStream audioStream;

    public static void playMusic(String filepath) {
        try {
            InputStream music = new FileInputStream(new File(filepath));
            audioStream = new AudioStream(music);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public static void stopMusic() {
        AudioPlayer.player.stop(audioStream);
        try {
            audioStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void btnHome (ActionEvent event) {
        playMusic("C:\\Users\\21692\\Downloads\\thniti\\Sound.wav");

    }

    public void btnStop(ActionEvent event) {
        stopMusic();
    }

    public void statistiqueAction(ActionEvent action) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/StatisticsView.fxml"));

        Stage window =(Stage) statistique.getScene().getWindow();
        window.setScene(new Scene(root, 1064, 600));
    }

    @FXML
    public void deleteMessage(javafx.scene.input.MouseEvent mouseEvent) throws SQLException {
        CRUDMessage crud = new CRUDMessage();
        MultipleSelectionModel<String> selectionModel = messageList.getSelectionModel();
        String selectedItem = selectionModel.getSelectedItem();
        if (selectedItem != null) {
            crud.supprimerMessage(Integer.parseInt(selectedItem.substring(selectedItem.lastIndexOf(' ') + 1)));
            reload();
        }
    }
    @FXML
    public void btnactio(ActionEvent action) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));

        Stage window =(Stage) statistique.getScene().getWindow();
        window.setScene(new Scene(root, 1064, 600));
    }


}
