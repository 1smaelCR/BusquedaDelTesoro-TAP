package ito.app.busquedadeltesoro;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musica {
    private Clip clip;

    public void reproducirMusica(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de audio no soportado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de audio: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Dispositivo de audio no disponible: " + e.getMessage());
        }
    }

    public void detenerMusica() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
