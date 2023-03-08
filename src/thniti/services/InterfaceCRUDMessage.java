package thniti.services;

import thniti.entities.Message;
import javafx.collections.ObservableList;
import thniti.entities.Message;

import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author
 */
public interface InterfaceCRUDMessage {
    

    void ajouterMessage(Message message) throws SQLException;

    public void modifierMessage(int id, String message) throws SQLException;

    public void supprimerMessage(int id) throws SQLException;

    public  ObservableList<Message> afficheMessage(int idConversation) throws SQLException;
    
}
