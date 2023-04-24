package argprog157.prode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

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
    
    Properties myProperties = new Properties();
    
    public DDBBConnection(String rutaArchivoProperties) {
        try {
            myProperties.load(new BufferedReader(new FileReader(rutaArchivoProperties)));
            
            System.out.println(myProperties.getProperty("db.name"));
            System.out.println(myProperties.getProperty("db.user"));
            
            this.dBase = myProperties.getProperty("db.name");
            this.usuario = myProperties.getProperty("db.user");
            this.pass = myProperties.getProperty("db.pass");
            this.ip = myProperties.getProperty("db.ip");
            this.puerto = myProperties.getProperty("db.port");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo de configuración: " + rutaArchivoProperties, "Error en argumentos", ERROR_MESSAGE);
        }
        
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
