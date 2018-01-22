/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrafo;

/**
 *
 * @author Mateo
 */
public class MiGrafo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Grafo graf= new Grafo();
//        graf.preLLenado(6);
//        graf.llenadoMatrices("a", "b", 0, false);
//        graf.llenadoMatrices("a", "f", 0, false);
//        graf.llenadoMatrices("a", "e", 0, false);
//        graf.llenadoMatrices("b", "d", 0, false);
//        graf.llenadoMatrices("b", "e", 0, false);
//        graf.llenadoMatrices("d", "e", 0, false);
//        graf.llenadoMatrices("e", "f", 0, false);
//        //graf.llenadoMatrices("c", "b", 0, false);
//        graf.imprimirMatrizAdy();
//        System.out.println("__________________");
//        //graf.imprimirMatrizPesos();
//        graf.conexo(6);//Recalcular
//        graf.Grado();//Bien.
        
        Grafo graRecoridos= new Grafo();
        graRecoridos.preLLenado(6);
        graRecoridos.llenadoMatrices("a", "b", 0, false);
        graRecoridos.llenadoMatrices("a", "d", 0, false);
        graRecoridos.llenadoMatrices("a", "e", 0, false);
        graRecoridos.llenadoMatrices("b", "c", 0, false);
        graRecoridos.llenadoMatrices("b", "e", 0, false);
        graRecoridos.llenadoMatrices("c", "d", 0, false);
        graRecoridos.llenadoMatrices("c", "f", 0, false);
        graRecoridos.llenadoMatrices("d", "e", 0, false);
        graRecoridos.llenadoMatrices("d", "f", 0, false);
        graRecoridos.llenadoMatrices("e", "f", 0, false);
        graRecoridos.imprimirMatrizAdy();
        graRecoridos.anchura("a");
        graRecoridos.profundidad("a");
    }
    
}
