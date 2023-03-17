package argprog157.prode;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class Equipo {
    int eqId;
    String eqNombre;
    String eqDescripcion;

    public Equipo(int eqId, String eqNombre, String eqDescripcion) {
        this.eqId = eqId;
        this.eqNombre = eqNombre;
        this.eqDescripcion = eqDescripcion;
    }

    public int getEqId() {
        return eqId;
    }

    public void setEqId(int eqId) {
        this.eqId = eqId;
    }

    public String getEqNombre() {
        return eqNombre;
    }

    public void setEqNombre(String eqNombre) {
        this.eqNombre = eqNombre;
    }

    public String getEqDescripcion() {
        return eqDescripcion;
    }

    public void setEqDescripcion(String eqDescripcion) {
        this.eqDescripcion = eqDescripcion;
    }
    
    
    
}
