package argprog157.prode;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class LeerArchivo {
    String nombreArchivo;
    String separadorCSV = ";";
    
    public LeerArchivo (String nombreArchivo, String separadorCSV) {
        this.nombreArchivo = nombreArchivo;
        this.separadorCSV = separadorCSV;
    }
    
    public ArrayList<String[]> devolverDatos () throws FileNotFoundException, IOException {
        
        ArrayList<String[]> datos = new ArrayList<>();
        File archivo = new File(this.nombreArchivo);
        
        Files.readAllLines(Paths.get(this.nombreArchivo));
        for (String linea : Files.readAllLines(Paths.get(this.nombreArchivo))) {
            String[] fila = linea.split(this.separadorCSV);
            datos.add(fila);
            
        }
        datos.remove(0); //Eliminamos la cabecera, son datosProno que no necesitamos
        return datos;
    }
    
}
