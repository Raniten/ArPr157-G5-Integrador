package argprog157.prode;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
    
    public ArrayList<String[]> devolverDatos () throws FileNotFoundException {
        ArrayList<String[]> datos = new ArrayList<>();
        File archivo = new File(this.nombreArchivo);
        
        Scanner sc = new Scanner(archivo);
        while (sc.hasNext()) {
            String linea = sc.nextLine();
            String[] fila = linea.split(this.separadorCSV);
            datos.add(fila);
        }
        datos.remove(0); //Eliminamos la cabecera, son datosProno que no necesitamos
        sc.close();  //Cerramos ela rchivo utilizado
        return datos;
    }
}
