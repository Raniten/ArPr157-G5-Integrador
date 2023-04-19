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
            //para verificar equipos habria que agregar 
            //algo asi: String nombEquipo1=fila[3];
            //if(!nombEquipo1.matches(([A-Z]{1})([A-Z]{1,2})?([a-z]\s)?[a-z]*+\s?[A-Z]{0,1}?[a-z]*?)){
            //throw new IllegalArgumentException("El nombre del equipo no es válido ");}
            String goles1comp=fila[4];
            if(!goles1comp.matches("[0-9]")){
            throw new IllegalArgumentException("Los goles del equipo 1 no son un número válido ");}
            resul1.setGolesEq1(Integer.parseInt(fila[4]));
            String goles2comp=fila[5];
            if(!goles2comp.matches("[0-9]")){
            throw new IllegalArgumentException("Los goles del equipo 2 no son un número válido ");}
            resul1.setGolesEq2(Integer.parseInt(fila[5]));
            resul1.setEq2Id(Integer.parseInt(fila[6]));
            if (resul1.getEq1Id() < resul1.getEq2Id()) {  //El ID del equipo 1 es menor al ID del equipo 2
                resul1.setResultadoId(String.format(resul1.getRondaId() + "%02d", resul1.getEq1Id()) + String.format("%02d", resul1.getEq2Id()));
            } else {
                resul1.setResultadoId(String.format(resul1.getRondaId() + "%02d", resul1.getEq2Id()) + String.format("%02d", resul1.getEq1Id()));
            }

            this.misResultados.add(resul1); //Agregamos un elemento del tipo RESULTADO al ArrayList
        }
        return this.misResultados;
    }
}
