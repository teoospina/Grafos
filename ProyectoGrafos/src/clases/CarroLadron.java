/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static Vista.PanelVistaPrincipal.posInicialX;
import static Vista.PanelVistaPrincipal.posInicialY;
import static Vista.PanelVistaPrincipal.proporcion;
import static Vista.VistaPrincipal.listaEscudos;
import Vista.VistaPrincipal;
import static Vista.VistaPrincipal.ladronCar;
import static Vista.VistaPrincipal.matrizMapa;
import static Vista.VistaPrincipal.objetosList;
import static Vista.VistaPrincipal.*;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
    private LinkedList<LinkedList<Vertice>> listaCaminos;
    LinkedList<Vertice> rutasIr;
    private String modo;
    private Banco bancoRobar;
    private int vidaCarro;
    private boolean abaricia;

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
        this.rutasIr = new LinkedList<>();
        this.vidaCarro = 100;
        this.abaricia = false;
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
            this.setModo("conducir");
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
        salioLadron = false;
        while (true) {
            llegarGuarida();

            colisionEscudo();
            switch (this.getModo().toLowerCase()) {
                case "conducir":
                    if (this.getxObjeto() == this.getxDestino() && this.getyObjeto() == this.getyDestino()) {
                        if (rutasIr.isEmpty()) {
                            switch (getDireccionLadron()) {
                                case "Arriba":
                                    if (this.getFila() - 1 >= 0 && this.getFila() - 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() - 1][this.getColumna()] == 1) {
                                        this.setFila(this.getFila() - 1);
                                    }
                                    if (this.getFila() - 1 >= 0 && this.getFila() - 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() - 1][this.getColumna()] == 2) {
                                        this.setModo("robar");
                                        this.setBancoRobar(obtenerBanco(getFila() - 1, getColumna()));
                                        if (getBancoRobar().getDinero() <= 0) {
                                            this.setModo("conducir");
                                        }
                                    }
                                    if (this.getFila() - 1 >= 0 && this.getFila() - 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() - 1][this.getColumna()] == 7) {
                                        salioLadron = true;
                                        this.setFila(this.getFila() - 1);

                                        System.out.println("entra guarida");

                                    }
                                    break;
                                case "Izquierda":
                                    if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() - 1 >= 0 && this.getColumna() - 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() - 1] == 1) {
                                        this.setColumna(this.getColumna() - 1);
                                    }
                                    if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() - 1 >= 0 && this.getColumna() - 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() - 1] == 2) {
                                        this.setModo("robar");
                                        this.setBancoRobar(obtenerBanco(getFila(), getColumna() - 1));
                                        if (getBancoRobar().getDinero() <= 0) {
                                            this.setModo("conducir");
                                        }
                                    }
                                    if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() - 1 >= 0 && this.getColumna() - 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() - 1] == 7) {
                                        salioLadron = true;
                                        this.setColumna(this.getColumna() - 1);

                                    }
                                    break;
                                case "Derecha":
                                    if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() + 1 >= 0 && this.getColumna() + 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() + 1] == 1) {
                                        this.setColumna(this.getColumna() + 1);
                                    }
                                    if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() + 1 >= 0 && this.getColumna() + 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() + 1] == 2) {
                                        this.setModo("robar");
                                        this.setBancoRobar(obtenerBanco(getFila(), getColumna() + 1));
                                        if (getBancoRobar().getDinero() <= 0) {
                                            this.setModo("conducir");
                                        }
                                    }
                                    if (this.getFila() >= 0 && this.getFila() < matrizMapa.length && this.getColumna() + 1 >= 0 && this.getColumna() + 1 < matrizMapa.length && matrizMapa[this.getFila()][this.getColumna() + 1] == 7) {
                                        salioLadron = true;
                                        this.setColumna(this.getColumna() + 1);

                                    }
                                    break;
                                case "Abajo":
                                    if (this.getFila() + 1 >= 0 && this.getFila() + 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() + 1][this.getColumna()] == 1) {
                                        this.setFila(this.getFila() + 1);
                                    }
                                    if (this.getFila() + 1 >= 0 && this.getFila() + 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() + 1][this.getColumna()] == 2) {
                                        this.setModo("robar");
                                        this.setBancoRobar(obtenerBanco(getFila() + 1, getColumna()));
                                        if (getBancoRobar().getDinero() <= 0) {
                                            this.setModo("conducir");
                                        }
                                    }
                                    if (this.getFila() + 1 >= 0 && this.getFila() + 1 < matrizMapa.length && this.getColumna() >= 0 && this.getColumna() < matrizMapa.length && matrizMapa[this.getFila() + 1][this.getColumna()] == 7) {
                                        salioLadron = true;
                                        this.setFila(this.getFila() + 1);

                                        System.out.println("entra guarida");
                                    }
                                    break;
                            }
                        } else {
                            this.modo = "huir";
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
                    int monto = getBancoRobar().entregarDinero();
                    if (monto > 0) {
                        System.out.println("Se obtuvo " + monto);
                        this.setDineroRobado(this.getDineroRobado() + monto);
                        if (getBancoRobar().getDinero() == 0) {
                            this.setModo("conducir");
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    break;
                case "huir":
                    if (!rutasIr.isEmpty()) {
                        Vertice verticeGuardAIr = rutasIr.removeFirst();
                        if (verticeGuardAIr.getFila() - this.getFila() == -1) {//arriba
                            this.direccionLadron = "Arriba";
                        } else if (verticeGuardAIr.getFila() - this.getFila() == 1) {//abaj0
                            this.direccionLadron = "Abajo";
                        } else if (verticeGuardAIr.getColumna() - this.getColumna() == -1) {//izq
                            this.direccionLadron = "Izquierda";
                        } else if (verticeGuardAIr.getColumna() - this.getColumna() == 1) {//der
                            this.direccionLadron = "Derecha";
                        }
                        this.setFila(verticeGuardAIr.getFila());
                        this.setColumna(verticeGuardAIr.getColumna());
                        this.setyDestino(posInicialY + (proporcion * this.fila));
                        this.setxDestino(posInicialX + (proporcion * this.columna));
                        this.modo = "conducir";
                        if(rutasIr.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Finaliza el juego, su puntaje es: "+ladronCar.getDineroRobado(), "Fin del juego", JOptionPane.ERROR_MESSAGE);
                            // vistacerrar.dispose();
                            System.exit(0);
                        }
                    }

                    break;
            }

        }
    }
    
    public void definirRutaHuir(LinkedList<Vertice> ruta){
        if(ruta != null){
            this.rutasIr = ruta;
            this.modo = "huir";
        }else{
            JOptionPane.showMessageDialog(null, "Finaliza el juego, no hay ruta, su puntaje es: "+ladronCar.getDineroRobado(), "Fin del juego", JOptionPane.ERROR_MESSAGE);
               // vistacerrar.dispose();
                System.exit(0);
        }
    }

    /**
     * *
     * Obtiene el banco que se va a robar
     *
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
        setListaCaminos(new LinkedList<>());
        LinkedList<Vertice> ruta = new LinkedList<>();
        LinkedList<Vertice> visitados = new LinkedList<>();

        ruta.add(vHost);
        visitados.add(vHost);
        camino(vHost, ruta, visitados);

    }

    private void camino(Vertice vHost, LinkedList<Vertice> ruta, LinkedList<Vertice> visitados) {
        if (vHost.getTipo().equalsIgnoreCase("Guarida")) {
            this.getListaCaminos().add(ruta);
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
        LinkedList<Vertice> menor = getListaCaminos().get(0);
        for (LinkedList<Vertice> rutas : this.getListaCaminos()) {
            if (rutas.size() < menor.size()) {
                menor = rutas;
            }
        }
        return menor;
    }

    /**
     * @return the dineroRobado
     */
    public int getDineroRobado() {
        return dineroRobado;
    }

    /**
     * @param dineroRobado the dineroRobado to set
     */
    public void setDineroRobado(int dineroRobado) {
        this.dineroRobado = dineroRobado;
    }

    /**
     * @return the listaCaminos
     */
    public LinkedList<LinkedList<Vertice>> getListaCaminos() {
        return listaCaminos;
    }

    /**
     * @param listaCaminos the listaCaminos to set
     */
    public void setListaCaminos(LinkedList<LinkedList<Vertice>> listaCaminos) {
        this.listaCaminos = listaCaminos;
    }

    /**
     * @return the modo
     */
    public String getModo() {
        return modo;
    }

    /**
     * @param modo the modo to set
     */
    public void setModo(String modo) {
        this.modo = modo;
    }

    /**
     * @return the bancoRobar
     */
    public Banco getBancoRobar() {
        return bancoRobar;
    }

    /**
     * @param bancoRobar the bancoRobar to set
     */
    public void setBancoRobar(Banco bancoRobar) {
        this.bancoRobar = bancoRobar;
    }

    /**
     * @return the vidaCarro
     */
    public int getVidaCarro() {
        return vidaCarro;
    }

    /**
     * @param vidaCarro the vidaCarro to set
     */
    public void setVidaCarro(int vidaCarro) {
        this.vidaCarro = vidaCarro;
    }

    public Rectangle areaImpacto() {
        Rectangle recImpacto = new Rectangle(this.getxObjeto(), this.getyObjeto(), 40, 40);
        return recImpacto;
    }

    public void colisionEscudo() {
        for (int i = 0; i < listaEscudos.size(); i++) {
            if (listaEscudos.get(i).areaImpacto().intersects(this.areaImpacto())) {
                if (getVidaCarro() < 100) {
                    this.setVidaCarro(this.getVidaCarro() + 10);
                    listaEscudos.remove(i);
                    return;
                }
            }
        }
    }

    public void llegarGuarida() {
        if (salioLadron) {
            if (matrizMapa[this.getFila()][this.getColumna()] == 7) {
                if (isAbaricia()) {
                    this.dineroRobado *= 2;

                }
                JOptionPane.showMessageDialog(null, "Finaliza el juego su puntaje es: " + ladronCar.getDineroRobado(), "Fin del juego", JOptionPane.INFORMATION_MESSAGE);

                System.exit(0);
            }

        }
    }

    /**
     * @return the abaricia
     */
    public boolean isAbaricia() {
        return abaricia;
    }

    /**
     * @param abaricia the abaricia to set
     */
    public void setAbaricia(boolean abaricia) {
        this.abaricia = abaricia;
    }
}
