
package abonnementFX.Util;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
public class MaConnexion {
       
    final static String URL ="jdbc:mysql://localhost:3306/thnity";
    final static String USERNAME="root";
    final static String PWD="";
    
    private Connection cnx;
    
    static MaConnexion instance= null;
    

    public MaConnexion() {
        try {
            
            cnx=DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("connected");
        } catch (SQLException ex) {
            ex.printStackTrace();}
            
        
    }
    
    

    public Connection getCnx() {
        return cnx;
    }


    public static MaConnexion getInstance() {
        if(instance==null){
            instance = new MaConnexion();
        }
        return instance;
    }
    
    }
    
    

