
package argprog157.prode;


//import java.io.FileNotFoundException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
        
        //-------------------------------------------------------------------------------------------
        
        System.out.println(args[0]);
        System.out.println(args[1]);
        
        
        // Obtenemos los datos del archivo PRONOSTICO
        ArrayList<String[]> datos = new ArrayList<>(); //Creamos un ArrayList de Arrays de Strings
        
        //LeerArchivo archivo = new LeerArchivo("src\\main\\java\\argprog157\\prode\\pronostico.csv", ";"); //Instanciamos un objeto del tipo LEERARCHIVO
        LeerArchivo archivo = new LeerArchivo(args[0], ";");
        
        datos = archivo.devolverDatos(); //Ponemos en el ArrayList de Arrays de Strings el ArraysList de Strings que devuleve el metodo devolverDatos de la clase LEERARCHIVO
        
        ArrayList<Pronostico> misPronosticos = new ArrayList<>();
        
        CSVtoProno cvsProno = new CSVtoProno(datos);
        misPronosticos = cvsProno.getArrayListProno();
        
        //-------------------------------------------------------------------------------------------
        
        // Obtenemos los datos del archivo RESULTADOS
        datos.clear();    //Reutilizamos la lista creada anteriormente
                
        //Reutilizamos el objeto "archivo" que es de tipo "LeerArchivo" para leer el archivo RESULTADOS
        //archivo = new LeerArchivo("src\\main\\java\\argprog157\\prode\\resultados.csv", ";");
        archivo = new LeerArchivo(args[1], ";");
        
        datos = archivo.devolverDatos();
        
        ArrayList<Resultado> misResultados = new ArrayList<>();
        
        CSVtoResul cvsResul = new CSVtoResul(datos);
        misResultados = cvsResul.getArrayListResul();
        
        Puntaje misPuntajes = new Puntaje(misPronosticos, misResultados);
        
        System.out.println("*************************************************");
        System.out.println("*                Puntaje: " + misPuntajes.calcularPuntaje() + "                    *");
        System.out.println("*************************************************");
        
        //-------------------------------------------------------------------------------------------
        
//        System.out.println("------------------------------------------------------");
//        System.out.println("Comprobar si carga los datos");
//        System.out.println("------------------------------------------------------");
//        
//        System.out.println("------------------------------------------------------");
//        System.out.println("Pronosticos");
//        System.out.println("------------------------------------------------------");
//        for (Pronostico misPronos : misPronosticos) {
//            System.out.println("Id de Pronostico: " + misPronos.getPronosticoId());
//            System.out.println("Id Equipo 1: " + misPronos.getEq1Id());
//            System.out.println("Gana Equipo 1: " + misPronos.isGana1());
//            System.out.println("Empatan: " + misPronos.isEmpatan());
//            System.out.println("Gana Equipo 2: " + misPronos.isGana2());
//            System.out.println("Id Equipo 2: " + misPronos.getEq2Id());
//            System.out.println("------------------------");
//        }
//        
//        System.out.println("------------------------------------------------------");
//        System.out.println("Resultados");
//        System.out.println("------------------------------------------------------");
//        for (Resultado misResuls : misResultados) {
//            System.out.println("Id Resultado: " + misResuls.getResultadoId());
//            System.out.println("Id Equipo 1: " + misResuls.getEq1Id());
//            System.out.println("Goles Equipo 1: " + misResuls.getGolesEq1());
//            System.out.println("Goles Equipo 2: " + misResuls.getGolesEq2());
//            System.out.println("Id Equipo 2: " + misResuls.getEq2Id());
//            System.out.println("------------------------");
//        }
    }
}