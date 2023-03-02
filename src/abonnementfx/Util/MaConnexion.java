
package abonnementFX.Util;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
public class MaConnexion {
       //BD 
    final static String URL ="jdbc:mysql://localhost:3306/thnity";
    final static String USERNAME="root";
    final static String PWD="";
    //att
    private Connection cnx;
    //singleton#1
    static MaConnexion instance= null;
    //constructor
    //singleton#2 :private

    private MaConnexion() {
        try {
            
            cnx=DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("cnx etablie");
        } catch (SQLException ex) {
            ex.printStackTrace();}
            
        
    }
    
    //getters setters

    public Connection getCnx() {
        return cnx;
    }
//singleton#3 

    public static MaConnexion getInstance() {
        if(instance==null){
            instance = new MaConnexion();
        }
        return instance;
    }
    
    }
    
    

