/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import static Vista.VistaPrincipal.*;
import clases.Banco;

import clases.EstacionDePolicia;
import clases.Proyectil;
import clases.Vertice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Danii
 */
public class PanelVistaPrincipal extends javax.swing.JPanel {

    public static int posInicialX;
    public static int posInicialY;
    public static int proporcion;
    public Color[] listaColor;
    VistaPrincipal vistacerrar;

    /**
     * Creates new form PanelVistaPrincipal
     */
    public PanelVistaPrincipal() {
        initComponents();
        proporcion = 40;
        this.setSize(600, 500);
        posInicialX = (600 - (10 * proporcion)) / 2;
        posInicialY = 10;

        listaColor = new Color[]{
            Color.black,
            Color.yellow,
            Color.lightGray,
            Color.red,
            Color.gray,
            Color.pink,
            Color.magenta,
            Color.darkGray,
            Color.orange,
            Color.cyan,
            Color.blue,
            Color.green,
            Color.white
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        pintarMapa(g);
        if (ladronCar != null) {
            pintarLadron(g);
        }
        pintarObjetos(g);
        pintarEscudos(g);
        pintarBarrera(g);
        if (pintarInfluencia) {
            pintarZonaInfluencia(g);
        }
//        if(!listaProyectil.isEmpty()){
//            pintarProyectil(null, g);
//        }

        repaint();
    }

    /**
     * *
     * Metodo para graficar el mapa sin aun paramerizar los objetos de este.
     *
     * @param g Componente grafico de la interfaz, donde se visualiaza
     */
    private void pintarMapa(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 600, 600);
        if (matrizMapa != null && matrizMapa.length > 0) {
            for (int i = 0; i < matrizMapa.length; i++) {
                for (int j = 0; j < matrizMapa[i].length; j++) {
                    if (matrizMapa[i][j] == 1 || matrizMapa[i][j] == 8 || matrizMapa[i][j] == 9) {
                        g.setColor(Color.white);
                    } else {
                        g.setColor(Color.green);
                    }
                    g.fillRect(posInicialX + (proporcion * j), posInicialY + (proporcion * i), proporcion, proporcion);
                    g.setColor(Color.black);
                    g.drawRect(posInicialX + (proporcion * j), posInicialY + (proporcion * i), proporcion, proporcion);
                }
            }
        }
    }

    public Point obtenerMouse(int x, int y) {
        x = (x - posInicialX) / proporcion;
        y = (y - posInicialY) / proporcion;
        System.out.println("fila" + y + "columna" + x);
        return new Point(x, y);
    }

    /**
     * *
     * Metodo que grafica los objetos vertice
     *
     * @param g Componente grafico
     */
    private void pintarObjetos(Graphics g) {
        if (objetosList != null && objetosList.size() > 0) {
            for (int i = 0; i < objetosList.size(); i++) {
                if (objetosList.get(i).getImagen() != null) {
                    if (objetosList.get(i).getTipo().equalsIgnoreCase("EstacionP")) {
                        pintarPatrulla(objetosList.get(i), g);
                    }
                    g.drawImage(objetosList.get(i).getImagen(), posInicialX + objetosList.get(i).getColumna() * proporcion, posInicialY + objetosList.get(i).getFila() * proporcion, proporcion + 10, proporcion + 10, null);

                    if (objetosList.get(i).getTipo().equalsIgnoreCase("Banco")) {
                        if (objetosList.get(i).getContenedor() != null && ((Banco) objetosList.get(i).getContenedor()).getDinero() == 0) {
                            g.setColor(Color.red);
                            g.fillRect((posInicialX + objetosList.get(i).getColumna() * proporcion) + proporcion / 2, posInicialY + objetosList.get(i).getFila() * proporcion, 5, proporcion);
                            g.fillRect((posInicialX + objetosList.get(i).getColumna() * proporcion), (posInicialY + objetosList.get(i).getFila() * proporcion) + proporcion / 2, proporcion, 5);
                        }
                    }
                }
            }
        }
    }

    private void pintarPatrulla(Vertice vertice, Graphics g) {
        EstacionDePolicia estacionP = (EstacionDePolicia) vertice.getContenedor();
        for (int i = 0; i < estacionP.getPatrullas().size(); i++) {
            //g.setColor(Color.blue);
            //g.fillRect((estacionP.getPatrullas().get(i).getColumna()*proporcion)+posInicialX, (estacionP.getPatrullas().get(i).getFila()*proporcion)+posInicialY, 20, 20);
            g.drawImage(estacionP.getPatrullas().get(i).getImagenes(), estacionP.getPatrullas().get(i).getxObjeto(), estacionP.getPatrullas().get(i).getyObjeto(), proporcion, proporcion, this);
            Rectangle rec = estacionP.getPatrullas().get(i).obtenerAreaAvistamiento();
            Rectangle recDis = estacionP.getPatrullas().get(i).obtenerAreaDisparo();
            pintarProyectil(estacionP.getPatrullas().get(i).getListaProyectil(), g);
            g.setColor(Color.red);
            g.drawRect(rec.x, rec.y, rec.width, rec.height);
            g.setColor(Color.MAGENTA);
            g.drawRect(recDis.x, recDis.y, recDis.width, recDis.height);
            //pintarProyectil(estacionP.getPatrullas().get(i).getListaProyectiles(), g);
        }
    }

    private void pintarProyectil(LinkedList<Proyectil> lista2, Graphics g) {
        for (int i = 0; i < lista2.size(); i++) {
            g.setColor(Color.GRAY);
            g.fillRect(lista2.get(i).getX(), lista2.get(i).getY(), 13, 10);
            g.setColor(Color.GREEN);
            g.drawRect(lista2.get(i).areaImpacto().x, lista2.get(i).areaImpacto().y, lista2.get(i).areaImpacto().height, lista2.get(i).areaImpacto().width);
        }
    }

    private void pintarLadron(Graphics g) {
        g.drawImage(ladronCar.getImagenes(), ladronCar.getxObjeto(), ladronCar.getyObjeto(), proporcion, proporcion, this);
        g.setColor(Color.yellow);
        g.drawRect(ladronCar.areaImpacto().x, ladronCar.areaImpacto().y, ladronCar.areaImpacto().width, ladronCar.areaImpacto().height);

        if (ladronCar != null) {
            g.setColor(Color.RED);
            g.fillRect(0, 1, ladronCar.getVidaCarro(), 15);
            g.setColor(Color.WHITE);
            g.drawString(("Dinero= "+String.valueOf(ladronCar.getDineroRobado())), 1, 30);
            if(ladronCar.getVidaCarro()<=0){
                 JOptionPane.showMessageDialog(null, "Finaliza el juego su puntaje es: "+ladronCar.getDineroRobado(), "Fin del juego", JOptionPane.ERROR_MESSAGE);
               // vistacerrar.dispose();
                System.exit(0);
            }
        }

    }

    private void pintarEscudos(Graphics g) {
        if (listaEscudos != null && !listaEscudos.isEmpty()) {
            for (int i = 0; i < listaEscudos.size(); i++) {
                g.drawImage(listaEscudos.get(i).getImagen(), posInicialX + (listaEscudos.get(i).getColumna() * proporcion), posInicialY + (listaEscudos.get(i).getFila() * proporcion), proporcion, proporcion, this);
            }
        }
    }

    private void pintarZonaInfluencia(Graphics g) {
        if (matrizInfluencia != null) {
            for (int i = 0; i < matrizMapa.length; i++) {
                for (int j = 0; j < matrizMapa[i].length; j++) {
                    if (matrizInfluencia[i][j] != 0) {
                        g.setColor(listaColor[matrizInfluencia[i][j] - 1]);
                        g.fill3DRect(posInicialX + (j * proporcion), posInicialY + (i * proporcion), 10, 10, true);
                    }
                }
            }
        }
    }

    private void pintarBarrera(Graphics g) {
        if (listaBarreras != null) {
            for (int i = 0; i < listaBarreras.size(); i++) {
                g.drawImage(listaBarreras.get(i).getImagen(), posInicialX + (listaBarreras.get(i).getColumna() * proporcion), posInicialY + (listaBarreras.get(i).getFila() * proporcion), proporcion, proporcion, this);

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
