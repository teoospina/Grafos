/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import java.awt.Rectangle;
import static Vista.VistaPrincipal.matrizInfluencia;
import static Vista.VistaPrincipal.matrizMapa;


/**
 *
 * @author Mateo
 */
public class Patrulla implements Runnable {

    private String idPatrulla;
    private int fila;
    private int columna;
    private Rectangle areaDisparo;
    private Rectangle areaoAvistamientoAdelante;
    private Rectangle areaoAvistamientoAtras;
    private int indiceInfluencia;
    private Image[][] imagen;

    /**
     * Modos de accion: 1. Vigilante. se mueve en 1 sola direccion si encuentra
     * 2 posibles sentidos. se gira y cambia de sentido, si solo encuentra 1
     * posicion si encuentra varios posibles caminos, escoge al asar para donde
     * seguir 2. Persecucion. obtiene la ubicacion del labron. determina la ruta
     * mas corta para llegar al destino. avanza. 3. Bloqueo. obtiene la ultima
     * posicion en la que fue visto.. determina cual es el banco mas cercano
     */
    public Patrulla() {
    }

    public Patrulla(String id, int indiceInfluencia, int fila, int columna) {
        this.idPatrulla = id;
        this.indiceInfluencia = indiceInfluencia;
    }

    public Patrulla(String idPatrulla, int fila, int columna, Rectangle areaDisparo, Rectangle areaoAvistamientoAdelante, Rectangle areaoAvistamientoAtras, int indiceInfluencia, Image[][] imagen) {
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
    public String getIdPatrulla() {
        return idPatrulla;
    }

    /**
     * @param idPatrulla the idPatrulla to set
     */
    public void setIdPatrulla(String idPatrulla) {
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
