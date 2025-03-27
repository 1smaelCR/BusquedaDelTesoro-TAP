package ito.app.busquedadeltesoro;
import javax.swing.*;
import java.awt.*;

public class Casilla extends JPanel {
    private int numero;
    private ImageIcon imagenFondo;
    private JLabel fichaActual;  // Aquí está la referencia a la ficha actual
    private JLabel labelNumero;

    public Casilla(int numero, String rutaImagen) {
        this.numero = numero;
        this.imagenFondo = new ImageIcon(rutaImagen);
        this.fichaActual = null;
        setLayout(new OverlayLayout(this));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        
        // Label para mostrar el número de casilla
        labelNumero = new JLabel(String.valueOf(numero), SwingConstants.CENTER);
        labelNumero.setFont(new Font("Arial", Font.BOLD, 20));
        labelNumero.setForeground(Color.WHITE);
        labelNumero.setOpaque(false);
        add(labelNumero);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Métodos para manejar fichas:
    public void agregarFicha(JLabel ficha) {
        if (fichaActual != null) {
            remove(fichaActual);  // Elimina la ficha anterior si existe
        }
        fichaActual = ficha;
        add(ficha);  // Añade la nueva ficha
        revalidate();
        repaint();
    }

    public void eliminarFicha(JLabel ficha) {
        if (fichaActual != null && fichaActual.equals(ficha)) {
            remove(ficha);
            fichaActual = null;
            revalidate();
            repaint();
        }
    }

    public void limpiarFichas() {
        if (fichaActual != null) {
            remove(fichaActual);
            fichaActual = null;
            revalidate();
            repaint();
        }
    }

    // Getters y setters
    public int getNumero() {
        return numero;
    }

    public JLabel getFichaActual() {
        return fichaActual;
    }
}