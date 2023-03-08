package thniti.services;

import thniti.entities.Conversation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thniti.utils.DataSource;

import java.sql.*;

public class CrudConversation implements InterfaceCrudConversation {

    Connection PrestaFindDB = DataSource.getInstance().getCnx();

    @Override
    public void ajouterConversation(int senderId, int receiverId) throws SQLException {
        String query = "INSERT INTO conversation (idexpediteur , idrecipient) VALUES (?, ?)";
        try (PreparedStatement statement = PrestaFindDB.prepareStatement(query)) {
            statement.setInt(1, senderId);
            statement.setInt(2, receiverId);
            statement.executeUpdate();
        }
    }

    @Override
    public Conversation getConversationById(int Id) throws SQLException {
        String query = "SELECT * FROM conversation WHERE id = ?";
        try (PreparedStatement statement = PrestaFindDB.prepareStatement(query)) {
            statement.setInt(1, Id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int senderId = resultSet.getInt("idexpediteur ");
                    int receiverId = resultSet.getInt("idrecipient");
                    String pseudo1 = resultSet.getString("pseudo1");
                    String pseudo2 = resultSet.getString("pseudo2");
                    return new Conversation(Id, senderId, receiverId, pseudo1, pseudo2);
                }
            }
        }
        return null;
    }

    @Override
    public ObservableList<Conversation> getConversationsByUserId(int userId) throws SQLException {
        ObservableList<Conversation> conversations = FXCollections.observableArrayList();
        String query = "SELECT * FROM conversation WHERE idexpediteur = ? ";
        try (PreparedStatement statement = PrestaFindDB.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int conversationId = resultSet.getInt("id");
                    int senderId = resultSet.getInt("idexpediteur");
                    int receiverId = resultSet.getInt("idrecipient");
                    String pseudo1 = resultSet.getString("pseudo1");
                    String pseudo2 = resultSet.getString("pseudo2");
                    Conversation conversation = new Conversation(conversationId, senderId, receiverId, pseudo1, pseudo2);
                    conversations.add(conversation);
                }
            }
        }
        return conversations;
    }

    @Override
    public void modifierConversation(String selectedItem, String New) throws SQLException {
        String query = "UPDATE conversation SET pseudo2 = ? WHERE pseudo2 = ?";
        try (PreparedStatement statement = PrestaFindDB.prepareStatement(query)) {
            statement.setString(1, New);
            statement.setString(2, selectedItem);
            statement.executeUpdate();
        }
    }

    @Override
    public void supprimerConversation(String Id) throws SQLException {
        String query = "DELETE FROM conversation WHERE pseudo1 = ? OR pseudo2 = ?";
        try (PreparedStatement statement = PrestaFindDB.prepareStatement(query)) {
            statement.setString(1, Id);
            statement.setString(2, Id);
            statement.executeUpdate();
        }
    }

    @Override
    public void afficheConversation(int Id) throws SQLException {
        String query = "SELECT * FROM message WHERE id = ?";
        try (PreparedStatement statement = PrestaFindDB.prepareStatement(query)) {
            statement.setInt(1, Id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int messageId = resultSet.getInt("message_id");
                    int senderId = resultSet.getInt("sender_id");
                    String content = resultSet.getString("content");
                    Timestamp timestamp = resultSet.getTimestamp("timestamp");
                    System.out.printf("Message %d (sent by user %d at %s): %s\n", messageId, senderId, timestamp.toString(), content);
                }
            }
        }
    }

}
