package ito.app.busquedadeltesoro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuTesoro extends JFrame {
    private JPanel panelMenu = new JPanel(new GridBagLayout());
    private JPanel panelNumeroJugadores = new JPanel(new GridBagLayout());
    private JButton btnInicio = new JButton("INICIAR");
    private JButton btnSalir = new JButton("SALIR");
    private JButton btnJ2 = new JButton("JUGADORES 2");
    private JButton btnJ3 = new JButton("JUGADORES 3");
    private JButton btnJ4 = new JButton("JUGADORES 4");
    private JButton btnRegresar = new JButton("REGRESAR");

    public MenuTesoro() {
        setTitle("Caza del Tesoro");
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Dimension buttonSize = new Dimension(200, 50);
        btnInicio.setPreferredSize(buttonSize);
        btnSalir.setPreferredSize(buttonSize);
        btnJ2.setPreferredSize(buttonSize);
        btnJ3.setPreferredSize(buttonSize);
        btnJ4.setPreferredSize(buttonSize);
        btnRegresar.setPreferredSize(buttonSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelMenu.add(new JLabel("BIENVENIDO A LA CAZA DEL TESORO!!!"), gbc);

        gbc.gridy = 1;
        panelMenu.add(btnInicio, gbc);

        gbc.gridy = 2;
        panelMenu.add(btnSalir, gbc);

        add(panelMenu, BorderLayout.CENTER);

        btnInicio.addActionListener(e -> elegirNumeroJugadores());
        btnSalir.addActionListener(e -> System.exit(0));
        btnRegresar.addActionListener(e -> regresarMenuPrincipal());
        btnJ2.addActionListener(e -> iniciarJuego(2));
        btnJ3.addActionListener(e -> iniciarJuego(3));
        btnJ4.addActionListener(e -> iniciarJuego(4));

        setVisible(true);
    }

    private void elegirNumeroJugadores() {
        panelMenu.setVisible(false);
        panelNumeroJugadores.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelNumeroJugadores.add(btnJ2, gbc);

        gbc.gridy = 1;
        panelNumeroJugadores.add(btnJ3, gbc);

        gbc.gridy = 2;
        panelNumeroJugadores.add(btnJ4, gbc);

        gbc.gridy = 3;
        panelNumeroJugadores.add(btnRegresar, gbc);

        add(panelNumeroJugadores, BorderLayout.CENTER);
        panelNumeroJugadores.setVisible(true);
        revalidate();
        repaint();
    }

    private void regresarMenuPrincipal() {
        panelNumeroJugadores.setVisible(false);
        panelMenu.setVisible(true);
        revalidate();
        repaint();
    }

    private void iniciarJuego(int numeroJugadores) {
    Jugador[] jugadores = new Jugador[numeroJugadores];
    
    for (int i = 0; i < numeroJugadores; i++) {
        String rutaFicha = SelectorFicha.mostrarDialogo(this, i + 1);
        if (rutaFicha == null || rutaFicha.isEmpty()) {
            rutaFicha = "images/Ficha" + (i + 1) + ".png"; // Default si no se selecciona
        }
        jugadores[i] = new Jugador("Jugador " + (i + 1), rutaFicha);
    }

    TableroJuego pantalla = new TableroJuego(jugadores);
    pantalla.setVisible(true);
    this.setVisible(false);
}

    public static void main(String[] args) {
        new MenuTesoro();
    }
}