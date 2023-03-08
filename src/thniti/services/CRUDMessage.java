/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thniti.services;

import thniti.entities.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thniti.utils.DataSource;

import java.sql.*;
import java.time.LocalDateTime;

/**
 *
 * @author zizou
 */
public class CRUDMessage implements InterfaceCRUDMessage {

    Connection PrestaFindDB = DataSource.getInstance().getCnx();

    @Override
    public void ajouterMessage(Message message) throws SQLException {
        String sql = "INSERT INTO message(date, etat, type_m, contenu, idconversation, idexpediteur, idrecipient) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = PrestaFindDB.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setObject(1, message.getDateEnvoi());
            stmt.setString(2, message.getEtat().name());
            stmt.setString(3, message.getType_m().name());
            stmt.setString(4, message.getContenu());
            stmt.setInt(5, message.getIdconversation());
            stmt.setInt(6, message.getIdexpediteur());
            stmt.setInt(7, message.getIdrecipient());
            int nbrLignes = stmt.executeUpdate();
            if (nbrLignes == 0) {
                throw new SQLException("Message non ajouté");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    message.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Message non ajouté.");
                }
            }
        }
    }

    @Override
    public void modifierMessage(int id, String message) throws SQLException {
        String sql = "UPDATE message SET contenu = ? WHERE id = ?";
        try (PreparedStatement stmt = PrestaFindDB.prepareStatement(sql)) {
            stmt.setString(1, message);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void supprimerMessage(int id) throws SQLException {
        String sql = "DELETE FROM message WHERE id = ?";
        try (PreparedStatement stmt = PrestaFindDB.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int nbrLignes = stmt.executeUpdate();
            if (nbrLignes == 0) {
                throw new SQLException("Aucune ligne n'a été supprimée.");
            }
        }
    }

    @Override
    public ObservableList<Message> afficheMessage(int idConversation) throws SQLException {
        ObservableList<Message> messages = FXCollections.observableArrayList();
        String sql = "SELECT m.* FROM message m, conversation c where c.id = ?";
        try (PreparedStatement stmt = PrestaFindDB.prepareStatement(sql)) {
            stmt.setInt(1, idConversation);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    /*modified ici*/
//                    LocalDateTime dateEnvoi = rs.getObject("date", LocalDateTime.class);
                    Message.EtatMsg etat = Message.EtatMsg.valueOf(rs.getString("etat"));
                    Message.TypeMsg type_m = Message.TypeMsg.valueOf(rs.getString("type_m"));
                    String contenu = rs.getString("contenu");
                    int idConversationn = rs.getInt("idconversation");
                    int idExpediteur = rs.getInt("idexpediteur");
                    int idRecipient = rs.getInt("idrecipient");
                    Message message = new Message(id, null, etat, type_m, contenu, idConversationn, idExpediteur, idRecipient);
                    messages.add(message);

                }
            }
            return messages;
        }

    }

    public String getSenderName(int senderId) throws SQLException {
        String sql = "SELECT pseudo2 FROM conversation WHERE id = ?";
        /*try (PreparedStatement stmt = PrestaFindDB.prepareStatement(sql)) {
            stmt.setInt(1, senderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("sender_name");
                } else {
                    throw new SQLException("No conversation found with sender ID: " + senderId);
                }
            }
        }*/
        return ("meriam");
    }

}
