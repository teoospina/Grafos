/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import static Vista.PanelVistaPrincipal.*;

/**
 *
 * @author Mateo
 */
public class EscudosRestauradores {
    private int fila;
    private int columna;
    private int idEscudo;
    private Image imagen;

    public EscudosRestauradores() {
    }

    public EscudosRestauradores(int fila, int columna, int idEscudo) {
        this.fila = fila;
        this.columna = columna;
        this.idEscudo = idEscudo;
        this.imagen = new ImageIcon(getClass().getResource("../imagenes/Escudo/escudo.png")).getImage();
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
     * @return the idEscudo
     */
    public int getIdEscudo() {
        return idEscudo;
    }

    /**
     * @param idEscudo the idEscudo to set
     */
    public void setIdEscudo(int idEscudo) {
        this.idEscudo = idEscudo;
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
    public Rectangle areaImpacto() {
        Rectangle recImpacto = new Rectangle((this.columna*proporcion)+posInicialX, (this.fila*proporcion)+posInicialY, 40, 40);
        return recImpacto;
    }
    
}
