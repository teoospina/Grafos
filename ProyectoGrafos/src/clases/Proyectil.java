/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static clases.Sounds.proyectilSound;
import javax.swing.ImageIcon;

/**
 *
 * @author Mateo
 */
public class Proyectil implements Runnable {

    private int x;
    private int y;
    private int idBala;
    private Thread hilo;
    private String sentido;
    private ImageIcon[] imagen;

    public Proyectil() {
    }

    public Proyectil(int x, int y, int idBala, String sentido) {
        this.x = x;
        this.y = y;
        this.idBala = idBala;
        this.hilo = new Thread(this);
        this.sentido = sentido;
        proyectilSound();
        iniciarHilo();
    }
    
    private void iniciarHilo(){
        this.hilo.start();
    }

    @Override
    public void run() {
        while (getX()>-5 && getX() <1000 && getY()>-5 && getY() < 1000) {
            switch (getSentido().toLowerCase()) {
                case "arriba":
                    setY(getY() - 5);
                    break;
                case "abajo":
                    setY(getY() + 5);
                    break;
                case "izquierda":
                    setX(getX() - 5);
                    break;
                case "derecha":
                    setX(getX() + 5);
                    break;
            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                System.err.println("Error "+e);
            }
        }
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
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

    /**
     * @return the hilo
     */
    public Thread getHilo() {
        return hilo;
    }

    /**
     * @param hilo the hilo to set
     */
    public void setHilo(Thread hilo) {
        this.hilo = hilo;
    }

    /**
     * @return the sentido
     */
    public String getSentido() {
        return sentido;
    }

    /**
     * @param sentido the sentido to set
     */
    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    /**
     * @return the imagen
     */
    public ImageIcon[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(ImageIcon[] imagen) {
        this.imagen = imagen;
    }

}
