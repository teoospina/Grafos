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

    private int[][] radioAccionMatriz;
    LinkedList<Patrulla> patrullas;
    int idEstacion;

    public EstacionDePolicia(int[][] radioAccionMatriz, LinkedList<Patrulla> patrullas) {
        this.radioAccionMatriz = radioAccionMatriz;
        crearPatrulla();
    }

    public EstacionDePolicia(int[][] radioAccionMatriz) {
        this.radioAccionMatriz = radioAccionMatriz;
        crearPatrulla();
        this.idEstacion = cuentaEstacion;
        cuentaEstacion += 1;
    }

    public EstacionDePolicia() {
    }
    
    

    private void crearPatrulla() {
        int Random = (int) (Math.random() * 3) + 1;
        for (int i = 0; i < Random; i++) {
            this.patrullas.add(new Patrulla());
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
    public void setRadioAccionMatriz(int[][] radioAccionMatriz) {
        this.radioAccionMatriz = radioAccionMatriz;
    }

}
