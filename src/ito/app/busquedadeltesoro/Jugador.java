package ito.app.busquedadeltesoro;

import javax.swing.*;
import java.awt.Image;

public class Jugador {
    private String nombre;
    private int posicion;
    private JLabel ficha;
    private String rutaFicha;

    public Jugador(String nombre, String rutaFicha) {
        this.nombre = nombre;
        this.posicion = 1;
        this.rutaFicha = rutaFicha;
        actualizarFicha();
    }

    private void actualizarFicha() {
        ImageIcon iconoFicha = new ImageIcon(rutaFicha);
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

    public void setRutaFicha(String rutaFicha) {
        this.rutaFicha = rutaFicha;
        actualizarFicha();
    }
}