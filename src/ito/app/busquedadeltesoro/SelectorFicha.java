package ito.app.busquedadeltesoro;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class SelectorFicha {
    private static final String[] RUTAS_FICHAS = {
        "images/Ficha1.png", "images/Ficha2.png", 
        "images/Ficha3.png", "images/Ficha4.png"
    };
    private static String seleccion = "";

    public static String mostrarDialogo(Component parent, int numeroJugador) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Estilo profesional para los botones
        Border hoverBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );

        for (int i = 0; i < 4; i++) {
            JButton btn = crearBotonFicha(RUTAS_FICHAS[i], hoverBorder, i);
            panel.add(btn);
        }

        JOptionPane.showOptionDialog(
            parent, panel, "Jugador " + numeroJugador + ": Elige tu personaje",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, new Object[]{}, null
        );

        return seleccion.isEmpty() ? RUTAS_FICHAS[numeroJugador - 1] : seleccion;
    }

    private static JButton crearBotonFicha(String ruta, Border hoverBorder, int index) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image img = iconoOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JButton btn = new JButton(new ImageIcon(img));
        
        // Estilo normal
        btn.setBackground(Color.WHITE);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBorder(hoverBorder);
                btn.setBackground(new Color(230, 240, 255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                btn.setBackground(Color.WHITE);
            }
        });

        // Efecto click
        btn.addActionListener(e -> {
            seleccion = ruta;
            // Animación de selección
            Timer timer = new Timer(50, new ActionListener() {
                float opacity = 1.0f;
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (opacity <= 0.3f) {
                        ((Timer)evt.getSource()).stop();
                        Window window = SwingUtilities.getWindowAncestor(btn);
                        window.dispose();
                    } else {
                        opacity -= 0.1f;
                        btn.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(70, 130, 180, (int)(opacity * 255)), 3),
                            BorderFactory.createEmptyBorder(8, 8, 8, 8)
                        ));
                    }
                }
            });
            timer.start();
        });

        return btn;
    }
}