/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrafo;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author Mateo
 */
public class Grafo {

    int[][] matrizAdy;
    int[][] matrizPesos;
    boolean[] marcasProf;
    boolean esGrafoDirigido;
    LinkedList<LinkedList<String>> listaCaminos = new LinkedList<>();

    public void preLLenado(int nVertices) {
        this.matrizAdy = new int[nVertices][nVertices];
        this.matrizPesos = new int[nVertices][nVertices];
        for (int i = 0; i < matrizAdy.length; i++) {
            for (int j = 0; j < matrizAdy[i].length; j++) {

                matrizPesos[i][j] = 1;//infinito

            }
        }
        this.marcasProf = new boolean[matrizAdy.length];

    }

    public int idetentificador(String vertice) {
        if (vertice.equalsIgnoreCase("a")) {
            return 0;
        }
        if (vertice.equalsIgnoreCase("b")) {
            return 1;
        }
        if (vertice.equalsIgnoreCase("c")) {
            return 2;
        }
        if (vertice.equalsIgnoreCase("d")) {
            return 3;
        }
        if (vertice.equalsIgnoreCase("e")) {
            return 4;
        }
        if (vertice.equalsIgnoreCase("f")) {
            return 5;
        }
        System.out.println("No es un vertice permitido");
        return -1;
    }

    public char traductorVertice(int vertice) {
        if (vertice == 0) {
            return 'a';
        } else if (vertice == 1) {
            return 'b';
        } else if (vertice == 2) {
            return 'c';
        } else if (vertice == 3) {
            return 'd';
        } else if (vertice == 4) {
            return 'e';
        } else if (vertice == 5) {
            return 'f';
        }
        return '*';
    }

    public void llenadoMatrices(String vinicial, String vFinal, int peso, boolean esDirigido) {
        if (esDirigido && !this.esGrafoDirigido) {
            this.esGrafoDirigido = true;
        }
        int inicial = this.idetentificador(vinicial);
        int vfinal = this.idetentificador(vFinal);
        if (esDirigido) {
            this.matrizAdy[inicial][vfinal] = 1;
            this.matrizPesos[inicial][vfinal] = peso;
        } else {
            this.matrizAdy[inicial][vfinal] = 1;
            this.matrizAdy[vfinal][inicial] = 1;
            this.matrizPesos[inicial][vfinal] = peso;
            this.matrizPesos[vfinal][inicial] = peso;
        }

    }

    public void imprimirMatrizAdy() {
        System.err.println("Matriz de Adyacencia:");
        for (int i = 0; i < matrizAdy.length; i++) {
            for (int j = 0; j < matrizAdy[i].length; j++) {
                System.out.print(matrizAdy[i][j] + "|");
            }
            System.out.println("");
        }
    }

    public void imprimirMatrizPesos() {
        System.err.println("Matriz de Pesos:");
        for (int i = 0; i < matrizPesos.length; i++) {
            for (int j = 0; j < matrizPesos[i].length; j++) {
                System.out.print(matrizPesos[i][j] + "|");
            }
            System.out.println("");
        }
    }

    public int conexo(int nVertices) {
        int[] arregloTam = new int[nVertices];
        int cont = 0, vertice = -5, sum = 0;
        for (int i = 0; i < matrizAdy.length; i++) {
            for (int j = 0; j < matrizAdy[i].length; j++) {

                if (matrizAdy[i][j] == 0 && matrizAdy[j][i] == 0 && cont < nVertices) {
                    arregloTam[cont] = 1;
                    vertice = j;
                    cont++;
                }

            }
        }
        for (int i = 0; i < arregloTam.length; i++) {
            sum = sum + arregloTam[i];
        }
        if (sum == nVertices) {
            System.out.println("No es conexo");
            return vertice;
        } else {
            System.out.println("Es conexo");
        }
        return vertice;
    }

    public String isConexo() {
        String vertices = "";
        for (int i = 0; i < matrizAdy.length; i++) {
            if (!validarFila(i)) {
                if (!validarColumna(i)) {
                    vertices += traductorVertice(i) + " ";
                }
            }
        }
        if (vertices.equals("")) {
            return "Es conexo";
        }
        return "No es conexo por:" + vertices;
    }

    private boolean validarFila(int fila) {
        for (int i = 0; i < matrizAdy.length; i++) {
            if (matrizAdy[fila][i] == 1) {
                return true;
            }
        }
        return false;
    }

    private boolean validarColumna(int columna) {
        for (int i = 0; i < matrizAdy.length; i++) {
            if (matrizAdy[i][columna] == 1) {
                return true;
            }
        }
        return false;
    }

    public void Grado() {
        int[] grado = new int[matrizAdy.length];
        for (int i = 0; i < matrizAdy.length; i++) {
            for (int j = 0; j < matrizAdy[i].length; j++) {
                grado[i] += matrizAdy[i][j];
            }
        }

        for (int i = 0; i < grado.length; i++) {
            System.out.println("Grado= " + this.traductorVertice(i) + grado[i]);
        }
    }

    public int[] anchura(String vInicio) {
        int inicio = this.idetentificador(vInicio);
        int resultado[] = new int[matrizAdy.length];
        int indice = 0;
        LinkedList<Integer> fila = new LinkedList<Integer>();
        boolean[] marcas = new boolean[matrizAdy.length];
        marcas[inicio] = true;
        fila.add(inicio);
        while (!fila.isEmpty()) {
            int aux = fila.removeFirst();
            for (int i = 0; i < matrizAdy.length; i++) {
                if (!String.valueOf(traductorVertice(aux)).equalsIgnoreCase(String.valueOf(traductorVertice(i)))) {
                    if (esAdyacente(String.valueOf(traductorVertice(aux)), String.valueOf(traductorVertice(i)))) {
                        if (!marcas[i]) {
                            marcas[i] = true;
                            fila.addLast(i);
                        }
                    }
                }
            }
            resultado[indice] = aux;
            indice++;
            System.out.print(aux + "-");

        }
        System.out.println("");
        return resultado;
    }

    public void profundidad(String vInicio) {
        int inicio = this.idetentificador(vInicio);
        for (int i = 0; i < matrizAdy.length; i++) {
            marcasProf[i] = false;
        }
        for (int i = 0; i < matrizAdy.length; i++) {
            if (marcasProf[i] == false) {
                busquedaProfundidad(i);
            }
        }
    }

    private void busquedaProfundidad(int i) {
        marcasProf[i] = true;
        System.out.println(i);
        for (int j = 0; j < matrizAdy.length; j++) {
            if (!String.valueOf(traductorVertice(i)).equalsIgnoreCase(String.valueOf(traductorVertice(j)))) {
                if (esAdyacente(String.valueOf(traductorVertice(i)), String.valueOf(traductorVertice(j)))) {
                    if (!marcasProf[j]) {
                        busquedaProfundidad(j);

                    }
                }
            }
        }
    }

    public boolean esAdyacente(String vInicial, String vFinal) {
        int inicial = this.idetentificador(vInicial);
        int vfinal = this.idetentificador(vFinal);
        if (this.esGrafoDirigido) {
            if (matrizAdy[inicial][vfinal] == 1) {
                return true;
            }
        } else {
            if (matrizAdy[inicial][vfinal] == 1 || matrizAdy[vfinal][inicial] == 1) {
                return true;
            }
        }

        return false;
    }

    public void validarFC() {
        if (esGrafoDirigido && !fuerteConectado()) {
            System.err.println("No es un grafo fuertemente conectado");

        } else {
            System.err.println("Es un grafo fuertemente conectado");
        }
    }

    private boolean fuerteConectado() {
        for (int j = 0; j < matrizAdy.length; j++) {
            String vInicial = String.valueOf(traductorVertice(j));
            for (int i = 0; i < matrizAdy.length; i++) {
                if (!vInicial.equalsIgnoreCase(String.valueOf(traductorVertice(i)))) {
                    if (!validarAlcanceRecursivo(vInicial, String.valueOf(traductorVertice(i)))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validarAlcanceRecursivo(String vHost, String vDestino) {
        if (esAdyacente(vHost, vDestino)) {
            return true;
        } else {
            for (int i = 0; i < matrizAdy.length; i++) {
                if (!vHost.equalsIgnoreCase(String.valueOf(traductorVertice(i)))) {
                    if (esAdyacente(String.valueOf(traductorVertice(i)), vHost)) {
                        if (validarAlcanceRecursivo(String.valueOf(traductorVertice(i)), vDestino)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void caminoSimples(String vHost, String vDestino) {
        LinkedList<String> ruta = new LinkedList<>();
        LinkedList<Integer> visitados = new LinkedList<>();

        ruta.add(vHost);
        visitados.add(idetentificador(vHost));
        camino(vHost, vDestino, ruta, visitados);
        for (LinkedList<String> rutas : listaCaminos) {
            System.out.println(rutas.toString());
        }

    }

    private void camino(String vHost, String vDestino, LinkedList<String> ruta, LinkedList<Integer> visitados) {
        if (vHost.equalsIgnoreCase(vDestino)) {
            this.listaCaminos.add(ruta);
        } else {
            int idFila = idetentificador(vHost);
            for (int i = 0; i < matrizAdy.length; i++) {
                if (matrizAdy[idFila][i] == 1 && !visitados.contains(i)) {
                    LinkedList<String> rutaN = (LinkedList<String>) ruta.clone();
                    LinkedList<Integer> visitN = (LinkedList<Integer>) visitados.clone();

                    rutaN.add(String.valueOf(traductorVertice(i)));
                    visitN.add(i);
                    camino(String.valueOf(traductorVertice(i)), vDestino, rutaN, visitN);
                }
            }
        }
    }

    public void caminoMasVertices() {
        LinkedList<String> mayor = new LinkedList<>();
        for (LinkedList<String> rutas : this.listaCaminos) {
            if (rutas.size() > mayor.size()) {
                mayor = rutas;
            }
        }
        System.out.println(mayor.toString());
    }

    public void caminoMenosVertices() {
        LinkedList<String> menor = listaCaminos.get(0);
        for (LinkedList<String> rutas : this.listaCaminos) {
            if (rutas.size() < menor.size()) {
                menor = rutas;
            }
        }
        System.out.println(menor.toString());
    }

    public void validarMatrizBinaria(int[][] matriz) {
        if (esBinaria(matriz)) {
            if (esMatrizGrafoConexoValida(matriz)) {
                System.out.println("La matriz ingresada corresponde a un grafo conexo");
            } else {
                System.out.println("La matriz ingresada no corresponde a un grafo conexo");
            }
        } else {
            System.out.println("La matriz ingresada no es binaria");
        }
    }

    private boolean esMatrizGrafoConexoValida(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            int validacion = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                validacion += matriz[i][j];
            }
            if (validacion == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean esBinaria(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] != 0 && matriz[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
