
package argprog157.prode;

/**
 *
 * @author Argentina Programa - Comisión 157-2023 - Grupo 5
 */
public class Pronostico {
    private String pronosticoId;
    private int eq1Id;
    private boolean gana1; 
    private boolean empatan;
    private boolean gana2;
    private int eq2Id;
    
    public String getPronosticoId() {
        return pronosticoId;
    }

    public void setPronosticoId(String pronosticoId) {
        this.pronosticoId = pronosticoId;
    }

    public int getEq1Id() {
        return eq1Id;
    }

    public void setEq1Id(int eq1Id) {
        this.eq1Id = eq1Id;
    }

    public boolean isGana1() {
        return gana1;
    }

    public void setGana1(boolean gana1) {
        this.gana1 = gana1;
    }

    public boolean isEmpatan() {
        return empatan;
    }

    public void setEmpatan(boolean empatan) {
        this.empatan = empatan;
    }

    public boolean isGana2() {
        return gana2;
    }

    public void setGana2(boolean gana2) {
        this.gana2 = gana2;
    }

    public int getEq2Id() {
        return eq2Id;
    }

    public void setEq2Id(int eq2Id) {
        this.eq2Id = eq2Id;
    }
    
    
    
}
