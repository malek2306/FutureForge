/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.entities;

/**
 *
 * @author
 */
public class Conversation {
    private int id;
    private int senderId;
    private int receiverId;
    private String pseudo1;
    private String pseudo2;

    // Constructor

    public Conversation(int senderId, int receiverId, String pseudo1, String pseudo2) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.pseudo1 = pseudo1;
        this.pseudo2 = pseudo2;
    }

    public Conversation(int id, int senderId, int receiverId, String pseudo1, String pseudo2) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.pseudo1 = pseudo1;
        this.pseudo2 = pseudo2;
    }

    public Conversation(int id, String pseudo2) {
        this.id = id;
        this.pseudo2 = pseudo2;
    }


    // Getters and setters
    public int getId() {
        return id;
    }
    
    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getPseudo1() {
        return pseudo1;
    }

    public String getPseudo2() {
        return pseudo2;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public void setPseudo1(String pseudo1) {
        this.pseudo1 = pseudo1;
    }

    public void setPseudo2(String pseudo2) {
        this.pseudo2 = pseudo2;
    }
    
    

 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conversation other = (Conversation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conversation{" + "id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", pseudo1=" + pseudo1 + ", pseudo2=" + pseudo2 + '}';
    } 
}
