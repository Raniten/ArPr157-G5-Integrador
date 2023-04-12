package argprog157.prode;

import java.util.ArrayList;

/**
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class CSVtoResul {

    private ArrayList<String[]> datos = new ArrayList<String[]>();
    private ArrayList<Resultado> misResultados = new ArrayList<Resultado>();

    public CSVtoResul(ArrayList<String[]> datos) {
        this.datos = datos;
    }

    public ArrayList<Resultado> getArrayListResul() {

        for (String[] fila : this.datos) {
            Resultado resul1 = new Resultado();
            resul1.setRondaId(Integer.parseInt(fila[0]));
            resul1.setEq1Id(Integer.parseInt(fila[1]));
            resul1.setGolesEq1(Integer.parseInt(fila[4]));
            resul1.setGolesEq2(Integer.parseInt(fila[5]));
            resul1.setEq2Id(Integer.parseInt(fila[6]));
            if (resul1.getEq1Id() < resul1.getEq2Id()) {  //El ID del equipo 1 es menor al ID del equipo 2
                resul1.setResultadoId(String.format("%02d", resul1.getEq1Id()) + String.format("%02d", resul1.getEq2Id()));
            } else {
                resul1.setResultadoId(String.format("%02d", resul1.getEq2Id()) + String.format("%02d", resul1.getEq1Id()));
            }

            this.misResultados.add(resul1); //Agregamos un elemento del tipo RESULTADO al ArrayList
        }
        return this.misResultados;
    }
}
