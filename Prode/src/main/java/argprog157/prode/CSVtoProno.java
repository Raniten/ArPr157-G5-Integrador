package argprog157.prode;

import java.util.ArrayList;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class CSVtoProno {

    ArrayList<String[]> datos = new ArrayList<>();
    ArrayList<Pronostico> misPronosticos = new ArrayList<>();

    public CSVtoProno(ArrayList<String[]> datos) {
        this.datos = datos;
    }

    public ArrayList<Pronostico> getArrayListProno() {

        for (String[] fila : this.datos) {
            Pronostico prono1 = new Pronostico();

            prono1.setEq1Id(Integer.parseInt(fila[0]));
            if (fila[1].equals("X")) {
                prono1.setGana1(true);
            }
            if (fila[2].equals("X")) {
                prono1.setEmpatan(true);
            }
            if (fila[3].equals("X")) {
                prono1.setGana2(true);
            }
            prono1.setEq2Id(Integer.parseInt(fila[4]));

            if (prono1.getEq1Id() < prono1.getEq2Id()) {  //El ID del equipo 1 es menor al ID del equipo 2
                if (prono1.getEq1Id() < 10) {
                    if (prono1.getEq2Id() < 10) {
                        prono1.setPronosticoId("0" + prono1.getEq1Id() + "0" + prono1.getEq2Id());
                    } else {
                        prono1.setPronosticoId("0" + prono1.getEq1Id() + prono1.getEq2Id());
                    }
                } else if (prono1.getEq2Id() < 10) {
                    prono1.setPronosticoId("" + prono1.getEq1Id() + "0" + prono1.getEq2Id());
                } else {
                    prono1.setPronosticoId("" + prono1.getEq1Id() + prono1.getEq2Id());
                }
            } else if (prono1.getEq2Id() < 10) {  //El ID del segundo equipo es menor al ID del primer equipo, están al revés
                if (prono1.getEq1Id() < 10) {
                    prono1.setPronosticoId("0" + prono1.getEq2Id() + "0" + prono1.getEq1Id());
                } else {
                    prono1.setPronosticoId("0" + prono1.getEq2Id() + prono1.getEq1Id());
                }
            } else if (prono1.getEq1Id() < 10) {
                prono1.setPronosticoId("" + prono1.getEq2Id() + "0" + prono1.getEq1Id());
            } else {
                prono1.setPronosticoId("" + prono1.getEq2Id() + prono1.getEq1Id());
            }

            this.misPronosticos.add(prono1); //Agregamos un elemento del tipo PRONOSTICO al ArrayList
        }

        return this.misPronosticos;
    }
}
