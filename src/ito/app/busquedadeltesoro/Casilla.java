package ito.app.busquedadeltesoro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Casilla extends JLabel {
    private int posicion;
    private Icon icono;
    private JPanel panelFichas; // Panel para mostrar múltiples fichas
    private List<JLabel> fichas; // Lista de fichas en esta casilla

    public Casilla(int posicion, String rutaImagen) {
        this.posicion = posicion;
        this.fichas = new ArrayList<>();

        // Cargar la imagen de fondo de la casilla
        ImageIcon originalIcon = new ImageIcon(rutaImagen);
        Image image = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.icono = new ImageIcon(image);
        this.setIcon(icono);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());

        // Panel para organizar las fichas
        panelFichas = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)); // Espaciado pequeño entre fichas
        panelFichas.setOpaque(false); // Hacer el panel transparente
        this.add(panelFichas, BorderLayout.CENTER); // Añadir el panel de fichas a la casilla
    }

    public void agregarFicha(JLabel ficha) {
        fichas.add(ficha); // Agregar la ficha a la lista
        panelFichas.add(ficha); // Agregar la ficha al panel
        panelFichas.revalidate();
        panelFichas.repaint();
    }

    public void eliminarFicha(JLabel ficha) {
        fichas.remove(ficha); // Eliminar la ficha de la lista
        panelFichas.remove(ficha); // Eliminar la ficha del panel
        panelFichas.revalidate();
        panelFichas.repaint();
    }

    public void limpiarFichas() {
        for (JLabel ficha : fichas) {
            panelFichas.remove(ficha); // Eliminar todas las fichas del panel
        }
        fichas.clear(); // Limpiar la lista de fichas
        panelFichas.revalidate();
        panelFichas.repaint();
    }

    public List<JLabel> getFichas() {
        return fichas;
    }
}