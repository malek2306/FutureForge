/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Reservation_bus;

/**
 *
 * @author LENOVO
 */
public class SendSMS {
    
    public static final String ACCOUNT_SID = System.getenv("AC0b065bb9ba457e183a39d09c13170b40");
    public static final String AUTH_TOKEN = System.getenv("a19f5ee3e18872333a12059072e26bc2");

    public static void sendSMS(Reservation_bus r) {
        Twilio.init("AC0b065bb9ba457e183a39d09c13170b40", "a19f5ee3e18872333a12059072e26bc2");
        Message message = Message.creator(new PhoneNumber("+21694392948"),
                new PhoneNumber("+12765308592"),
                "reservation ajouté: nom: "+r.getNom()+" prenom: "+r.getPrenom()+" destination: "+r.getDestination()).create();


        System.out.println(message.getSid());
    }
    
}
