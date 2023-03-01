/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.evenement.test;
import edu.evenement.entities.Evenement;
import edu.evenement.entities.Categories;
import edu.evenement.services.Serviceevenement;
import edu.evenement.services.Servicecategories ;
import edu.evenement.utils.DataSource;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author farah
 */
public class MainClass {
    public static void main(String[] args) throws SQLException  {
       
    
     Categories c2= new Categories (25,"roua","el","hammemi");
      Servicecategories sc = new Servicecategories();
         //sc.ajouter(c2);
          
     // sc.supprimer(65);
     // System.out.println(sc.getAll());
      //System.out.println(sc.getOneById(22));
     // sc.ajouter(c2); 

Evenement e2 = new Evenement (1,"farah", "toumi", "ben", "hassen",c2);
Serviceevenement se = new Serviceevenement();
//se.ajouter(e2);
//se.modifier(e1);

//se.afficherEvenement();
     // Evenement e2= new Evenement(74,"ga","far","sss","fkf", 53);
//System.out.println(se.getAll());
 //System.out.println(se.getOneById(74));


                 //System.out.println(se.afficherEvenement()) ;
    
      
     
     
    }
     }
        
       
        

        
    

