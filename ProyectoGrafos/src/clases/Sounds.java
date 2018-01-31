/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Mateo
 */
public class Sounds{

    
    public static void fondoSound(String ruta) {
        try {

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(ruta));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            //Thread.sleep(5000);
        } catch (Exception e) {
        }
    }
    public static void radioSound(String ruta) {
        try {

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/Sonido/radio.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
            //Thread.sleep(5000);
        } catch (Exception e) {
        }
    }
    
    public static void proyectilSound(){
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/Sonido/disparo.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
        }
    }


    /**
     *
     * @param ruta
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void rep(String ruta) throws FileNotFoundException, IOException {
        InputStream in = new FileInputStream(ruta);
        AudioStream audio = new AudioStream(in);
        AudioPlayer.player.start(audio);
    }


}
