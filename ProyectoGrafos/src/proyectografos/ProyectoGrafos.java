/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectografos;

import java.io.*;

import com.google.gson.*;
//xml
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;         // |
import org.jdom2.Element;          // |\ Librerías
import org.jdom2.JDOMException;    // |/ JDOM
import org.jdom2.input.SAXBuilder; // |

/**
 *
 * @author Mateo
 */
public class ProyectoGrafos {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
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
       /* JsonParser parser = new JsonParser();
        FileReader fr = new FileReader("src/proyectografos/persona.json");
        JsonElement datos = parser.parse(fr);
        dumpJSONElement(datos);*/
       cargarXml();

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
    public static void cargarXml()
{
    //Se crea un SAXBuilder para poder parsear el archivo
    SAXBuilder builder = new SAXBuilder();
    File xmlFile = new File( "src/proyectografos/persona.xml");
    try
    {
//        //Se crea el documento a traves del archivo
//        Document document = (Document) builder.build( xmlFile );
// 
//        //Se obtiene la raiz 'tables'
//        Element rootNode = document.getRootElement();
// 
//        //Se obtiene la lista de hijos de la raiz 'tables'
//        List list = rootNode.getChildren( "tabla" );
// 
//        //Se recorre la lista de hijos de 'tables'
//        for ( int i = 0; i < list.size(); i++ )
//        {
//            //Se obtiene el elemento 'tabla'
//            Element tabla = (Element) list.get(i);
// 
//            //Se obtiene el atributo 'nombre' que esta en el tag 'tabla'
//            String nombreTabla = tabla.getAttributeValue("nombre");
// 
//            System.out.println( "Tabla: " + nombreTabla );
// 
//            //Se obtiene la lista de hijos del tag 'tabla'
//            List lista_campos = tabla.getChildren();
// 
//            System.out.println( "\tNombre\t\tTipo\t\tValor" );
// 
//            //Se recorre la lista de campos
//            for ( int j = 0; j < lista_campos.size(); j++ )
//            {
//                //Se obtiene el elemento 'campo'
//                Element campo = (Element)lista_campos.get( j );
//         
//                //Se obtienen los valores que estan entre los tags '<campo></campo>'
//                //Se obtiene el valor que esta entre los tags '<nombre></nombre>'
//                String nombre = campo.getChildTextTrim("nombre");
// 
//                //Se obtiene el valor que esta entre los tags '<tipo></tipo>'
//                String tipo = campo.getChildTextTrim("tipo");
// 
//                //Se obtiene el valor que esta entre los tags '<valor></valor>'
//                String valor = campo.getChildTextTrim("valor");
// 
//                System.out.println( "\t"+nombre+"\t\t"+tipo+"\t\t"+valor);
//            }
//        }
     Document document = (Document) builder.build( xmlFile );
     Element rootNode = document.getRootElement();
     List list = rootNode.getChildren( "Nombre" );
        for (int i = 0; i < list.size(); i++) {
            Element nombre = (Element) list.get(i);
            String nombrep=nombre.getValue();
            
            System.out.println(nombrep);
          
        }
        List list2 = rootNode.getChildren( "Apellido" );
        for (int i = 0; i < list2.size(); i++) {
            Element apellido = (Element) list2.get(i);
            String nombrep=apellido.getAttributeValue("apellido");
            
            System.out.println(nombrep);
          
        }
     
    }catch ( IOException io ) {
        System.out.println( io.getMessage() );
    }catch ( JDOMException jdomex ) {
        System.out.println( jdomex.getMessage() );
    }
}
}
