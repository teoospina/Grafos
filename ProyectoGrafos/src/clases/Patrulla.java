/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;

/**
 *
 * @author Mateo
 */
public class Patrulla implements Runnable{

    private int idPatrulla;
    private int fila;
    private int columna;
    private int radioDisparo;
    private int radioAvistamiento;
    private int indiceFila;
    private Image [][] imagen;

    public Patrulla() {
        this.imagen = new Image[4][3];
    }

    public Patrulla(int idPatrulla, int fila, int columna, int radioDisparo, int radioAvistamiento, Image[][] imagen) {
        this.idPatrulla = idPatrulla;
        this.fila = fila;
        this.columna = columna;
        this.radioDisparo = radioDisparo;
        this.radioAvistamiento = radioAvistamiento;
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

    /**
     * @return the radioDisparo
     */
    public int getRadioDisparo() {
        return radioDisparo;
    }

    /**
     * @param radioDisparo the radioDisparo to set
     */
    public void setRadioDisparo(int radioDisparo) {
        this.radioDisparo = radioDisparo;
    }

    /**
     * @return the radioAvistamiento
     */
    public int getRadioAvistamiento() {
        return radioAvistamiento;
    }

    /**
     * @param radioAvistamiento the radioAvistamiento to set
     */
    public void setRadioAvistamiento(int radioAvistamiento) {
        this.radioAvistamiento = radioAvistamiento;
    }

    @Override
    public void run() {
       
    }

}
