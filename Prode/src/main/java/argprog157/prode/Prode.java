
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

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Argentina Programa 4.0 - Desarrollador JAVA inicial Comisión 157 - Grupo 5 - Trabajo Integrador");
        System.out.println("--------------------------------------------------------");
        System.out.println("Primer paso antes de trabajar, en el desktop, hacer un fetch, si hay cambios en el proyecto, hacer un pull"); 
        System.out.println("--------------------------------------------------------");
        System.out.println("Cuando se termina de trabajar, guardar, cerrar, y en el desktop, \nhacer un add si se crearon archivos nuevos, luego commit agregando comentarios sino no deja, y luego el push"); //agregado pro Marcelo Ranzani
        
        
        
        // Obtenemos los datos del archivo PRONOSTICO
        ArrayList<String[]> datos = new ArrayList<String[]>(); //Creamos un ArrayList de Arrays de Strings
        
        LeerArchivo archivoProno = new LeerArchivo("src\\main\\java\\argprog157\\prode\\pronostico.csv", ";"); //Instanciamos un objeto del tipo LEERARCHIVO
        datos = archivoProno.devolverDatos(); //Ponemos en el ArrayList de Arrays de Strings el ArraysList de Strings que devuleve el metodo devolverDatos de la clase LEERARCHIVO
        
         ArrayList<Pronostico> misPronosticos = new ArrayList<Pronostico>();
        
        CSVtoProno cvsProno = new CSVtoProno(datos);
        misPronosticos = cvsProno.getArrayListProno();
        
        
        
        // Obtenemos los datos del archivo RESULTADOS
        datos.clear();    //Reutilizamos la lista creada anteriormente
                
        LeerArchivo archivoResul = new LeerArchivo("src\\main\\java\\argprog157\\prode\\resultados.csv", ";");
        datos = archivoResul.devolverDatos();
        
        
        
        
       
        
        System.out.println("------------------------------------------------------");
        System.out.println("Comprobar si carga los datos");
        System.out.println("------------------------------------------------------");
        
        System.out.println("------------------------------------------------------");
        
        for (Pronostico misPronos : misPronosticos) {
            System.out.println("Id Equipo 1: " + misPronos.eq1Id);
            System.out.println("Gana Equipo 1: " + misPronos.gana1);
            System.out.println("Empatan: " + misPronos.empatan);
            System.out.println("Gana Equipo 2: " + misPronos.gana2);
            System.out.println("Id Equipo 2: " + misPronos.eq2Id);
            System.out.println("------------------------");
        }
    }
}