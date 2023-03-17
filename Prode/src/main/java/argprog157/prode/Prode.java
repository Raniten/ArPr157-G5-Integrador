
package argprog157.prode;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class Prode {

    public static void main(String[] args) {
        System.out.println("Argentina Programa 4.0 - Desarrollador JAVA inicial \nComisión 157 - Grupo 5 - Trabajo Integrador");
        System.out.println("hola github");//agregado 14:27hs x Manu Amsler
        System.out.println("java17.0.6, sino, no anda");//agregado 14:27hs x Manu Amsler
        System.out.println("--------------------------------------------------------");
        System.out.println("Primer paso antes de trabajar, \nen el desktop, hacer un fetch, \nsi hay cambios en el proyecto, hacer un pull"); 
        System.out.println("--------------------------------------------------------");
        System.out.println("Cuando se termina de trabajar, guardar, cerrar, \ny en el desktop, hacer un add si se crearon archivos nuevos, luego commit agregando comentarios sino no deja, \ny luego el push"); //agregado pro Marcelo Ranzani
        
        ArrayList<String[]> datos = new ArrayList<String[]>();
        try {
            datos = Prode.LeerArchivo("D:\\ArPr157-G5-Integrador\\Prode\\src\\main\\java\\argprog157\\prode\\resultados.csv");
        } catch (IOException ex) {
            Logger.getLogger(Prode.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < datos.size(); i++) {
            System.out.println(datos.get(i));
        }
        System.out.println(datos.toString());
        

    }

    static ArrayList<String[]> LeerArchivo (String archivo) throws FileNotFoundException, IOException {
        
        String archivoCSV = "ruta/al/archivo.csv";
        Scanner scanner = null;
        String separadorCSV = ";";
        ArrayList<String[]> datos = new ArrayList<String[]>();

        
        scanner = new Scanner(new File(archivo));
        while (scanner.hasNext()) {
            String linea = scanner.nextLine();
            String[] fila = linea.split(separadorCSV);
            datos.add(fila);
        }
        return datos;
    }
}