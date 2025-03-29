package ito.app.busquedadeltesoro;

import static ito.app.busquedadeltesoro.MenuTesoro.modoFullScreen;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class PantallaFinal extends JFrame {
    private EfectosSonido winSfx = new EfectosSonido();
    
    public PantallaFinal(Jugador[] jugadores) {
        if(modoFullScreen == true){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setUndecorated(true);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLayout(new BorderLayout());
        } else {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setUndecorated(false);
            setExtendedState(JFrame.NORMAL);
            setTitle("Bob Esponja: La Búsqueda del Tesoro");
            setSize(1176, 664);
            setLocationRelativeTo(null);
        }
        
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
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        
        JButton btnVolverMenu = new JButton("Volver al menú");
        btnVolverMenu.addActionListener(e -> {
            MenuTesoro menu = new MenuTesoro();
            if(modoFullScreen == true){
                dispose();
                menu.setVisible(true);
            } else {
                dispose();
                menu.setUndecorated(false);
                menu.setExtendedState(JFrame.NORMAL);
                menu.setSize(1176, 664);
                menu.setLocationRelativeTo(null);
                modoFullScreen = false;
                menu.actualizarFondo();
                menu.setVisible(true);
            }
            menu.iniciarMusicaFondo();
        });
        
        btnVolverMenu.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolverMenu.setPreferredSize(new Dimension(180, 50));
        
        panelBoton.add(btnVolverMenu);
        panel.add(panelBoton, BorderLayout.SOUTH);
        
        add(panel);
        setVisible(true);
    }
    
    public void iniciarWinSfx() {
        winSfx.reproducirEfecto("sounds/winSound.wav");
    }
}