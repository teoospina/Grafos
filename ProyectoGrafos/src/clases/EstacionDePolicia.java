/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.LinkedList;
import static Vista.VistaPrincipal.cuentaEstacion;
import static Vista.VistaPrincipal.matrizInfluencia;

/**
 *
 * @author Mateo
 */
public class EstacionDePolicia {

    private LinkedList<Patrulla> patrullas;
    private int idEstacion;

    public EstacionDePolicia() {
        patrullas = new LinkedList<>();
        idEstacion = cuentaEstacion;
        cuentaEstacion += 1;
    }

    public EstacionDePolicia(LinkedList<Patrulla> patrullas, int idEstacion) {
        this.patrullas = patrullas;
        idEstacion = cuentaEstacion;
        cuentaEstacion += 1;
    }

    /**
     * @return the patrullas
     */
    public LinkedList<Patrulla> getPatrullas() {
        return patrullas;
    }

    /**
     * @param patrullas the patrullas to set
     */
    public void setPatrullas(LinkedList<Patrulla> patrullas) {
        this.patrullas = patrullas;
    }

    /**
     * @return the idEstacion
     */
    public int getIdEstacion() {
        return idEstacion;
    }

    /**
     * @param idEstacion the idEstacion to set
     */
    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public void crearPatrulla(int numero) {
        for (int i = 0; i < numero; i++) {
            this.getPatrullas().add(new Patrulla());
        }
    }
}
