package argprog157.prode;

import java.util.ArrayList;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class Puntaje {
    private ArrayList<Pronostico> misPronosticos;
    private ArrayList<Resultado> misResultados;
    private int puntaje = 0;
    
    public Puntaje(ArrayList<Pronostico> misPronosticos, ArrayList<Resultado> misResultados) {
        this.misPronosticos = misPronosticos;
        this.misResultados = misResultados;
    }
    
    public int calcularPuntaje() {
        for (Pronostico prono : misPronosticos) {
            for (Resultado resul : misResultados) {
                if(prono.getPronosticoId().equals(resul.getResultadoId())) {
                    if(resul.getGolesEq1() == resul.getGolesEq2()) { //EMPATAN
                        if(prono.isEmpatan()){
                            puntaje++;
                        }
                        break;
                    } else if(resul.getGolesEq1() > resul.getGolesEq2()){
                        if(prono.isGana1()){
                            puntaje++;
                        }
                        break;
                    } else {
                        if(prono.isGana2()) {
                            puntaje++;
                        }
                        break;
                    }
                }
            }
            
        }
        
        
        
        return puntaje;
        
    }
}
