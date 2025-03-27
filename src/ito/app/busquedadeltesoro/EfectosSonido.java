package ito.app.busquedadeltesoro;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class EfectosSonido {
    private Clip clip;

    public void reproducirEfecto(String rutaArchivo) {
        try {
            detenerEfecto();
            File archivo = new File(rutaArchivo);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            clip.setFramePosition(0); // Reiniciar al principio
            clip.start(); // Iniciar reproducción
            
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de audio no soportado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de audio: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Dispositivo de audio no disponible: " + e.getMessage());
        }
    }

    public void detenerEfecto() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
