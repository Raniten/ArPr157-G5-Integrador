
package argprog157.prode;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class Persona {
    private int personaId;
    private String apellido;
    private String nombres;
    private int puntajeTotal;

    public Persona(int personaId, String apellido, String nombres, int puntajeTotal) {
        this.personaId = personaId;
        this.apellido = apellido;
        this.nombres = nombres;
        this.puntajeTotal = puntajeTotal;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }
    
    
    
}
