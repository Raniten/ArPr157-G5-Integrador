package argprog157.prode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
        int variable;
        String sentencia = "";
        Statement st;
        ResultSet rs;
        ArrayList<Resultado>[] resultadosPorRonda;

        /*
        ******************************************************************************
        CONECTAMOS A LA BASE DE DATOS
        ******************************************************************************
         */
        DDBBConnection myConnection = new DDBBConnection();
        Connection DDBBConnect = myConnection.ConnectToDDBB();

        //Si no se pudo conectar a la base de datos
        if (DDBBConnect == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. PROGRAMA TERMINADO ", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        //Se pudo conectar a la base de datos.
        
        
        /*
        ******************************************************************************
        Vamos a crear un ArrayList<Resultado> por cada ronda distinta en la tabla "prode.resultados"
        ******************************************************************************
         */
        sentencia = "Select count(distinct Ronda) from resultados;";
        try {
            st = DDBBConnect.createStatement();
            rs = st.executeQuery(sentencia);
            if (rs.next()) {
                cantRondasEnResultados = rs.getInt(1);
                JOptionPane.showMessageDialog(null, "Cantidad de rondas distintas que componen la tabla RESULTADOS: " + cantRondasEnResultados);
            }

            /**
             * ***********************************************************************
             * Vamos a crear un array, sus elementos son ArrayList de objetos
             * RESULTADO, de un tamaño igual a la cantidad de rondas que tiene
             * la tabla RESULTADOS En este array, en definitiva, estarán todos
             * los resultados, divididios por RONDA Ronda 1 =
             * resultadosPorRonda[0] Ronda 2 = resultadosPorRonda[1] etc...
             * *************************************************************************
             */
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        resultadosPorRonda = new ArrayList[cantRondasEnResultados];
        try {

            for (int i = 0; i < cantRondasEnResultados; i++) {
                resultadosPorRonda[i] = new ArrayList<Resultado>();
                sentencia = "Select * from resultados WHERE (Ronda = " + (i + 1) + ");";
                st = DDBBConnect.createStatement();
                rs = st.executeQuery(sentencia);
                SQLtoResul sqlResul = new SQLtoResul(rs);
                resultadosPorRonda[i] = sqlResul.getArrayListResul();
            }
        } catch (Exception e) {
        }

        /**
         * ******************************************************************
         * Vamos a contar la cantidad de personas que han hecho pronósticos
         * ******************************************************************
         */
        sentencia = "Select count(distinct PersonasId) from pronosticos;";

        try {
            st = DDBBConnect.createStatement();
            rs = st.executeQuery(sentencia);
            if (rs.next()) {
                cantPersonasPonosticos = rs.getInt(1);
                JOptionPane.showMessageDialog(null, "Cantidad de personas distintas que han hecho pronósticos: " + cantPersonasPonosticos);
            }
            //rs.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        /**
         * ***************************************************************************
         * Vamos a hacer un ciclo por cada persona distinta que haya hecho un
         * pronóstico
         * ***************************************************************************
         */
        int puntajePorPersona = 0;

        for (int i = 0; i < cantPersonasPonosticos; i++) {
            //Para cada persona vamos a obtener la cantidad de rondas por las que hizo pronósticos
            sentencia = "SELECT COUNT(DISTINCT Ronda) FROM pronosticos WHERE (PersonasId = " + (i+1) + ");";
            try {
                st = DDBBConnect.createStatement();
                rs = st.executeQuery(sentencia);
                if (rs.next()) {
                    cantRondasPorPersonaEnPonosticos = rs.getInt(1);
                    JOptionPane.showMessageDialog(null, "Cantidad de rondas para las que la persona " + (i+1) + " ha hecho pronósticos es: " + cantRondasPorPersonaEnPonosticos);
                }
                rs.close();

            } catch (Exception e) {
            }

            /**
             * ***********************************************************************
             * Vamos a crear un array, sus elementos son ArrayList de objetos
             * PRONOSTICO, de un tamaño igual a la cantidad de rondas que tiene
             * la tabla PRONOSTICOS por cada Persona. En este array, en
             * definitiva, estarán todos los pronosticos, divididios por RONDA
             * Ronda 1 = pronosticosPorRonda[0] Ronda 2 = pronosticosPorRonda[1]
             * etc...
             * **********************************************************************
             */
            ArrayList<Pronostico>[] pronosticosPorRonda = new ArrayList[cantRondasPorPersonaEnPonosticos];

            int puntajePorRonda = 0;
            //resultadosPorRonda[i].clear();
            for (int j = 0; j < cantRondasPorPersonaEnPonosticos; j++) {
                //Consultamos a la tabla PRONSOTICOS y traemos todos los datos de la ronda[j] de la persona[i]
                sentencia = "Select * from pronosticos WHERE (Ronda = " + (j + 1) + " and PersonasId = " + (i + 1) + ");";
                try {
                    st = DDBBConnect.createStatement();
                    rs = st.executeQuery(sentencia);
                    SQLtoProno sqlProno = new SQLtoProno(rs);
                    pronosticosPorRonda[j] = sqlProno.getArrayListProno();
                    System.out.println(i);
                } catch (Exception e) {
                }

                // Vamos a calcular el puntaje para cada persona por cada ronda
                Puntaje misPuntajes = new Puntaje(pronosticosPorRonda[j], resultadosPorRonda[j]);
                puntajePorRonda = misPuntajes.calcularPuntaje();
                System.out.println("*************************************************");
                System.out.println("*                Puntaje Ronda " + (j + 1) + ":    " + puntajePorRonda + "          *");
                System.out.println("*************************************************");
                puntajePorPersona = puntajePorPersona + puntajePorRonda;
            }
        }
    }
}
