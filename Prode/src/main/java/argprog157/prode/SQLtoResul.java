package argprog157.prode;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class SQLtoResul {

    private ResultSet datos;
    
    private ArrayList<Resultado> misResultados = new ArrayList<>();

    public SQLtoResul(ResultSet datos) {
        this.datos = datos;
    }

    public ArrayList<Resultado> getArrayListResul() {
        
        try {
        while(datos.next()) {
            Resultado resul1 = new Resultado();
            resul1.setRondaId(datos.getInt("Ronda"));
            resul1.setEq1Id(datos.getInt("Equipo1Id"));
            resul1.setGolesEq1(datos.getInt("Equipo1Goles"));
            resul1.setGolesEq2(datos.getInt("Equipo2Goles"));
            resul1.setEq2Id(datos.getInt("Equipo2Id"));
            
            if (resul1.getEq1Id() < resul1.getEq2Id()) {  //El ID del equipo 1 es menor al ID del equipo 2
                resul1.setResultadoId(String.format(resul1.getRondaId() + "%02d", resul1.getEq1Id()) + String.format("%02d", resul1.getEq2Id()));
            } else {
                resul1.setResultadoId(String.format(resul1.getRondaId() + "%02d", resul1.getEq2Id()) + String.format("%02d", resul1.getEq1Id()));
            }
            this.misResultados.add(resul1); //Agregamos un elemento del tipo RESULTADO al ArrayList
        }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede continuar. Error" + e.toString(), "Error", ERROR_MESSAGE);
        }
        return this.misResultados;
    }
}
