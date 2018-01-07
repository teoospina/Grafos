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

    int fila;
    int columna;
    int areaContacto;
    String[] direccionesImgLadron;
    Image[] ladronImg;
    String direccionLadron;

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
    

}
