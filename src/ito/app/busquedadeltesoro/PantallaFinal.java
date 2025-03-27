package ito.app.busquedadeltesoro;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class PantallaFinal extends JFrame {
    public PantallaFinal(Jugador[] jugadores) {
        setTitle("¡Juego Terminado!");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        Arrays.sort(jugadores, Comparator.comparingInt(Jugador::getPosicion).reversed());
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titulo = new JLabel("RESULTADOS FINALES", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titulo, BorderLayout.NORTH);
        
        JPanel panelJugadores = new JPanel(new GridLayout(jugadores.length, 1, 10, 10));
        for (Jugador jugador : jugadores) {
            JPanel panelJugador = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
            
            JLabel labelFicha = new JLabel(new ImageIcon(
                ((ImageIcon)jugador.getFicha().getIcon()).getImage()
                    .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
            ));
            
            String texto = jugador.getNombre() + " - Posición: " + jugador.getPosicion();
            if (jugador.getPosicion() == 100) {
                texto += " ¡GANADOR!";
                panelJugador.setBackground(new Color(255, 215, 0)); // Fondo dorado
            }
            
            JLabel labelTexto = new JLabel(texto);
            labelTexto.setFont(new Font("Arial", Font.PLAIN, 18));
            
            panelJugador.add(labelFicha);
            panelJugador.add(labelTexto);
            panelJugadores.add(panelJugador);
        }
        
        JScrollPane scroll = new JScrollPane(panelJugadores);
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        
        JButton btnReiniciar = new JButton("Reiniciar Juego");
        btnReiniciar.addActionListener(e -> {
            new MenuTesoro();
            dispose();
        });
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));
        
        btnReiniciar.setFont(new Font("Arial", Font.BOLD, 16));
        btnReiniciar.setPreferredSize(new Dimension(180, 50));
        btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalir.setPreferredSize(new Dimension(180, 50));
        
        panelBotones.add(btnReiniciar);
        panelBotones.add(btnSalir);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        add(panel);
        setVisible(true);
    }
}