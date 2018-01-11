/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Mateo
 */
public class Arista {
    private int peso;
    private Vertice origen;
    private Vertice destino;

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * @return the origen
     */
    public Vertice getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Vertice origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public Vertice getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Vertice destino) {
        this.destino = destino;
    }
}
