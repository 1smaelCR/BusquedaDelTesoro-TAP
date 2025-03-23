package ito.app.busquedadeltesoro;

import javax.swing.*;
import java.awt.Image;

public class Jugador {
    private String nombre;
    private int posicion;
    private JLabel ficha;

    public Jugador(String nombre, String rutaImagenFicha) {
        this.nombre = nombre;
        this.posicion = 0;

        // Cargar la imagen de la ficha
        ImageIcon iconoFicha = new ImageIcon(rutaImagenFicha);
        if (iconoFicha.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
            System.err.println("Error: No se pudo cargar la imagen de la ficha: " + rutaImagenFicha);
        }

        // Reescalar la imagen al tamaño deseado (30x30 píxeles)
        Image imagenFicha = iconoFicha.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        this.ficha = new JLabel(new ImageIcon(imagenFicha));
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public JLabel getFicha() {
        return ficha;
    }
}