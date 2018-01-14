/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import clases.EscudosRestauradores;
import clases.EstacionDePolicia;
import clases.Vertice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
//XML
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.List;
import java.util.Random;
import org.jdom2.Document;         // |
import org.jdom2.Element;          // |\ Librerías
import org.jdom2.JDOMException;    // |/ JDOM
import org.jdom2.input.SAXBuilder; // |

/**
 *
 * @author Mateo
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     *
     */
    public static int[][] matrizMapa;
    public static int[][] matrizAdyacen;
    public static int[][] matrizInfluencia;
    public static List<Vertice> objetosList;
    public LinkedList<EscudosRestauradores> listaEscudos;
    public static int cuentaBanco;
    public static int cuentaEstacion;

    private final Gson gson;

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
        matrizMapa = new int[10][10];
        matrizInfluencia = new int[10][10];
        listaEscudos = new LinkedList<>();
        cuentaBanco = 0;
        cuentaEstacion = 1;
        gson = new Gson();
        //cargarDatos();
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        objetosList = new LinkedList<Vertice>();
    }

    /**
     * *
     * Metodo cargarDatos se encarga de llamar al respectivo metodo para obtener
     * la informacion del archivo a utilizar para cargar el mapa(JSON o XML)
     */
    private void cargarDatos() {
        String json = cargarJSON("src/proyectografos/persona.json");
        cargarEnMatrizJSON(json);
    }

    /**
     * *
     * Metodo cargarJSON se encargar de abrir el archivo JSON y extraer la
     * informacion
     *
     * @param ruta es la ruta del archvo a cargar.
     * @return La informacion del archivo en formato String.
     */
    public String cargarJSON(String ruta) {
        StringBuilder datos = new StringBuilder();
        String cadena;
        FileReader f;
        BufferedReader b = null;
        try {
            f = new FileReader(ruta);
            b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                datos.append(cadena);
            }
            b.close();
        } catch (IOException e) {
            System.err.println("Error cargarJson " + e);
        }
        return datos.toString();
    }

    /**
     * *
     * Metodo cargarEnMatrizJSON se encarga de pasar la informacion recuperada
     * del archivo JSON y distribuirla en la matriz del mapa del juego
     *
     * @param infoJSON informacion recuperada del archivo JSON
     */
    public void cargarEnMatrizJSON(String infoJSON) {
        LinkedList<LinkedList<Integer>> matriz;
        matriz = gson.fromJson(infoJSON, new TypeToken<LinkedList<LinkedList<Integer>>>() {
        }.getType());
        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < matriz.get(i).size(); j++) {
                matrizMapa[i][j] = matriz.get(i).get(j);
            }
        }
    }

    /**
     * *
     * Metodo para que bertel me lo sople, Metodo cargarXml se encarga de
     * extraer la informacion de un archivo XML Y distribuirla para general el
     * mapa del juego.
     *
     * @param ruta Es la direccion especificada por el filechooser donde se enc-
     * cuentra el archivo XML.
     */
    public void cargarXml(String ruta) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(ruta);
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("tbody");
            for (int i = 0; i < list.size(); i++) {
                Element tbody = (Element) list.get(i);
                List lista_tr = tbody.getChildren();
                for (int j = 0; j < lista_tr.size(); j++) {
                    Element tr = (Element) lista_tr.get(j);
                    List lista_td = tr.getChildren();
                    for (int k = 0; k < 10; k++) {
                        Element td = (Element) lista_td.get(k);
                        String th = td.getValue();
                        matrizMapa[j][k] = Integer.parseInt(th);
                    }
                }
            }

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
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

        panelVistaPrincipal1 = new Vista.PanelVistaPrincipal();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnCargarMapa = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelVistaPrincipal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelVistaPrincipal1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelVistaPrincipal1Layout = new javax.swing.GroupLayout(panelVistaPrincipal1);
        panelVistaPrincipal1.setLayout(panelVistaPrincipal1Layout);
        panelVistaPrincipal1Layout.setHorizontalGroup(
            panelVistaPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        panelVistaPrincipal1Layout.setVerticalGroup(
            panelVistaPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");

        btnCargarMapa.setText("Cargar mapa");
        btnCargarMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarMapaActionPerformed(evt);
            }
        });
        jMenu1.add(btnCargarMapa);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVistaPrincipal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelVistaPrincipal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarMapaActionPerformed
        JFileChooser abrirArchivo = new JFileChooser();
        //Con esto solamente podamos abrir archivos
        abrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int seleccion = abrirArchivo.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File f = abrirArchivo.getSelectedFile();
            try {
                String nombre = f.getName();
                String path = f.getAbsolutePath();
                if (path.substring(path.length() - 5).contains(".xml")) {
                    cargarXml(path);
                    recorreCreaObj();
                    esAdyacente();

                } else if (path.substring(path.length() - 5).contains(".json")) {
                    String datos = cargarJSON(path);
                    cargarEnMatrizJSON(datos);
                    recorreCreaObj();
                    esAdyacente();
                } else {
                    JOptionPane.showMessageDialog(this, "Archivo invalido, solo puede cargar archivos *.json o *.xml");
                }
                obtenerMatrizInfluencia();
            } catch (Exception e) {
                System.err.println("Error cargarArchivo filechooser " + e);
            }
        }
    }//GEN-LAST:event_btnCargarMapaActionPerformed

    private void panelVistaPrincipal1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelVistaPrincipal1MouseClicked
        int x = evt.getX();
        int y = evt.getY();
        this.panelVistaPrincipal1.obtenerMouse(x, y);

    }//GEN-LAST:event_panelVistaPrincipal1MouseClicked

    public void recorreCreaObj() {
        for (int i = 0; i < matrizMapa.length; i++) {
            for (int j = 0; j < matrizMapa[i].length; j++) {
                if (matrizMapa[i][j] != 0) {
                    if (matrizMapa[i][j] == 5) {
                        objetosList.add(new Vertice("EstacionP", i, j));
                    }
                    if (matrizMapa[i][j] == 2) {
                        objetosList.add(new Vertice("Banco", i, j));
                    }
                    if (matrizMapa[i][j] == 7) {
                        objetosList.add(new Vertice("Guarida", i, j));
                    }
//                    if (matrizMapa[i][j]==18) {
//                        Patrulla patrulla=new Patrulla();
//                        objetosList.add(patrulla);
//                    }
//                    if (matrizMapa[i][j]==12) {
//                        CarroLadron carroLadron=new CarroLadron();
//                        objetosList.add(carroLadron);
//                    }
//                    if (matrizMapa[i][j]==1817) {
//                        Vertice verticeBala= new Vertice();
//                        verticeBala.setTipo("proyectil");
//                        objetosList.add(verticeBala);
//                    }
//                    if (matrizMapa[i][j]==20) {
//                        Vertice verticeEscudo= new Vertice();
//                        verticeEscudo.setTipo("escudo");
//                        objetosList.add(verticeEscudo);
//                    }
//                    if (matrizMapa[i][j]==2120) {
//                        Vertice verticebarrera= new Vertice();
//                        verticebarrera.setTipo("barrera");
//                        objetosList.add(verticebarrera);
//                    }
                    if (matrizMapa[i][j] == 1) {
                        objetosList.add(new Vertice("Calle", i, j));
                    }
                }
            }
        }
    }

    public void esAdyacente() {
        matrizAdyacen = new int[objetosList.size()][objetosList.size()];
        for (int i = 0; i < objetosList.size(); i++) {
            for (int j = 0; j < objetosList.size(); j++) {
                if (!objetosList.get(i).equals(objetosList.get(j))) {
                    if (abs(objetosList.get(i).getFila() - objetosList.get(j).getFila()) < 2 && abs(objetosList.get(i).getColumna() - objetosList.get(j).getColumna()) < 2) {
                        matrizAdyacen[i][j] = 1;
                    }
                }
            }
        }
//        for (int i = 0; i < matrizAdyacen.length; i++) {
//            for (int j = 0; j < matrizAdyacen[i].length; j++) {
//                if (matrizAdyacen[i][j] == 1) {
//                    System.out.print("&&|");
//
//                }
//                System.out.print(" |");
//
//            }
//            System.out.println();
//        }
    }

    /**
     * *
     * Metodo obtenerMatrizInfluencia, se encarga de realizar la subdivision del
     * mapa para cada una de las estaciones que se encuentran disponibles en el
     * mismo, a travez del Algoritmo de Voronoi.
     */
    private static void obtenerMatrizInfluencia() {
        LinkedList<Vertice> listaEstaciones = new LinkedList<>();

        for (int i = 0; i < objetosList.size(); i++) {
            if (objetosList.get(i).getTipo().equalsIgnoreCase("EstacionP")) {
                listaEstaciones.add(objetosList.get(i));
            }
        }
        for (int i = 0; i < matrizInfluencia.length; i++) {
            for (int j = 0; j < matrizInfluencia[i].length; j++) {
                Vertice vertice = obtenerEstacionMasCercana(listaEstaciones, new Point(i, j));
                matrizInfluencia[i][j] = ((EstacionDePolicia) vertice.getContenedor()).getIdEstacion();
            }
        }

        System.err.println("Matriz de influencia:");
        for (int i = 0; i < matrizInfluencia.length; i++) {
            for (int j = 0; j < matrizInfluencia[i].length; j++) {
                System.out.print(matrizInfluencia[i][j] + "|");
            }
            System.out.println("");
        }

    }

    /**
     * *
     * Metodo obtenerEstacionMasCercana se encarga de validar una posicion con
     * respecto a las filas y columnas para determinar cual es la estacion mas
     * cercana.
     *
     * @param listaEstaciones lista con todas las estaciones que existen en el
     * mapa
     * @param punto objeto Point empleado para enviar cordenadas de fila y
     * columna
     * @return retorna el vertice que cumple con la condicion de estar mas cerca
     * al punto en caso de haber 2 o mas estaciones a la misma distancia, se
     * toma la primera estacion.
     */
    private static Vertice obtenerEstacionMasCercana(LinkedList<Vertice> listaEstaciones, Point punto) {
        int indiceLista = -1;
        int distancia = 0;
        for (int i = 0; i < listaEstaciones.size(); i++) {
            int distanciaPunto = (int) Math.sqrt(
                    Math.pow(abs(punto.getX() - listaEstaciones.get(i).getFila()), 2)
                    + Math.pow(abs(punto.getY() - listaEstaciones.get(i).getColumna()), 2)
            );
            if (indiceLista == -1 || distanciaPunto < distancia) {
                distancia = distanciaPunto;
                indiceLista = i;
            }
        }
        if (indiceLista != -1) {
            return listaEstaciones.get(indiceLista);
        }
        return null;
    }

    /***
     * Metodo agregarEscudoAleatorio se encarga de crear un escudo cada vez que el metodo sea invocado,la 
     * ubicacion del mismo se obtiene a partir de enlistar todas las posiciones en las calles disponibles,
     * donde aun no exista un escudo anteriormente.
     */
    private void agregarEscudoAleatorio() {
        LinkedList<Vertice> listaCalles = new LinkedList<>();
        for (int i = 0; i < objetosList.size(); i++) {
            if (objetosList.get(i).getTipo().equalsIgnoreCase("Calle")
                    && !validarExistenciaEscudo(objetosList.get(i).getFila(), objetosList.get(i).getColumna())) {
                listaCalles.add(objetosList.get(i));
            }
        }
        int random = (int) Math.random()*(listaCalles.size());
        listaEscudos.add(new EscudosRestauradores(listaCalles.get(random).getFila(),
                listaCalles.get(random).getColumna(),
                listaEscudos.size()));
    }

    /***
     * Metodo validarExistenciaEscudo, se encarga de verificar que en una posicion de fila y columna no exista ya
     * un escudo creado.
     * @param fila indice de fila a buscar.
     * @param columna indice de columna a buscar.
     * @return retorna true si la posicion ya existe, de lo contrario retorna false.
     */
    private boolean validarExistenciaEscudo(int fila, int columna) {
        for (EscudosRestauradores escudo : this.listaEscudos) {
            if (escudo.getFila() == fila && escudo.getColumna() == columna) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnCargarMapa;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private Vista.PanelVistaPrincipal panelVistaPrincipal1;
    // End of variables declaration//GEN-END:variables
}
