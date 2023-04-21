package argprog157.prode;

//import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class Prode {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        int cantRondasEnResultados = 0;
        int cantPersonasPonosticos = 0;
        int cantRondasPorPersona = 0;
        int cantRondasPorPersonaEnPonosticos = 0;
        String sentencia = "";
        Statement st;
        ResultSet rs;

        //System.out.println(args[0]);
        //System.out.println(args[1]);
        DDBBConnection myConnection = new DDBBConnection();
        Connection DDBBConnect = myConnection.ConnectToDDBB();

        //Si no se pudo conectar a la base de datos
        if (DDBBConnect == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. PROGRAMA TERMINADO ", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        //Se pudo conectar a la base de datos.
        
        //Vamos a crear un ArrayList<Resultado> por cada ronda distinta en la tabla "prode.resultados"
        sentencia = "Select count(distinct Ronda) from resultados;"; 
        try {
            st = DDBBConnect.createStatement();
            rs = st.executeQuery(sentencia);
            if (rs.next()) {
                cantRondasEnResultados = rs.getInt(1);
                JOptionPane.showMessageDialog(null, "Cantidad de rondas distintas que componen la tabla RESULTADOS: " + cantRondasEnResultados);
            }
            
            /*
            Vamos a crear un array, sus elementos son ArrayList de objetos RESULTADO, 
            de un tamaño igual a la cantidad de rondas que tiene la tabla RESULTADOS
            En este array, en definitiva, estarán todos los resultados, divididios por RONDA
            */
            
            ArrayList<Resultado>[] resultadosPorRonda = new ArrayList[cantRondasEnResultados];
            for (int i = 0; i < cantRondasEnResultados; i++) {
                sentencia = "Select * from resultados WHERE (Ronda = " + (i+1) + ");";
                rs = st.executeQuery(sentencia);
                SQLtoResul sqlResul = new SQLtoResul(rs);
                resultadosPorRonda[i] = sqlResul.getArrayListResul();
                System.out.println(i);
                
            }
            

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        
        
        //Vamos a contar la cantidad de personas que han hecho pronósticos
        sentencia = "Select count(distinct PersonasId) from pronosticos;";
                
        try {
            st = DDBBConnect.createStatement();
            rs = st.executeQuery(sentencia);
            if (rs.next()) {
                cantPersonasPonosticos = rs.getInt(1);
                JOptionPane.showMessageDialog(null, "Cantidad de personas distintas que han hecho pronósticos: " + cantPersonasPonosticos);
            }
            

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        
        //Vamos a hacer un ciclo por cada persona distinta que haya hecho un pronóstico
        
        for (int i = 1; i <= cantPersonasPonosticos; i++) {
            //Para cada persona vamos a obtener la cantidad de rondas por las que hizo pronósticos
            sentencia = "SELECT COUNT(DISTINCT Ronda) FROM pronosticos WHERE (PersonasId = " + i + ");";
            try {
                st = DDBBConnect.createStatement();
                rs = st.executeQuery(sentencia);
                if (rs.next()) {
                cantRondasPorPersonaEnPonosticos = rs.getInt(1);
                JOptionPane.showMessageDialog(null, "Cantidad de rondas para las que la persona " + i + " ha hecho pronósticos es: " + cantRondasPorPersonaEnPonosticos);
            }
                
            } catch (Exception e) {
            }
            
            
        }

        // Obtenemos los datos del archivo PRONOSTICO
        //texto de prueba
        ArrayList<String[]> datos = new ArrayList<>(); //Creamos un ArrayList de Arrays de Strings

        //LeerArchivo archivo = new LeerArchivo("src\\main\\java\\argprog157\\prode\\pronostico.csv", ";"); //Instanciamos un objeto del tipo LEERARCHIVO
        LeerArchivo archivo = new LeerArchivo(args[0], ";");  //Instanciamos un objeto del tipo LEERARCHIVO

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

        //-------------------------------------------------------------------------------------------
        // * * * * * * * * * * * VAMOS A CALCULAR LOS PUNTAJES, POR CADA PERSONA Y POR CADA RONDA
        // PARA ESO VAMOS A CONTAR LA CANTIDAD DE PERSONAS DISTITNAS Y LA CANTIDAD DE RONDAS DISTINTAS
        //-------------------------------------------------------------------------------------------
        HashSet<Integer> personasIds = new HashSet<>();
        HashSet<Integer> rondasIds = new HashSet<>();

        // Iterar sobre cada objeto Pronostico del ArrayList
        for (Pronostico p : misPronosticos) {

            // Agregar el valor de personaId al HashSet de personasIds. Sólo agrega valores distintos
            personasIds.add(p.getPersonaId());

            // Agregar el valor de rondaId al HashSet de rondasIds. Sólo agrega valores distintos
            rondasIds.add(p.getRondaId());
        }

        // Obtener la cantidad de valores únicos en cada conjunto
        int cantidadPersonaIds = personasIds.size();
        int cantidadRondaIds = rondasIds.size();

        //Vamos a calcular el puntaje de cada PERSONA, y por cada RONDA
        //Creamos un ArrayList para crear las listas filtradas
        ArrayList<Pronostico> pronosticosFiltrados = new ArrayList<Pronostico>();
        Puntaje misPuntajes;
        int puntajePorPersona;
        int puntajePorRonda;

        for (Integer persId : personasIds) {
            puntajePorPersona = 0;
            for (Integer rondId : rondasIds) {
                pronosticosFiltrados.clear(); //blanqueamos la lista filtrada
                for (Pronostico p : misPronosticos) {
                    // Verificar si el objeto cumple con los criterios de filtrado
                    if (persId == p.getPersonaId() && rondId == p.getRondaId()) {
                        // Si cumple, agregar el objeto al ArrayList de objetos filtrados
                        pronosticosFiltrados.add(p);
                    }
                }
                misPuntajes = new Puntaje(pronosticosFiltrados, misResultados);
                puntajePorRonda = misPuntajes.calcularPuntaje();
                System.out.println("*************************************************");
                System.out.println("*                Puntaje Ronda " + rondId + ":    " + puntajePorRonda + "          *");
                System.out.println("*************************************************");
                puntajePorPersona = puntajePorPersona + puntajePorRonda;
            }

            System.out.println("------------------------------------------------------");
            System.out.println("|           Puntaje TOTAL Persona " + persId + ":  " + puntajePorPersona + "             |");
            System.out.println("------------------------------------------------------");
        }
    }
}