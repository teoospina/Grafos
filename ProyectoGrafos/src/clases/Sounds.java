/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.io.*;
import sun.audio.*;

/**
 *
 * @author Mateo
 */
public class Sounds {

    /**
     *
     * @param ruta
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void rep(String ruta) throws FileNotFoundException, IOException{
       InputStream in= new FileInputStream(ruta);
       AudioStream audio= new AudioStream(in);
       AudioPlayer.player.start(audio);
    }
        
    }
