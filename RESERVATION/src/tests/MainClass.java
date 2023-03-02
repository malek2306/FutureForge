/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import API.SendSMS;
import entities.Reservation_bus;
import entities.Reservation_covoiturage;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import services.ServiceReservationBus;
import services.ServiceReservationCovoiturage;


/**
 *
 * @author LENOVO
 */
public class MainClass {
    
    public static void main(String[] args)  {  
        //Reservation_covoiturage r1 = new Reservation_covoiturage("MALEK", "moumou", "ii" ,"tunis",25,"2023-10-10");
       Reservation_bus r2 = new Reservation_bus(33,"g", "g" ,10,"gg","aa","g");
    ServiceReservationBus sp = new ServiceReservationBus();
    ServiceReservationCovoiturage sp1 = new ServiceReservationCovoiturage();
  // sp.ajouter(r2);
   //sp1.supprimer(26);
  //sp.modifier1(r2);
      //  ServiceReservationCovoiturage sp1 = new ServiceReservationCovoiturage();
//Reservation_bus r = new Reservation_bus("alooooo","test",1234);
   // sp.ajouter(r);
    //sp.supprimer(8);
    //sp.modifier1(r);
 //System.out.println(sp.getAll());
  //sp.getOneById(5);
    
        SendSMS sm = new SendSMS();
        sm.sendSMS(r2);
    
    
    
    }}
