package argprog157.prode;

import java.util.ArrayList;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class Puntaje {
    private ArrayList<Pronostico> misPronosticos;
    private ArrayList<Resultado> misResultados;
    private float puntaje = 0.0f;
    
    public Puntaje(ArrayList<Pronostico> misPronosticos, ArrayList<Resultado> misResultados) {
        this.misPronosticos = misPronosticos;
        this.misResultados = misResultados;
    }
    
    public float calcularPuntaje(float puntosPorAcierto, float incrementoPorRondaCompleta) {
        int cantAciertos = 0;
        for (Pronostico prono : misPronosticos) {
            for (Resultado resul : misResultados) {
                if(prono.getPronosticoId().equals(resul.getResultadoId())) {
                    if(resul.getGolesEq1() == resul.getGolesEq2()) { //EMPATAN
                        if(prono.isEmpatan()){
                            puntaje+=puntosPorAcierto;
                            cantAciertos++;
                        }
                        break;
                    } else if(resul.getGolesEq1() > resul.getGolesEq2()){
                        if(prono.isGana1()){
                            puntaje+=puntosPorAcierto;
                            cantAciertos++;
                        }
                        break;
                    } else {
                        if(prono.isGana2()) {
                            puntaje+=puntosPorAcierto;
                            cantAciertos++;
                        }
                        break;
                    }
                }
            }
            
        }
        if(cantAciertos == this.misPronosticos.size()) {
            puntaje = puntaje + puntaje * incrementoPorRondaCompleta;
        }
        return puntaje;
    }
}
