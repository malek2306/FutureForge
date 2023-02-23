/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.Services;

import abonnementFX.Entities.Abonnement;
import java.util.List;


public interface IServicesAbonnement <T>{
    public void ajouter_abonnement(Abonnement a);
    public void modifier_abonnement(String identifiant, Abonnement a);
     public T get_abonnementByIdentifiant(String identifiant);
     public T get_abonnement(String email);
     public List<Abonnement> getall_abonnements();
     public void annuler_abonnement(int id);
     
    
}
