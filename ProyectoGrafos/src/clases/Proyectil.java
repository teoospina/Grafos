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
public class Proyectil implements Runnable{

    private int fila;
    private int columna;
    private int idBala;
    private Thread hilo;

    public Proyectil() {
    }

    public Proyectil(int fila, int columna, int idBala) {
        this.fila = fila;
        this.columna = columna;
        this.idBala = idBala;
        this.hilo = new Thread(this);
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
     * @return the idBala
     */
    public int getIdBala() {
        return idBala;
    }

    /**
     * @param idBala the idBala to set
     */
    public void setIdBala(int idBala) {
        this.idBala = idBala;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
