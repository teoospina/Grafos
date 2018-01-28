/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static Vista.PanelVistaPrincipal.posInicialX;
import static Vista.PanelVistaPrincipal.posInicialY;
import static Vista.PanelVistaPrincipal.proporcion;
import static Vista.VistaPrincipal.matrizMapa;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Mateo
 */
public class CarroLadron implements Runnable {

    private int fila;
    private int columna;
    private int areaContacto;
    private String[] direccionesImgLadron;
    private Image[] ladronImg;
    private String direccionLadron;
    private int xObjeto;
    private int xDestino;
    private int yObjeto;
    private int yDestino;
    private Thread hiloLadron;
    private int dineroRobado;

    public CarroLadron() {
    }

    public CarroLadron(int fila, int columna, int areaContacto) {
        this.fila = fila;
        this.columna = columna;
        this.areaContacto = areaContacto;
        this.direccionesImgLadron = new String[]{"../imagenes/Ladrones/Audi",
                                                 "../imagenes/Ladrones/Black viper", 
                                                 "../imagenes/Ladrones/Car O", 
                                                 "../imagenes/Ladrones/Mini Truck", 
                                                 "../imagenes/Ladrones/Mini van", 
                                                 "../imagenes/Ladrones/Taxi"
                                                };
        int dirRandom = (int) (Math.random() * 3);
        String dirObtenida = this.direccionesImgLadron[dirRandom];
        this.ladronImg = new Image[4];
        for (int i = 1; i <= ladronImg.length; i++) {
            this.ladronImg[i - 1] = new ImageIcon(getClass().getResource(dirObtenida + "/" + i + ".png")).getImage();
        }
        this.direccionLadron = "Abajo";
        this.xObjeto = posInicialX + (proporcion * this.columna);
        this.xDestino = this.xObjeto;
        this.yObjeto = posInicialY + (proporcion * this.fila);
        this.yDestino = this.yObjeto;
        this.dineroRobado = 0;
        this.hiloLadron = new Thread(this);
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
        this.setDireccionesImgLadron(direccionesImgLadron);
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
        this.setLadronImg(ladronImg);
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

    public Image getImagenes() {
        switch (getDireccionLadron()) {
            case "Arriba":
                return getLadronImg()[0];
            case "Izquierda":
                return getLadronImg()[3];
            case "Derecha":
                return getLadronImg()[1];
            case "Abajo":
                return getLadronImg()[2];
        }
        return null;
    }

    @Override
    public void run() {
        while (true) {
            if (this.getxObjeto() == this.getxDestino() && this.getyObjeto() == this.getyDestino()) {
                switch (getDireccionLadron()) {
                    case "Arriba":
                        if (this.getFila() - 1 >= 0 && this.getFila() - 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() - 1][this.getColumna()] == 1) {
                            this.setFila(this.getFila() - 1);
                        }

                        break;
                    case "Izquierda":
                        if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() - 1 >= 0 && this.getColumna() - 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() - 1] == 1) {
                            this.setColumna(this.getColumna() - 1);
                        }
                        break;
                    case "Derecha":
                        if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() + 1 >= 0 && this.getColumna() + 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() + 1] == 1) {
                            this.setColumna(this.getColumna() + 1);
                        }
                        break;
                    case "Abajo":
                        if (this.getFila() + 1 >= 0 && this.getFila() + 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() + 1][this.getColumna()] == 1) {
                            this.setFila(this.getFila() + 1);
                        }
                        break;
                }

                this.setyDestino(posInicialY + (proporcion * this.getFila()));
                this.setxDestino(posInicialX + (proporcion * this.getColumna()));

                try {
                    Thread.sleep(100);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Patrulla.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (this.getyObjeto() != this.getyDestino()) {
                    if (this.getyObjeto() > this.getyDestino()) {
                        this.setyObjeto(this.getyObjeto() - 1);
                    } else {
                        this.setyObjeto(this.getyObjeto() + 1);
                    }
                } else if (this.getxObjeto() != this.getxDestino()) {
                    if (this.getxObjeto() > this.getxDestino()) {
                        this.setxObjeto(this.getxObjeto() - 1);
                    } else {
                        this.setxObjeto(this.getxObjeto() + 1);
                    }
                }
                try {
                    Thread.sleep(8);
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * @return the xObjeto
     */
    public int getxObjeto() {
        return xObjeto;
    }

    /**
     * @param xObjeto the xObjeto to set
     */
    public void setxObjeto(int xObjeto) {
        this.xObjeto = xObjeto;
    }

    /**
     * @return the xDestino
     */
    public int getxDestino() {
        return xDestino;
    }

    /**
     * @param xDestino the xDestino to set
     */
    public void setxDestino(int xDestino) {
        this.xDestino = xDestino;
    }

    /**
     * @return the yObjeto
     */
    public int getyObjeto() {
        return yObjeto;
    }

    /**
     * @param yObjeto the yObjeto to set
     */
    public void setyObjeto(int yObjeto) {
        this.yObjeto = yObjeto;
    }

    /**
     * @return the yDestino
     */
    public int getyDestino() {
        return yDestino;
    }

    /**
     * @param yDestino the yDestino to set
     */
    public void setyDestino(int yDestino) {
        this.yDestino = yDestino;
    }

    /**
     * @return the hiloLadron
     */
    public Thread getHiloLadron() {
        return hiloLadron;
    }

    /**
     * @param hiloLadron the hiloLadron to set
     */
    public void setHiloLadron(Thread hiloLadron) {
        this.hiloLadron = hiloLadron;
    }

}
