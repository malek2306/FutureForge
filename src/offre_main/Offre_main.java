/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package offre_main;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import offre.entities.Offre;
import offre.utils.DataSource;
import offre.services.ServiceOffre;
import java.util.Date;

/**
 *
 * @author Firas
 */
public class Offre_main {
    public static void main(String[] args){
        /*Offre O1 = new Offre(3,3,3,3,3,3,3,3);*/
            Offre O2 = new Offre("hfhfhgffg","ghada","firasss","valentines","16h","hhh","fgh",5,40);
             Offre O3 = new Offre("atch","ghada","firasss","valentines","16h","hhgggh","fgh",5,40);
         
        ServiceOffre so = new ServiceOffre();
       
        /*so.ajouter_offre(O3);*/
   /*so.modifier_offre(O3);*/
    /*so.supprimer_offre(1);*/
    System.out.println(so.getAll());
}
}