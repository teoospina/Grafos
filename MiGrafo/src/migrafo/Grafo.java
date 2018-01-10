/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrafo;

import java.util.List;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author Mateo
 */
public class Grafo {

    int[][] matrizAdy;
    int[][] matrizPesos;

    public void preLLenado(int nVertices) {
        this.matrizAdy = new int[nVertices][nVertices];
        this.matrizPesos = new int[nVertices][nVertices];
        for (int i = 0; i < matrizAdy.length; i++) {
            for (int j = 0; j < matrizAdy.length; j++) {

                matrizPesos[i][j] = 2000;

            }
        }
    }

    public int idetentificador(String vertice) {
        if (vertice.equals("a")) {
            return 0;
        }
        if (vertice.equals("b")) {
            return 1;
        }
        if (vertice.equals("c")) {
            return 2;
        }
        if (vertice.equals("d")) {
            return 3;
        }
        if (vertice.equals("e")) {
            return 4;
        }
        if (vertice.equals("f")) {
            return 5;
        }
        System.out.println("No es un vertice permitido");
        return -1;
    }

    public void llenadoMatrices(String vinicial, String vFinal, int peso, boolean esDirigido) {
        int inicial=this.idetentificador(vinicial);
        int vfinal=this.idetentificador(vFinal);
        if (esDirigido) {
             this.matrizAdy[inicial][vfinal]=1;
             this.matrizPesos[inicial][vfinal]=peso;
        }else{
            this.matrizAdy[inicial][vfinal]=1;
            this.matrizAdy[vfinal][inicial]=1;
            this.matrizPesos[inicial][vfinal]=peso;
            this.matrizPesos[vfinal][inicial]=peso;
        }
    }
    
    public int conexo(int[][] MatrizDAdy,int nVertices){
        int[] arregloTam= new int[nVertices];
        int cont=0,vertice=-5,sum=0;
        for (int i = 0; i < matrizAdy.length; i++) {
            for (int j = 0; j < matrizAdy.length; j++) {

                if (matrizAdy[i][j]==0&&matrizAdy[j][i]==0&&cont<nVertices) {
                    arregloTam[cont]=1;
                    vertice=j;
                    cont++;
                }

            }
        }
        for (int i = 0; i < arregloTam.length; i++) {
           sum=arregloTam[i]+arregloTam[i+1];
        }
        if (sum==nVertices) {
            return vertice;
        }else{
            System.out.println("Es conexo");
        }
        return vertice;
    }
}
