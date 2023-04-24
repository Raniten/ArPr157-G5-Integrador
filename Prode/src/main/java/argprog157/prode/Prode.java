package argprog157.prode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class Prode {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Persona[] personas;
        int cantPersonasEnTablaPersonas = 0;
        int cantRondasEnResultados = 0;
        int cantPersonasPonosticos = 0;
        int cantRondasPorPersona = 0;
        int cantRondasPorPersonaEnPonosticos = 0;
        //int variable;

        String peopleTable = args[5];
        String teamsTable = args[6];
        String resultsTable = args[7];
        String predictionsTable = args[8];
        float puntosPorAcierto = Float.parseFloat(args[9]);
        float incrementoPorRondaCompleta = Float.parseFloat(args[10]);

        /*
         ***********************************************************
         * verificamos si al cantidad de parámetros es la correcta
         ***********************************************************
         */
        if (args.length != 11) {
            JOptionPane.showMessageDialog(null, "Cantidad de argumentos inválida", "Error en argumentos", ERROR_MESSAGE);
        }

        String myStatement;
        Statement st;
        ResultSet rs;
        ArrayList<Resultado>[] resultadosPorRonda;

        /*
         * *****************************************************************************
         * CONECTAMOS A LA BASE DE DATOS
         *******************************************************************************
         */
        DDBBConnection myConnection = new DDBBConnection(args[0], args[1], args[2], args[3], args[4]);
        Connection DDBBConnect = myConnection.ConnectToDDBB();

        //Si no se pudo conectar a la base de datos
        if (DDBBConnect == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. PROGRAMA TERMINADO ", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }


        /*
        ******************************************************************************
        Vamos a crear un ArrayList<Resultado> por cada ronda distinta en la tabla "prode.resultados"
        ******************************************************************************
         */
        myStatement = "Select count(distinct Ronda) from " + resultsTable + ";";

        try {
            st = DDBBConnect.createStatement();
            rs = st.executeQuery(myStatement);
            if (rs.next()) {
                cantRondasEnResultados = rs.getInt(1);
                //JOptionPane.showMessageDialog(null, "Cantidad de rondas distintas que componen la tabla RESULTADOS: " + cantRondasEnResultados);
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
                myStatement = "Select * from " + resultsTable + " WHERE (Ronda = " + (i + 1) + ");";
                st = DDBBConnect.createStatement();
                rs = st.executeQuery(myStatement);
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
        myStatement = "Select count(distinct PersonasId) from " + predictionsTable + ";";

        try {
            st = DDBBConnect.createStatement();
            rs = st.executeQuery(myStatement);
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
        for (int i = 0; i < cantPersonasPonosticos; i++) {
            float puntajePorPersona = 0.0f;
            //Para cada persona vamos a obtener la cantidad de rondas por las que hizo pronósticos
            myStatement = "SELECT COUNT(DISTINCT Ronda) FROM " + predictionsTable + " WHERE (PersonasId = " + (i + 1) + ");";
            try {
                st = DDBBConnect.createStatement();
                rs = st.executeQuery(myStatement);
                if (rs.next()) {
                    cantRondasPorPersonaEnPonosticos = rs.getInt(1);
                    JOptionPane.showMessageDialog(null, "Cantidad de rondas para las que la persona " + (i + 1) + " ha hecho pronósticos es: " + cantRondasPorPersonaEnPonosticos);
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

            float puntajePorRonda = 0.0f;
            //resultadosPorRonda[i].clear();
            for (int j = 0; j < cantRondasPorPersonaEnPonosticos; j++) {
                //Consultamos a la tabla PRONSOTICOS y traemos todos los datos de la ronda[j] de la persona[i]
                myStatement = "Select * from " + predictionsTable + " WHERE (Ronda = " + (j + 1) + " and PersonasId = " + (i + 1) + ");";
                try {
                    st = DDBBConnect.createStatement();
                    rs = st.executeQuery(myStatement);
                    SQLtoProno sqlProno = new SQLtoProno(rs);
                    pronosticosPorRonda[j] = sqlProno.getArrayListProno();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error con al tabla PRONOSTICOS: " + e.toString(), "Error en tabla PRONOSTICO", ERROR_MESSAGE);
                }

                // Vamos a calcular el puntaje para cada persona por cada ronda
                Puntaje misPuntajes = new Puntaje(pronosticosPorRonda[j], resultadosPorRonda[j]);
                puntajePorRonda = misPuntajes.calcularPuntaje(puntosPorAcierto, incrementoPorRondaCompleta);
                
                //Acumulamos el puntaje de cada Persona de todas las rondas
                puntajePorPersona = puntajePorPersona + puntajePorRonda;

            }
            String sentenciaSelectPersonas = "SELECT * FROM " + peopleTable + " WHERE (id = " + (i+1) + ");";
            myStatement = "UPDATE " + peopleTable + " SET Puntos = " + puntajePorPersona + " WHERE (id = " + (i + 1) + ");";
            try {
                st = DDBBConnect.createStatement();
                st.executeUpdate(myStatement);
//                if(resultadoUpdate == 1) {
//                    JOptionPane.showMessageDialog(null, "Se modificó satisfactoriamente la base de datos con el puntaje de la persona " + (i+1), "Operación exitosa", INFORMATION_MESSAGE);
//                }
                st = DDBBConnect.createStatement();
                rs = st.executeQuery(sentenciaSelectPersonas);
                if (rs.next()) {
                System.out.println("-------------------------------------------------");
                System.out.println("| Puntaje Total de " + rs.getString(3) + " " + rs.getString(2) + ":    " + puntajePorPersona);
                System.out.println("-------------------------------------------------");
                    
                }
               
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo realizar el UPDATE. Error: " + e.toString(), "Error de UPDATE", ERROR_MESSAGE);
            }
            
        }
    }
}
