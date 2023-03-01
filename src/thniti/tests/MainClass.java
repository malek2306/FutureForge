/*newwwwwwwwwwwwwwwwwwwwwwwwwww
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.tests;
import thniti.entities.Reclamation;
//import thniti.entities.Avis;
import thniti.services.ServiceReclamation;
//import thniti.services.ServiceAvis;
import thniti.utils.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import thniti.entities.Messagerie;
import thniti.entities.User;
import thniti.services.ServiceMessagerie;
/**
 *
 * @author 21692
 */
public class MainClass {
    public static void main(String[] args) throws ParseException {
    String s = "2000-06-08";
    User u= new User();
    u.setId_u(6);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = sdf.parse(s);
   
    
         //Reclamation r1 = new Reclamation("l"," k ","rec",date,"jjjj",3);

         Reclamation r2 = new Reclamation("d", "d"," d",date,"d");
         
        Messagerie m1 =  new Messagerie (6,"Mfrjhbmmm","Lll",u);
        
       
        ServiceReclamation sr = new ServiceReclamation();
        ServiceMessagerie sm = new ServiceMessagerie();
       
      //sm.modifier(m1,1);   //done
       sm.supprimer(m1);   //done
        //sr.modifier(r1,30);  //done
        
       try {
  //sr.ajouter(r2);done
  //sm.ajouter(m1); //done
             System.out.println(sr.getAll());//appeler la méthode getAll

  
} catch (IllegalArgumentException ex) {
    System.out.println(ex.getMessage());
       
        
     
         
        
        
         //sm.getAll().toString() ;
             
       
       
    }
    }}
