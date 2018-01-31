/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static Vista.VistaPrincipal.*;

/**
 *
 * @author Mateo
 */
public class Banco {

    private int dinero;
    private int idBanco;

    public Banco(int dinero) {
        this.dinero = dinero;
        this.idBanco = cuentaBanco;
        cuentaBanco += 1;
    }

    /**
     * @return the dinero
     */
    public int entregarDinero() {
        int monto =dinero;
        dinero -= monto;
       reportarRobo(this);
        return monto;
    }

    public int getDinero() {
        return dinero;
    }

    /**
     * @param dinero the dinero to set
     */
    public void setDinero(int dinero) {
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
