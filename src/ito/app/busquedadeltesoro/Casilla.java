package ito.app.busquedadeltesoro;
import javax.swing.*;
import java.awt.*;

//@authors 1smaelCR, LaLoGlitch, M4RC0S17
public class Casilla extends JLabel {
    private int posicion; // Posición en el tablero (1, 2, 3, ...)
    private Icon icono;   // Icono asociado a la casilla

    // Constructor
    public Casilla(int posicion, String rutaImagen) {
        this.posicion = posicion; // Asigna la posición

        // Reescalar la imagen al tamaño del JLabel
        ImageIcon originalIcon = new ImageIcon(rutaImagen);
        Image image = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.icono = new ImageIcon(image); // Asigna el icono reescalado

        this.setIcon(icono); // Establece el icono en el JLabel
        this.setHorizontalAlignment(SwingConstants.CENTER); // Alineación centrada
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    // Getter para la posición
    public int getPosicion() {
        return posicion;
    }

    // Setter para la posición (por si necesitas modificarla)
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    // Getter para el icono
    public Icon getIcono() {
        return icono;
    }

    // Setter para el icono (con reescalado de imagen)
    public void setIcono(String rutaImagen) {
        ImageIcon originalIcon = new ImageIcon(rutaImagen);
        Image image = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.icono = new ImageIcon(image);
        this.setIcon(icono); // Actualiza el icono en el JLabel
    }

    // Método para representar la casilla como texto (opcional, útil para depuración)
    @Override
    public String toString() {
        return "Casilla [Posición: " + posicion + "]";
    }
}
