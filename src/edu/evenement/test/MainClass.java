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
import java.time.LocalDate;
import java.time.Month;
import java.util.List;



/**
 *
 * @author farah
 */
public class MainClass {
    public static void main(String[] args) throws SQLException  {
       
    byte[] photo = new byte[0];
     Categories c2= new Categories (28,"roua","el",photo);
     Servicecategories sc = new Servicecategories();
        sc.ajouter(c2);
          
     // sc.supprimer(65);
     // System.out.println(sc.getAll());
      //System.out.println(sc.getOneById(22));
     // sc.ajouter(c2); 

Evenement e2 = new Evenement(28, "farah", "toumi","ezzzzz", LocalDate.of(1998, 4, 6), c2);
Serviceevenement se = new Serviceevenement();
//se.ajouter(e2);
//se.modifier(e1);

//se.afficherEvenement();
     //Evenement e2= new Evenement(74,"ga","far","sss","fkf", 53);
//System.out.println(se.getAll());
 //System.out.println(se.getOneById(55));


                 //System.out.println(se.afficherEvenement()) ;
    
      
     
     
    }
     }
        
       
        

        
    

