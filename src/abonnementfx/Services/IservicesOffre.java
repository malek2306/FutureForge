/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnementfx.Services;

import abonnementFX.Entities.OffreAbonnement;
import java.util.List;


public interface IservicesOffre <T> {
     public void ajouter_offre( T offre);
     public void annuler_offre(int id);
     public void modifier_offre(int id, T offre);
     public List<T> offre_dispo();
     public List<T> allOffres();
     public T findOffre(int id);
}
