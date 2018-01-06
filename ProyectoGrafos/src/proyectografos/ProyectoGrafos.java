/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectografos;

import java.io.*;

import com.google.gson.*;



/**
 *
 * @author Mateo
 */
public class ProyectoGrafos {

    /**
     * @param args the command line arguments
     */
    /*public static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
        }
        b.close();
    }

    public static void main(String[] args) throws IOException {
        muestraContenido("src/proyectografos/persona.json");
    
    }*/
    public static void main(String args[]) throws java.io.IOException {
        JsonParser parser = new JsonParser();
        FileReader fr = new FileReader("src/proyectografos/persona.json");
        JsonElement datos = parser.parse(fr);
        dumpJSONElement(datos);
    }
    public static void dumpJSONElement(JsonElement elemento) {
    if (elemento.isJsonObject()) {
        System.out.println("Es objeto");
        JsonObject obj = elemento.getAsJsonObject();
        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
        while (iter.hasNext()) {
            java.util.Map.Entry<String,JsonElement> entrada = iter.next();
            System.out.println("Clave: " + entrada.getKey());
            System.out.println("Valor:");
            dumpJSONElement(entrada.getValue());
        }
 
    } else if (elemento.isJsonArray()) {
        JsonArray array = elemento.getAsJsonArray();
        System.out.println("Es array. Numero de elementos: " + array.size());
        java.util.Iterator<JsonElement> iter = array.iterator();
        while (iter.hasNext()) {
            JsonElement entrada = iter.next();
            dumpJSONElement(entrada);
        }
    } else if (elemento.isJsonPrimitive()) {
        System.out.println("Es primitiva");
        JsonPrimitive valor = elemento.getAsJsonPrimitive();
        if (valor.isBoolean()) {
            System.out.println("Es booleano: " + valor.getAsBoolean());
        } else if (valor.isNumber()) {
            System.out.println("Es numero: " + valor.getAsNumber());
        } else if (valor.isString()) {
            System.out.println("Es texto: " + valor.getAsString());
        }
    } else if (elemento.isJsonNull()) {
        System.out.println("Es NULL");
    } else {
        System.out.println("Es otra cosa");
    }
}
}
