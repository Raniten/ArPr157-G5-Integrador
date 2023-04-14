package argprog157.prode;

import java.util.ArrayList;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5 - 
 */
public class CSVtoProno {

    private ArrayList<String[]> datos = new ArrayList<>() ;
    private ArrayList<Pronostico> misPronosticos = new ArrayList<>();

    public CSVtoProno(ArrayList<String[]> datos) {
        this.datos = datos;
    }

    public ArrayList<Pronostico> getArrayListProno() {

        for (String[] fila : this.datos) {
            Pronostico prono1 = new Pronostico();

            prono1.setEq1Id(Integer.parseInt(fila[2]));
            if (fila[3].equals("X")) {
                prono1.setGana1(true);
            }
            if (fila[4].equals("X")) {
                prono1.setEmpatan(true);
            }
            if (fila[5].equals("X")) {
                prono1.setGana2(true);
            }
            prono1.setEq2Id(Integer.parseInt(fila[6]));
            
            prono1.setPersonaId(Integer.parseInt(fila[0]));
            prono1.setRondaId(Integer.parseInt(fila[1]));

            if (prono1.getEq1Id() < prono1.getEq2Id()) {  //El ID del equipo 1 es menor al ID del equipo 2
                prono1.setPronosticoId(String.format(prono1.getRondaId() + "%02d", prono1.getEq1Id()) + String.format("%02d", prono1.getEq2Id()));
            } else {
                prono1.setPronosticoId(String.format(prono1.getRondaId() + "%02d", prono1.getEq2Id()) + String.format("%02d", prono1.getEq1Id()));
            }


            this.misPronosticos.add(prono1); //Agregamos un elemento del tipo PRONOSTICO al ArrayList
        }

        return this.misPronosticos;
    }
}
