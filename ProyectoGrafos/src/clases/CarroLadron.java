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
public class CarroLadron {

    private int fila;
    private int columna;
    private int areaContacto;
    private String[] direccionesImgLadron;
    private Image[] ladronImg;
    private String direccionLadron;

    public CarroLadron() {
    }

    public CarroLadron(int fila, int columna, int areaContacto) {
        this.fila = fila;
        this.columna = columna;
        this.areaContacto = areaContacto;
        this.direccionesImgLadron =new String []{"src/imagenes/Ladrones/Audi","src/imagenes/Ladrones/Black viper","src/imagenes/Ladrones/Car O","src/imagenes/Ladrones/Mini Truck","src/imagenes/Ladrones/Mini van","src/imagenes/Ladrones/Taxi"} ;
        int dirRandom= (int) (Math.random() * 3);
        String dirObtenida=this.direccionesImgLadron[dirRandom];
        for (int i = 1; i <= 4; i++) {
           this.ladronImg[i-1]=new ImageIcon(getClass().getResource(dirObtenida+"/"+i+".png")).getImage();
        }
        this.ladronImg = ladronImg;
        this.direccionLadron = direccionLadron;
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
     * @return the areaContacto
     */
    public int getAreaContacto() {
        return areaContacto;
    }

    /**
     * @param areaContacto the areaContacto to set
     */
    public void setAreaContacto(int areaContacto) {
        this.areaContacto = areaContacto;
    }

    /**
     * @return the direccionesImgLadron
     */
    public String[] getDireccionesImgLadron() {
        return direccionesImgLadron;
    }

    /**
     * @param direccionesImgLadron the direccionesImgLadron to set
     */
    public void setDireccionesImgLadron(String[] direccionesImgLadron) {
        this.direccionesImgLadron = direccionesImgLadron;
    }

    /**
     * @return the ladronImg
     */
    public Image[] getLadronImg() {
        return ladronImg;
    }

    /**
     * @param ladronImg the ladronImg to set
     */
    public void setLadronImg(Image[] ladronImg) {
        this.ladronImg = ladronImg;
    }

    /**
     * @return the direccionLadron
     */
    public String getDireccionLadron() {
        return direccionLadron;
    }

    /**
     * @param direccionLadron the direccionLadron to set
     */
    public void setDireccionLadron(String direccionLadron) {
        this.direccionLadron = direccionLadron;
    }
    

}
