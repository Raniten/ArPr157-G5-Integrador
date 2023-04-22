package argprog157.prode;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5 - 
 */
public class SQLtoProno {

    private ResultSet datos;
    
    private ArrayList<Pronostico> misPronosticos = new ArrayList<>();

    public SQLtoProno(ResultSet datos) {
        this.datos = datos;
    }

    public ArrayList<Pronostico> getArrayListProno() {

        try {
        while(datos.next()) {
            Pronostico prono1 = new Pronostico();
            
            prono1.setPersonaId(datos.getInt("PersonasId"));
            prono1.setRondaId(datos.getInt("Ronda"));
            prono1.setEq1Id(datos.getInt("Equipo1Id"));
            prono1.setEq2Id(datos.getInt("Equipo2Id"));
            if(datos.getString("GanaEq1").equals("1")) {
                prono1.setGana1(true);
            }
            if(datos.getString("Empatan").equals("1")) {
                prono1.setEmpatan(true);
            }
            if(datos.getString("GanaEq2").equals("1")) {
                prono1.setGana2(true);
            }
            
            if (prono1.getEq1Id() < prono1.getEq2Id()) {  //El ID del equipo 1 es menor al ID del equipo 2
                prono1.setPronosticoId(String.format(prono1.getRondaId() + "%02d", prono1.getEq1Id()) + String.format("%02d", prono1.getEq2Id()));
            } else {
                prono1.setPronosticoId(String.format(prono1.getRondaId() + "%02d", prono1.getEq2Id()) + String.format("%02d", prono1.getEq1Id()));
            }
            this.misPronosticos.add(prono1); //Agregamos un elemento del tipo RESULTADO al ArrayList
        }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede continuar. Error" + e.toString(), "Error", ERROR_MESSAGE);
        }
        return this.misPronosticos;
    }
}