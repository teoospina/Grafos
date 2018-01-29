/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Mateo
 */
public class Barrera {
    private int fila;
    private int columna;
    private int idBarrera;
    private Image imagen;

    public Barrera() {
    }

    public Barrera(int fila, int columna, int idBarrera) {
        this.fila = fila;
        this.columna = columna;
        this.idBarrera = idBarrera;
        this.imagen = new ImageIcon(getClass().getResource("../imagenes/Barrera/barrera.png")).getImage();
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
     * @return the idBarrera
     */
    public int getIdBarrera() {
        return idBarrera;
    }

    /**
     * @param idBarrera the idBarrera to set
     */
    public void setIdBarrera(int idBarrera) {
        this.idBarrera = idBarrera;
    }

    /**
     * @return the imagen
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    

}
