package ito.app.busquedadeltesoro;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Dado extends JPanel {
    private final ImageIcon[] carasDado;
    private final Timer animacion;
    private int resultadoFinal;
    private int contadorAnimacion;
    private final Random random = new Random();
    private JLabel caraVisible;

    public Dado() {
        setLayout(new BorderLayout());
        setOpaque(false);
        
        carasDado = new ImageIcon[6];
        cargarImagenesDado(); // Llamada al método
        
        caraVisible = new JLabel(carasDado[0], SwingConstants.CENTER);
        caraVisible.setHorizontalAlignment(SwingConstants.CENTER);
        caraVisible.setVerticalAlignment(SwingConstants.CENTER);
        add(caraVisible, BorderLayout.CENTER);
        
        animacion = new Timer(100, e -> {
            if (contadorAnimacion < 15) {
                caraVisible.setIcon(carasDado[random.nextInt(6)]);
                contadorAnimacion++;
            } else {
                ((Timer)e.getSource()).stop(); // Detenemos el timer directamente
                caraVisible.setIcon(carasDado[resultadoFinal - 1]);
            }
        });
    }

    // Método para cargar las imágenes del dado
    private void cargarImagenesDado() {
        try {
            carasDado[0] = new ImageIcon("images/dado1.png");
            carasDado[1] = new ImageIcon("images/dado2.png");
            carasDado[2] = new ImageIcon("images/dado3.png");
            carasDado[3] = new ImageIcon("images/dado4.png");
            carasDado[4] = new ImageIcon("images/dado5.png");
            carasDado[5] = new ImageIcon("images/dado6.png");
            
            // Escalar imágenes si es necesario
            for (int i = 0; i < 6; i++) {
                if (carasDado[i] != null) {
                    Image img = carasDado[i].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    carasDado[i] = new ImageIcon(img);
                }
            }
        } catch (Exception e) {
            System.err.println("Error cargando imágenes del dado: " + e.getMessage());
            crearDadosAlternativos(); // Si hay error, creamos dados alternativos
        }
    }

    // Método para crear dados alternativos si no se cargan las imágenes
    private void crearDadosAlternativos() {
        for (int i = 0; i < 6; i++) {
            BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = img.createGraphics();
            
            // Fondo blanco con borde
            g.setColor(Color.WHITE);
            g.fillRoundRect(0, 0, 100, 100, 20, 20);
            g.setColor(Color.BLACK);
            g.drawRoundRect(0, 0, 99, 99, 20, 20);
            
            // Puntos del dado
            g.setColor(Color.BLACK);
            dibujarPuntos(g, i+1);
            g.dispose();
            carasDado[i] = new ImageIcon(img);
        }
    }

    // Método auxiliar para dibujar los puntos del dado
    private void dibujarPuntos(Graphics2D g, int valor) {
        switch (valor) {
            case 1: dibujarPunto(g, 50, 50); break;
            case 2: dibujarPunto(g, 30, 30); dibujarPunto(g, 70, 70); break;
            case 3: dibujarPunto(g, 30, 30); dibujarPunto(g, 50, 50); dibujarPunto(g, 70, 70); break;
            case 4: dibujarPunto(g, 30, 30); dibujarPunto(g, 70, 30); 
                    dibujarPunto(g, 30, 70); dibujarPunto(g, 70, 70); break;
            case 5: dibujarPunto(g, 30, 30); dibujarPunto(g, 70, 30); 
                    dibujarPunto(g, 50, 50); 
                    dibujarPunto(g, 30, 70); dibujarPunto(g, 70, 70); break;
            case 6: dibujarPunto(g, 30, 25); dibujarPunto(g, 70, 25); 
                    dibujarPunto(g, 30, 50); dibujarPunto(g, 70, 50); 
                    dibujarPunto(g, 30, 75); dibujarPunto(g, 70, 75); break;
        }
    }

    // Método auxiliar para dibujar un punto individual
    private void dibujarPunto(Graphics2D g, int x, int y) {
        g.fillOval(x-10, y-10, 20, 20);
    }

    public int lanzar() {
        resultadoFinal = random.nextInt(6) + 1;
        contadorAnimacion = 0;
        animacion.start();
        return resultadoFinal;
    }

    public boolean estaAnimando() {
        return animacion.isRunning();
    }
}