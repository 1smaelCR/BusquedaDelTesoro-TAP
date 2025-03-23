package ito.app.busquedadeltesoro;

import java.awt.Image;
import javax.swing.*;

public class Jugador {
    private String nombre;
    private int posicion;
    private JLabel ficha;

    public Jugador(String nombre, String rutaImagenFicha) {
        this.nombre = nombre;
        this.posicion = 0;
        ImageIcon iconoFicha = new ImageIcon(rutaImagenFicha);
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