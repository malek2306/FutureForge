/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thniti.entities;

import java.time.LocalDateTime;
/**
 *
 * @author
 */
public class Message {
    private int id;
    private LocalDateTime dateEnvoi;
    private EtatMsg etat;
    private TypeMsg type_m;
    private String contenu;
    private int idconversation;
    private int idexpediteur;
    private int idrecipient;

    public Message(LocalDateTime dateEnvoi, EtatMsg etat, TypeMsg type_m, String contenu, int idconversation, int idexpediteur, int idrecipient) {
        this.dateEnvoi = dateEnvoi;
        this.etat = etat;
        this.type_m = type_m;
        this.contenu = contenu;
        this.idconversation = idconversation;
        this.idexpediteur = idexpediteur;
        this.idrecipient = idrecipient;
    }

    public Message(int id, LocalDateTime dateEnvoi, EtatMsg etat, TypeMsg type_m, String contenu, int idconversation, int idexpediteur, int idrecipient) {
        this.id = id;
        this.dateEnvoi = dateEnvoi;
        this.etat = etat;
        this.type_m = type_m;
        this.contenu = contenu;
        this.idconversation = idconversation;
        this.idexpediteur = idexpediteur;
        this.idrecipient = idrecipient;
    }
    

    public int getId() {
        return id;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public EtatMsg getEtat() {
        return etat;
    }

    public TypeMsg getType_m() {
        return type_m;
    }

    public String getContenu() {
        return contenu;
    }

    public int getIdconversation() {
        return idconversation;
    }

    public int getIdexpediteur() {
        return idexpediteur;
    }

    public int getIdrecipient() {
        return idrecipient;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public void setEtat(EtatMsg etat) {
        this.etat = etat;
    }

    public void setType_m(TypeMsg type_m) {
        this.type_m = type_m;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setIdconversation(int idconversation) {
        this.idconversation = idconversation;
    }

    public void setIdexpediteur(int idexpediteur) {
        this.idexpediteur = idexpediteur;
    }

    public void setIdrecipient(int idrecipient) {
        this.idrecipient = idrecipient;
    }


    
    public enum EtatMsg {
        ENATTENTE,
        ENVOYE,
        LU,
        SUPPRIME
    }

    public enum TypeMsg {
        TEXTE,
        FICHIER
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
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
        final Message other = (Message) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", dateEnvoi=" + dateEnvoi + ", etat=" + etat + ", type_m=" + type_m + ", contenu=" + contenu + ", idconversation=" + idconversation + ", idexpediteur=" + idexpediteur + ", idrecipient=" + idrecipient + '}';
    }

    
    
    
    
    
    
}