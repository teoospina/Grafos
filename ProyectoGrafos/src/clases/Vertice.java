/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateo
 */
public class Vertice {

    private String tipo;
    private List<Arista> aristas;
    private int fila;
    private int columna;
    private Image imagen;
    private Object contenedor;

    public Vertice() {
    }

    public Vertice(String tipo, int fila, int columna) {
        this.tipo = tipo;
        this.aristas = new LinkedList<>();
        this.fila = fila;
        this.columna = columna;
        switch (tipo) {
            case "Banco":
                this.imagen=new ImageIcon(getClass().getResource("../imagenes/Banco/bank.png")).getImage();
                int dinero= Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese Monto inicial del banco"));
                this.contenedor=new Banco(dinero);
                break;
            case "EstacionP":
                this.imagen=new ImageIcon(getClass().getResource("../imagenes/Policia/4.png")).getImage();
                this.contenedor=new EstacionDePolicia();
                break;
            case "Guarida":
                 this.imagen=new ImageIcon(getClass().getResource("../imagenes/Guarida/guarida.png")).getImage();
                 this.contenedor=new Guarida();
                break;
        
        }
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the aristas
     */
    public List<Arista> getAristas() {
        return aristas;
    }

    /**
     * @param aristas the aristas to set
     */
    public void setAristas(List<Arista> aristas) {
        this.aristas = aristas;
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

    /**
     * @return the contenedor
     */
    public Object getContenedor() {
        return contenedor;
    }

    /**
     * @param contenedor the contenedor to set
     */
    public void setContenedor(Object contenedor) {
        this.contenedor = contenedor;
    }

}
