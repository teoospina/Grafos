/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static Vista.PanelVistaPrincipal.posInicialX;
import static Vista.PanelVistaPrincipal.posInicialY;
import static Vista.PanelVistaPrincipal.proporcion;
import Vista.VistaPrincipal;
import static Vista.VistaPrincipal.matrizMapa;
import static Vista.VistaPrincipal.objetosList;
import java.awt.Image;
import java.util.LinkedList;
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
    LinkedList<LinkedList<Vertice>> listaCaminos;
    private String modo;
    private Banco bancoRobar;

    public CarroLadron() {
    }

    public CarroLadron(int fila, int columna, int areaContacto) {
        this.fila = fila;
        this.columna = columna;
        this.dineroRobado = 0;
        this.areaContacto = areaContacto;
        this.direccionesImgLadron = new String[]{"../imagenes/Ladrones/Audi",
            "../imagenes/Ladrones/Black viper",
            "../imagenes/Ladrones/Car O",
            "../imagenes/Ladrones/Mini Truck",
            "../imagenes/Ladrones/Mini van",
            "../imagenes/Ladrones/Taxi"
        };
        this.modo = "conducir";
        int dirRandom = (int) (Math.random() * direccionesImgLadron.length);
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
        this.hiloLadron = new Thread(this);
        this.listaCaminos = new LinkedList<>();
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
        if (!this.modo.equalsIgnoreCase("conducir")) {
            this.modo = "conducir";
        }
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
            switch (this.modo.toLowerCase()) {
                case "conducir":
                    if (this.getxObjeto() == this.getxDestino() && this.getyObjeto() == this.getyDestino()) {
                        switch (getDireccionLadron()) {
                            case "Arriba":
                                if (this.getFila() - 1 >= 0 && this.getFila() - 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() - 1][this.getColumna()] == 1) {
                                    this.setFila(this.getFila() - 1);
                                }
                                if (this.getFila() - 1 >= 0 && this.getFila() - 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() - 1][this.getColumna()] == 2) {
                                    this.modo = "robar";
                                    this.bancoRobar = obtenerBanco(fila - 1, columna);
                                    if (bancoRobar.getDinero()<=0) {
                                        this.modo="conducir";
                                    }
                                }
                                break;
                            case "Izquierda":
                                if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() - 1 >= 0 && this.getColumna() - 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() - 1] == 1) {
                                    this.setColumna(this.getColumna() - 1);
                                }
                                if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() - 1 >= 0 && this.getColumna() - 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() - 1] == 2) {
                                    this.modo = "robar";
                                    this.bancoRobar = obtenerBanco(fila, columna - 1);
                                     if (bancoRobar.getDinero()<=0) {
                                        this.modo="conducir";
                                    }
                                }
                                break;
                            case "Derecha":
                                if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() + 1 >= 0 && this.getColumna() + 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() + 1] == 1) {
                                    this.setColumna(this.getColumna() + 1);
                                }
                                if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() + 1 >= 0 && this.getColumna() + 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() + 1] == 2) {
                                    this.modo = "robar";
                                    this.bancoRobar = obtenerBanco(fila, columna + 1);
                                     if (bancoRobar.getDinero()<=0) {
                                        this.modo="conducir";
                                    }
                                }
                                break;
                            case "Abajo":
                                if (this.getFila() + 1 >= 0 && this.getFila() + 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() + 1][this.getColumna()] == 1) {
                                    this.setFila(this.getFila() + 1);
                                }
                                if (this.getFila() + 1 >= 0 && this.getFila() + 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() + 1][this.getColumna()] == 2) {
                                    this.modo = "robar";
                                    this.bancoRobar = obtenerBanco(fila + 1, columna);
                                     if (bancoRobar.getDinero()<=0) {
                                        this.modo="conducir";
                                    }
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
                    break;
                case "robar":
                    int monto = bancoRobar.entregarDinero();
                    if (monto > 0) {
                        System.out.println("Se obtuvo " + monto);
                        this.dineroRobado += monto;
                        if (bancoRobar.getDinero() == 0) {
                            this.modo = "conducir";
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    break;
                case "huir":
                    break;
            }

        }
    }
/***
 * Obtiene el banco que se va a robar
 * @param fila fila del banco
 * @param columna columna del banco
 * @return 
 */
    public Banco obtenerBanco(int fila, int columna) {
        for (Vertice vertice : objetosList) {
            if (vertice.getFila() == fila && vertice.getColumna() == columna) {
                if (vertice.getTipo().equalsIgnoreCase("banco")) {
                    return (Banco) vertice.getContenedor();
                }
            }
        }
        return null;
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

    public void caminoSimples(Vertice vHost) {
        listaCaminos = new LinkedList<>();
        LinkedList<Vertice> ruta = new LinkedList<>();
        LinkedList<Vertice> visitados = new LinkedList<>();

        ruta.add(vHost);
        visitados.add(vHost);
        camino(vHost, ruta, visitados);

    }

    private void camino(Vertice vHost, LinkedList<Vertice> ruta, LinkedList<Vertice> visitados) {
        if (vHost.getTipo().equalsIgnoreCase("Guarida")) {
            this.listaCaminos.add(ruta);
        } else {
            int idFila = objetosList.indexOf(vHost);
            for (int i = 0; i < VistaPrincipal.matrizAdyacen.length; i++) {
                if (VistaPrincipal.matrizAdyacen[idFila][i] == 1 && !visitados.contains(objetosList.get(i))) {
                    LinkedList<Vertice> rutaN = (LinkedList<Vertice>) ruta.clone();
                    LinkedList<Vertice> visitN = (LinkedList<Vertice>) visitados.clone();

                    rutaN.add(objetosList.get(i));
                    visitN.add(objetosList.get(i));
                    camino(objetosList.get(i), rutaN, visitN);
                }
            }
        }
    }

    public LinkedList<Vertice> caminoMenosVertices() {
        LinkedList<Vertice> menor = listaCaminos.get(0);
        for (LinkedList<Vertice> rutas : this.listaCaminos) {
            if (rutas.size() < menor.size()) {
                menor = rutas;
            }
        }
        return menor;
    }
}
