/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import java.awt.Rectangle;
import static Vista.VistaPrincipal.matrizMapa;
import static Vista.VistaPrincipal.matrizInfluencia;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    Thread hiloPatrulla;

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
        this.fila = fila;
        this.columna = columna;
        this.hiloPatrulla = new Thread(this);
        this.hiloPatrulla.start();
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
/***
 * Run donde se programa el conportamiendo del hilo que hace mover los coches de
 * policia teniendo en cuenta la influencia de cada estacion sobre ellos.
 */
    @Override
    public void run() {
       // int[] posAnterior = new int[2];
        while (true) {
            List<Integer[]> dirList = new LinkedList<>();

            if (this.fila >= 0 && this.fila < matrizMapa.length && this.columna - 1 >= 0 && this.columna - 1 < matrizMapa.length && matrizMapa[this.fila][this.columna - 1] == 1) {
                if(indiceInfluencia==matrizInfluencia[this.fila][this.columna-1])
                    dirList.add(new Integer[]{this.fila, this.columna - 1});//Izq
                

            }
            if (this.fila - 1 >= 0 && this.fila - 1 < matrizMapa.length && this.columna >= 0 && this.columna < matrizMapa.length && matrizMapa[this.fila - 1][this.columna] == 1) {
                if(indiceInfluencia==matrizInfluencia[this.fila-1][this.columna])
                    dirList.add(new Integer[]{this.fila - 1, this.columna});//Arriba
                
            }
            if (this.fila >= 0 && this.fila < matrizMapa.length && this.columna + 1 >= 0 && this.columna + 1 < matrizMapa.length && matrizMapa[this.fila][this.columna + 1] == 1) {
                if(indiceInfluencia==matrizInfluencia[this.fila][this.columna+1])
                    dirList.add(new Integer[]{this.fila, this.columna + 1});//"Derecha"
                
            }
            if (this.fila + 1 >= 0 && this.fila + 1 < matrizMapa.length && this.columna >= 0 && this.columna < matrizMapa.length && matrizMapa[this.fila + 1][this.columna] == 1) {
                if(indiceInfluencia==matrizInfluencia[this.fila+1][this.columna])
                    dirList.add(new Integer[]{this.fila + 1, this.columna});//"Abajo"
                
            }
                int dirRandom = (int) (Math.random() * dirList.size());
                if (!dirList.isEmpty()) {
                this.fila = dirList.get(dirRandom)[0];
                this.columna = dirList.get(dirRandom)[1];
            }
                
           


            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Patrulla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
