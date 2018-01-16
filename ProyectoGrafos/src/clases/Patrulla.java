/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import java.awt.Rectangle;
import static Vista.VistaPrincipal.matrizInfluencia;

/**
 *
 * @author Mateo
 */
public class Patrulla implements Runnable {

    private int idPatrulla;
    private int fila;
    private int columna;
    private Rectangle areaDisparo;
    private Rectangle areaoAvistamientoAdelante;
    private Rectangle areaoAvistamientoAtras;
    private int indiceInfluencia;
    private Image[][] imagen;

    public Patrulla() {
    }

    public Patrulla(int idPatrulla, int fila, int columna, Rectangle areaDisparo, Rectangle areaoAvistamientoAdelante, Rectangle areaoAvistamientoAtras, int indiceInfluencia, Image[][] imagen) {
        this.idPatrulla = idPatrulla;
        this.fila = fila;
        this.columna = columna;
        this.areaDisparo = areaDisparo;
        this.areaoAvistamientoAdelante = areaoAvistamientoAdelante;
        this.areaoAvistamientoAtras = areaoAvistamientoAtras;
        this.indiceInfluencia = indiceInfluencia;
        this.imagen = imagen;
    }

    /**
     * @return the idPatrulla
     */
    public int getIdPatrulla() {
        return idPatrulla;
    }

    /**
     * @param idPatrulla the idPatrulla to set
     */
    public void setIdPatrulla(int idPatrulla) {
        this.idPatrulla = idPatrulla;
    }

    /**
     * @return the fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public void run() {

    }

}
