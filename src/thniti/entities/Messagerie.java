/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.entities;

import javafx.scene.control.TextField;

/**
 *
 * @author 21692
 */
public class Messagerie {
    private int id_M;
    private String contenuM;
    private String etat;

    public Messagerie() {
    }

    public Messagerie(String contenuM, String etat) {
        this.contenuM = contenuM;
        this.etat = etat;
    }

    public Messagerie(TextField contenuM, TextField etat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_M() {
        return id_M;
    }

    public String getContenuM() {
        return contenuM;
    }

    public void setContenuM(String contenuM) {
        this.contenuM = contenuM;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Messagerie{" + "contenuM=" + contenuM + ", etat=" + etat + '}';
    }
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Messagerie other = (Messagerie) obj;
        if (this.id_M != other.id_M) {
            return false;
        }
        
        return true;
    }
    
}
