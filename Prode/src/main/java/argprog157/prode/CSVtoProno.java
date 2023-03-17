
package argprog157.prode;

import java.util.ArrayList;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class CSVtoProno {
    ArrayList<String[]> datos = new ArrayList<String[]>();
    ArrayList<Pronostico> misPronosticos = new ArrayList<Pronostico>();
    
    public CSVtoProno (ArrayList<String[]> datos ){
        this.datos = datos;
    }
    
    public ArrayList<Pronostico> getArrayListProno() {
        
        for (String[] fila : this.datos) {
            Pronostico prono1 = new Pronostico();
            
            prono1.setEq1Id(Integer.parseInt(fila[0]));
            if(fila[1].equals("X")) {
                prono1.setGana1(true);
            }
            if(fila[2].equals("X")) {
                prono1.setEmpatan(true);
            }
            if(fila[3].equals("X")) {
                prono1.setGana2(true);
            }
            prono1.setEq2Id(Integer.parseInt(fila[4]));
            
            this.misPronosticos.add(prono1); //Agregamos un elemento del tipo PRONOSTICO al ArrayList
        }
        
        return this.misPronosticos;
    }
        
}
