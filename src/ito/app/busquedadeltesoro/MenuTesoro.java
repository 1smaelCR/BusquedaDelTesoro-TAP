package ito.app.busquedadeltesoro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuTesoro extends JFrame {
    private JPanel panelMenu = new JPanel(new GridBagLayout()); // Panel del menú principal
    private JPanel panelNumeroJugadores = new JPanel(new GridBagLayout()); // Panel de selección de jugadores
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

        // Acción del botón INICIAR
        btnInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirNumeroJugadores();
            }
        });

        // Acción del botón SALIR
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Acción del botón REGRESAR
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarMenuPrincipal();
            }
        });

        // Acción del botón JUGADORES 2
        btnJ2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego(2); // Iniciar juego con 2 jugadores
            }
        });

        // Acción del botón JUGADORES 3
        btnJ3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego(3); // Iniciar juego con 3 jugadores
            }
        });

        // Acción del botón JUGADORES 4
        btnJ4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego(4); // Iniciar juego con 4 jugadores
            }
        });

        setVisible(true);
    }

    // Método para mostrar el panel de selección de número de jugadores
    public void elegirNumeroJugadores() {
        panelMenu.setVisible(false);
        panelNumeroJugadores.removeAll(); // Limpiar el panel antes de agregar componentes

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

    // Método para regresar al menú principal
    public void regresarMenuPrincipal() {
        panelNumeroJugadores.setVisible(false);
        panelMenu.setVisible(true);
        revalidate(); // Actualizar la interfaz
        repaint(); // Volver a dibujar
    }

    // Método para iniciar el juego con el número de jugadores seleccionado
 public void iniciarJuego(int numeroJugadores) {
    Jugador[] jugadores = new Jugador[numeroJugadores];
    for (int i = 0; i < numeroJugadores; i++) {
        // Cambiamos la extensión de .jpg a .png
        jugadores[i] = new Jugador("Jugador " + (i + 1), "images/Ficha" + (i + 1) + ".jpg");
    }

    // Crear y mostrar el tablero de juego
    TableroJuego pantalla = new TableroJuego(jugadores);
    pantalla.setVisible(true);

    // Ocultar el menú
    this.setVisible(false);
}

    public static void main(String[] args) {
        new MenuTesoro();
    }
}