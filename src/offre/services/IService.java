/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.services;

import java.util.List;

/**
 *
 * @author Firas
 */
public interface IService <T>{
    public void ajouter_offre(T o);
    public void supprimer_offre(int id);
     public void modifier(T o);
      public List<T> getAll();
}
