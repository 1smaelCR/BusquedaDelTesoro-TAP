package ito.app.busquedadeltesoro;

import javax.swing.*;
import java.awt.*;

public class Casilla extends JLabel {
    private int posicion;
    private Icon icono;
    private JLabel ficha;

    public Casilla(int posicion, String rutaImagen) {
        this.posicion = posicion;
        ImageIcon originalIcon = new ImageIcon(rutaImagen);
        Image image = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.icono = new ImageIcon(image);
        this.setIcon(icono);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());
    }

    public void setFicha(JLabel ficha) {
        if (this.ficha != null) {
            this.remove(this.ficha);
        }
        this.ficha = ficha;
        this.add(ficha, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void eliminarFicha() {
        if (this.ficha != null) {
            this.remove(this.ficha);
            this.ficha = null;
            this.revalidate();
            this.repaint();
        }
    }
}