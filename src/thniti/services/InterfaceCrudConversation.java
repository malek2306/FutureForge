package thniti.services;

import thniti.entities.Conversation;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceCrudConversation {

    public void ajouterConversation(int senderId, int receiverId) throws SQLException;

    public Conversation getConversationById(int Id) throws SQLException;
    
    public List<Conversation> getConversationsByUserId(int userId) throws SQLException;
            
    public void modifierConversation(String selectedItem,String New) throws SQLException;

    public void supprimerConversation(String Id) throws SQLException;

    public void afficheConversation(int Id) throws SQLException;

}
