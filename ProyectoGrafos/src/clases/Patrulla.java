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
import static Vista.VistaPrincipal.listaBarreras;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import static Vista.PanelVistaPrincipal.*;

/**
 *
 * @author Mateo
 */
public class Patrulla implements Runnable {

    private int idPatrulla;
    private int fila;
    private int columna;
    private Rectangle areaDisparo;
    private Rectangle areaoAvistamientoAdelante;
    private Rectangle areaoAvistamientoAtras;
    private int indiceInfluencia;
    private Image[][] imagen;
    private Thread hiloPatrulla;
    private int sentidoImagen;
    private int movSirena;
    private int xObjeto;
    private int xDestino;
    private int yObjeto;
    private int yDestino;

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

    public Patrulla(int id, int indiceInfluencia, int fila, int columna) {
        this.idPatrulla = id;
        this.indiceInfluencia = indiceInfluencia;
        this.fila = fila;
        this.columna = columna;
        this.hiloPatrulla = new Thread(this);
        this.imagen = new Image[][]{
            {new ImageIcon(getClass().getResource("../imagenes/Policia/11.png")).getImage(), new ImageIcon(getClass().getResource("../imagenes/Policia/12.png")).getImage(), new ImageIcon(getClass().getResource("../imagenes/Policia/13.png")).getImage()},
            {new ImageIcon(getClass().getResource("../imagenes/Policia/21.png")).getImage(), new ImageIcon(getClass().getResource("../imagenes/Policia/22.png")).getImage(), new ImageIcon(getClass().getResource("../imagenes/Policia/23.png")).getImage()},
            {new ImageIcon(getClass().getResource("../imagenes/Policia/31.png")).getImage(), new ImageIcon(getClass().getResource("../imagenes/Policia/32.png")).getImage(), new ImageIcon(getClass().getResource("../imagenes/Policia/33.png")).getImage()},
            {new ImageIcon(getClass().getResource("../imagenes/Policia/41.png")).getImage(), new ImageIcon(getClass().getResource("../imagenes/Policia/42.png")).getImage(), new ImageIcon(getClass().getResource("../imagenes/Policia/43.png")).getImage()}
        };
        this.movSirena = 0;
        this.sentidoImagen = 0;
        this.xObjeto = posInicialX + (proporcion * this.columna);
        this.xDestino = this.xObjeto;
        this.yObjeto = posInicialY + (proporcion * this.fila);
        this.yDestino = this.yObjeto;

    }
//"../imagenes/Policia/13.png"

    public Patrulla(int idPatrulla, int fila, int columna, Rectangle areaDisparo, Rectangle areaoAvistamientoAdelante, Rectangle areaoAvistamientoAtras, int indiceInfluencia, Image[][] imagen) {
        this.idPatrulla = idPatrulla;
        this.fila = fila;
        this.columna = columna;
        this.areaDisparo = areaDisparo;
        this.areaoAvistamientoAdelante = areaoAvistamientoAdelante;
        this.areaoAvistamientoAtras = areaoAvistamientoAtras;
        this.indiceInfluencia = indiceInfluencia;
        this.imagen = imagen;
    }

    public Image getImagenes() {
        return this.imagen[sentidoImagen][movSirena];
    }

    /**
     * @return the idPatrulla
     */
    public int getIdPatrulla() {
        return idPatrulla;
    }

    /**
     * @param idPatrulla the idPatrulla to set
     */
    public void setIdPatrulla(int idPatrulla) {
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

    /**
     * *
     * Run donde se programa el conportamiendo del hilo que hace mover los
     * coches de policia teniendo en cuenta la influencia de cada estacion sobre
     * ellos.
     */
    @Override
    public void run() {
        // int[] posAnterior = new int[2];
        while (true) {

            if (this.getxObjeto() == this.getxDestino() && this.getyObjeto() == this.getyDestino()) {
                List<Integer[]> dirList = new LinkedList<>();
                int barreraRandom = (int) (Math.random() * 100);
                if(barreraRandom == 4 && listaBarreras.size()<8)
                    bloquearCamino();
                
                if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() - 1 >= 0 && this.getColumna() - 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() - 1] == 1) {
                    if (getIndiceInfluencia() == matrizInfluencia[this.getFila()][this.getColumna() - 1]) {
                        dirList.add(new Integer[]{this.getFila(), this.getColumna() - 1, 3});//Izq
                    }

                }
                if (this.getFila() - 1 >= 0 && this.getFila() - 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() - 1][this.getColumna()] == 1) {
                    if (getIndiceInfluencia() == matrizInfluencia[this.getFila() - 1][this.getColumna()]) {
                        dirList.add(new Integer[]{this.getFila() - 1, this.getColumna(), 0});//Arriba
                    }
                }
                if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() + 1 >= 0 && this.getColumna() + 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() + 1] == 1) {
                    if (getIndiceInfluencia() == matrizInfluencia[this.getFila()][this.getColumna() + 1]) {
                        dirList.add(new Integer[]{this.getFila(), this.getColumna() + 1, 1});//"Derecha"
                    }
                }
                if (this.getFila() + 1 >= 0 && this.getFila() + 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() + 1][this.getColumna()] == 1) {
                    if (getIndiceInfluencia() == matrizInfluencia[this.getFila() + 1][this.getColumna()]) {
                        dirList.add(new Integer[]{this.getFila() + 1, this.getColumna(), 2});//"Abajo"
                    }
                }
                int dirRandom = (int) (Math.random() * dirList.size());
                if (!dirList.isEmpty()) {
                    this.setFila((int) dirList.get(dirRandom)[0]);
                    this.setColumna((int) dirList.get(dirRandom)[1]);
                    this.setyDestino(posInicialY + (proporcion * this.fila));
                    this.setxDestino(posInicialX + (proporcion * this.columna));
                    this.sentidoImagen = dirList.get(dirRandom)[2];
                }

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
                    Thread.sleep(10);
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * @return the areaDisparo
     */
    public Rectangle getAreaDisparo() {
        return areaDisparo;
    }

    /**
     * @param areaDisparo the areaDisparo to set
     */
    public void setAreaDisparo(Rectangle areaDisparo) {
        this.areaDisparo = areaDisparo;
    }

    /**
     * @return the areaoAvistamientoAdelante
     */
    public Rectangle getAreaoAvistamientoAdelante() {
        return areaoAvistamientoAdelante;
    }

    /**
     * @param areaoAvistamientoAdelante the areaoAvistamientoAdelante to set
     */
    public void setAreaoAvistamientoAdelante(Rectangle areaoAvistamientoAdelante) {
        this.areaoAvistamientoAdelante = areaoAvistamientoAdelante;
    }

    /**
     * @return the areaoAvistamientoAtras
     */
    public Rectangle getAreaoAvistamientoAtras() {
        return areaoAvistamientoAtras;
    }

    /**
     * @param areaoAvistamientoAtras the areaoAvistamientoAtras to set
     */
    public void setAreaoAvistamientoAtras(Rectangle areaoAvistamientoAtras) {
        this.areaoAvistamientoAtras = areaoAvistamientoAtras;
    }

    /**
     * @return the indiceInfluencia
     */
    public int getIndiceInfluencia() {
        return indiceInfluencia;
    }

    /**
     * @param indiceInfluencia the indiceInfluencia to set
     */
    public void setIndiceInfluencia(int indiceInfluencia) {
        this.indiceInfluencia = indiceInfluencia;
    }

    /**
     * @return the imagen
     */
    public Image[][] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Image[][] imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the hiloPatrulla
     */
    public Thread getHiloPatrulla() {
        return hiloPatrulla;
    }

    /**
     * @param hiloPatrulla the hiloPatrulla to set
     */
    public void setHiloPatrulla(Thread hiloPatrulla) {
        this.hiloPatrulla = hiloPatrulla;
    }

    /**
     * @return the sentidoImagen
     */
    public int getSentidoImagen() {
        return sentidoImagen;
    }

    /**
     * @param sentidoImagen the sentidoImagen to set
     */
    public void setSentidoImagen(int sentidoImagen) {
        this.sentidoImagen = sentidoImagen;
    }

    /**
     * @return the movSirena
     */
    public int getMovSirena() {
        return movSirena;
    }

    /**
     * @param movSirena the movSirena to set
     */
    public void setMovSirena(int movSirena) {
        this.movSirena = movSirena;
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

    public void bloquearCamino() {
        listaBarreras.add(new Barrera(this.fila, this.columna, this.idPatrulla));
    }
}
