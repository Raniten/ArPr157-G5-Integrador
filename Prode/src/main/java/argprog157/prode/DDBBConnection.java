package argprog157.prode;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DDBBConnection {
    
    Connection con = null;
    String usuario = "raniten";
    String pass = "vale2512";
    String dBase = "prode";
    String ip = "localhost";
    String puerto = "3306";
        
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + dBase;
    
    public Connection ConnectToDDBB () {
        try {
            
            //Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cadena, usuario, pass);
            JOptionPane.showMessageDialog(null, "Conexión realizada");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se conectó a la base de datos. Error: " + e.toString());
        }
        return con;
    }
    
}
