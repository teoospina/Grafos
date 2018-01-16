/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import static Vista.VistaPrincipal.cuentaBanco;

/**
 *
 * @author Mateo
 */
public class Banco {
  
    private float dinero;
    private int idBanco;

    public Banco(int dinero ) {
        this.dinero = dinero;
        this.idBanco = cuentaBanco;
        cuentaBanco+=1;
    }
 
    

    /**
     * @return the dinero
     */
    public float getDinero() {
        return dinero;
    }

    /**
     * @param dinero the dinero to set
     */
    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    /**
     * @return the idBanco
     */
    public int getIdBanco() {
        return idBanco;
    }

    /**
     * @param idBanco the idBanco to set
     */
    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }
    
}
