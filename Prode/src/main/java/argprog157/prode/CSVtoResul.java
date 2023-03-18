package argprog157.prode;

import java.util.ArrayList;

/**
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class CSVtoResul {
    
    ArrayList<String[]> datos = new ArrayList<String[]>();
    ArrayList<Resultado> misResultados = new ArrayList<Resultado>();
    
    public CSVtoResul (ArrayList<String[]> datos ){
        this.datos = datos;
    }
    
    public ArrayList<Resultado> getArrayListResul() {
        
        for (String[] fila : this.datos) {
            Resultado resul1 = new Resultado();
            resul1.setEq1Id(Integer.parseInt(fila[0]));
            resul1.setGolesEq1(Integer.parseInt(fila[3]));
            resul1.setGolesEq2(Integer.parseInt(fila[4]));
            resul1.setEq2Id(Integer.parseInt(fila[5]));
            if(resul1.getEq1Id() < resul1.getEq2Id()) {
                resul1.setResultadoId("" + resul1.getEq1Id() + resul1.getEq2Id());
            } else {
                resul1.setResultadoId("" + resul1.getEq2Id() + resul1.getEq1Id());
            }
            
            this.misResultados.add(resul1); //Agregamos un elemento del tipo RESULTADO al ArrayList
        }
        return this.misResultados;
    }
}