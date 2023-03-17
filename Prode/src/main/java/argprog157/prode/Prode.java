
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
        
        /* Creamos un ArrayList cuyos componentes son Arrays de Strings,
        donde guardaremos cada uno de los renglones del archivo PRONOSTICO.CSV*/
        
        ArrayList<String[]> datos = new ArrayList<String[]>();
        try {
            File archivo = new File("src\\main\\java\\argprog157\\prode\\pronostico.csv");
            System.out.println(archivo.getAbsolutePath());
            datos = Prode.LeerArchivo(archivo);
        } catch (IOException ex) {
            Logger.getLogger(Prode.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < datos.size(); i++) {
            System.out.println(datos.get(i));
        }
        System.out.println(datos.toString());
        
        //Creamos un arrayList de objetos PRONOSTICO
        ArrayList<Pronostico> misPronosticos = new ArrayList<Pronostico>();
        
        for (String[] fila : datos) {
            Pronostico prono1 = new Pronostico();
            
            prono1.setEq1Id(Integer.parseInt(fila[0]));
            if(fila[1].equals("X")) {
                prono1.setGana1(true);
            }
            if(fila[2].equals("X")) {
                prono1.setEmpatan(true);
            }
            if(fila[3].equals("X")) {
                prono1.setGana2(true);
            }
            prono1.setEq2Id(Integer.parseInt(fila[4]));
            
            misPronosticos.add(prono1); //Agregamos un elemento del tipo PRONOSTICO al ArrayList
        }

    }

    static ArrayList<String[]> LeerArchivo (File archivo) throws FileNotFoundException, IOException {
        
        String separadorCSV = ";";
        ArrayList<String[]> datos = new ArrayList<String[]>();

        
        Scanner sc = new Scanner(archivo);
        while (sc.hasNext()) {
            String linea = sc.nextLine();
            String[] fila = linea.split(separadorCSV);
            datos.add(fila);
        }
        datos.remove(0); //Eliminamos la cabecera, son datos que no necesitamos
        return datos;
    }
}