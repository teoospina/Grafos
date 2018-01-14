/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.LinkedList;
import static Vista.VistaPrincipal.cuentaEstacion;

/**
 *
 * @author Mateo
 */
public class EstacionDePolicia {

    /**
     * @param radioAccionMatriz the radioAccionMatriz to set
     */
    public void setRadioAccionMatriz(int[][] radioAccionMatriz) {
        this.radioAccionMatriz = radioAccionMatriz;
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

    private int[][] radioAccionMatriz;
    private LinkedList<Patrulla> patrullas;
    private int idEstacion;

    public EstacionDePolicia(int[][] radioAccionMatriz, LinkedList<Patrulla> patrullas) {
        this.radioAccionMatriz = radioAccionMatriz;
        crearPatrulla();
    }

    public EstacionDePolicia() {
        //this.radioAccionMatriz = radioAccionMatriz;
        //crearPatrulla();
        this.idEstacion = cuentaEstacion;
        cuentaEstacion += 1;
    }

    
    

    private void crearPatrulla() {
        int Random = (int) (Math.random() * 3) + 1;
        for (int i = 0; i < Random; i++) {
            this.getPatrullas().add(new Patrulla());
        }
    }

    /**
     * @return the radioAccionMatriz
     */
    public int[][] getRadioAccionMatriz() {
        return radioAccionMatriz;
    }

    /**
     * @param radioAccionMatriz the radioAccionMatriz to set
     */
    /*  public void setRadioAccionMatriz(int[][] radioAccionMatriz) {
    this.setRadioAccionMatriz(radioAccionMatriz);
    }*/
}
