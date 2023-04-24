package argprog157.prode;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */

public class DDBBConnection {
    
    private Connection con = null;
    
    private String dBase;// = "prode";
    private String usuario;// = "argprog";
    private String pass;// = "ArgPrograma1";
    private String ip;// = "localhost";
    private String puerto;// = "3306";

    public DDBBConnection(String dBase, String usuario, String pass, String ip, String puerto) {
        this.dBase = dBase;
        this.usuario = usuario;
        this.pass = pass;
        this.ip = ip;
        this.puerto = puerto;
    }
    
    public Connection ConnectToDDBB () {
        try {
            String cadena = "jdbc:mysql://" + this.ip + ":" + this.puerto + "/" + this.dBase;
            //Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cadena, this.usuario, this.pass);
            JOptionPane.showMessageDialog(null, "Conexión realizada a la base de datos: " + this.dBase.toUpperCase());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se conectó a la base de datos. Error: " + e.toString());
        }
        return con;
    }
}
