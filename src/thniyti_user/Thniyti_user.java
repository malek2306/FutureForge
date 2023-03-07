/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniyti_user;

import entities.User;
import entities.Admin;
import service.ServiceUser;
import util.Data;
/**
 *
 * @ayachizakaria
 */
public class Thniyti_user {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Admin a = new Admin("admin","Abdelaziz","sa@sa","123");
        ServiceUser sp = new ServiceUser();
        
        //sp.ajouter(u2);
        //sp.supprimer(5);
        //update
        User pm = new User(40,"tests","test","test","test@test.test","123","Q4D+0YqIj+WcZQML6kUbrT35HdTE0cP+OJAn4YxtBik=");
        sp.modifier(pm);
        
        //affichage
        //sp.getAll();
        System.out.println(sp.getOneByUsername("wawa123"));
    }
    
}
