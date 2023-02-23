/*newwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.entities;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
/**
 *
 * @author 21692
 */
public class Reclamation {

    private int id_R;
    private String TypeR;
    private String DescriptionR;
    private String Objet;
    private Date DateR;
    private String etat;
    private int id_u;
    
    
    public Reclamation() {
        
    }

    public Reclamation(int id_R) {
        this.id_R = id_R;
    }

    public Reclamation(String Objet,String TypeR, String DescriptionR,String etat) {
        this.TypeR = TypeR;
        this.DescriptionR = DescriptionR;
        this.Objet = Objet;
        this.etat = etat;
    }
    
    /*public Reclamation(String TypeR, String DescriptionR, String Objet, Date DateR, String etat,int id_u) {
        this.TypeR = TypeR;
        this.DescriptionR = DescriptionR;
        this.Objet = Objet;
        this.DateR = DateR;
        this.etat = etat;
        this.id_u = id_u;       
        
    }*/
    public Reclamation(String typeR, String descriptionR, String objet, Date dateR, String etat, int id_R) {
    this.TypeR = typeR;
    this.DescriptionR = descriptionR;
    this.Objet = objet;
    this.DateR = dateR;
    this.etat = etat;
    this.id_R = id_R;
}

    public Reclamation(String type, String description, String objet, Date localDate, String Etat) {
        this.TypeR = type;
        this.DescriptionR = description;
        this.Objet = objet;
        this.DateR = localDate;
        this.etat = Etat;        
    }
    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }        
    public int getId_R() {
        return id_R;
    }

    public void setId_R(int id_R) {
        this.id_R = id_R;
    }

    public String getTypeR() {
        return TypeR;
    }

    public void setTypeR(String TypeR) {
        this.TypeR = TypeR;
    }

    public String getDescriptionR() {
        return DescriptionR;
    }

    public void setDescriptionR(String DescriptionR) {
        this.DescriptionR = DescriptionR;
    }

    public String getObjet() {
        return Objet;
    }

    public void setObjet(String Objet) {
        this.Objet = Objet;
    }

    public Date getDateR() {
        return DateR;
    }

    public void setDateR(Date DateR) {
        this.DateR = DateR;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    
    


    @Override
    public String toString() {
        return "Reclamation{" + "TypeR=" + TypeR + ", DescriptionR=" + DescriptionR + ", Objet=" + Objet + ", DateR=" + DateR + ", etat=" + etat + ", id_u=" + id_u + '}';
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
        final Reclamation other = (Reclamation) obj;
        if (this.id_R != other.id_R) {
            return false;
        }
        
        return true;
    }
    
    
   
    
    
    }
    

