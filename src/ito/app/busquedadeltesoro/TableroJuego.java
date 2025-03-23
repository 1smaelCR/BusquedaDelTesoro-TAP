package ito.app.busquedadeltesoro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableroJuego extends JFrame {
    private Jugador[] jugadores;
    private int turnoActual = 0;
    private Casilla[] casillas;
    private Dado dado;
    private JLabel etiquetaTurno;
    private JTextArea areaAcertijo;
    private JButton botonLanzarDado;
    private JButton botonResolverAcertijo;
    private ManejadorFichas manejadorFichas;
    private boolean ordenDeterminado = false;

    public TableroJuego(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.casillas = new Casilla[100];
        this.dado = new Dado();
        this.manejadorFichas = new ManejadorFichas(this);
        inicializarUI();
    }

    private void inicializarUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        // Panel del tablero
        JPanel panelTablero = new JPanel(new GridLayout(10, 10));
        String rutaImagen = "images/tapizCasilla.jpg";
        String rutaImagenInicio = "images/inicio.png";
        String rutaImagenFinal = "images/tesoro.jpg";

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                int posicion = fila * 10 + columna + 1;
                Casilla casilla;
                if (posicion == 1) {
                    casilla = new CasillaInicioFinal(posicion, rutaImagenInicio);
                } else if (posicion == 100) {
                    casilla = new CasillaInicioFinal(posicion, rutaImagenFinal);
                } else {
                    casilla = new Casilla(posicion, rutaImagen);
                }
                casillas[posicion - 1] = casilla;
                panelTablero.add(casilla);
            }
        }

        // Panel de control
        JPanel panelControl = new JPanel(new BorderLayout());
        panelControl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dado
        JPanel panelDado = new JPanel();
        panelDado.add(dado);
        panelControl.add(panelDado, BorderLayout.NORTH);

        // Área de acertijos
        areaAcertijo = new JTextArea(5, 20);
        areaAcertijo.setEditable(false);
        areaAcertijo.setLineWrap(true);
        areaAcertijo.setWrapStyleWord(true);
        JScrollPane scrollAcertijo = new JScrollPane(areaAcertijo);
        panelControl.add(scrollAcertijo, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        botonLanzarDado = new JButton("Lanzar Dado para Orden");
        botonResolverAcertijo = new JButton("Resolver Acertijo");
        panelBotones.add(botonLanzarDado);
        panelBotones.add(botonResolverAcertijo);
        panelControl.add(panelBotones, BorderLayout.SOUTH);

        // Etiqueta de turno
        etiquetaTurno = new JLabel("Turno de: " + jugadores[turnoActual].getNombre());
        panelControl.add(etiquetaTurno, BorderLayout.NORTH);

        // Añadir paneles al JFrame
        this.add(panelTablero, BorderLayout.CENTER);
        this.add(panelControl, BorderLayout.EAST);

        // Configurar acciones de los botones
        botonLanzarDado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ordenDeterminado) {
                    int resultadoDado = dado.lanzar();
                    areaAcertijo.setText("Lanzaste un " + resultadoDado + ".\n");
                    determinarOrden(resultadoDado);
                    ordenDeterminado = true;
                    botonLanzarDado.setEnabled(false);
                }
            }
        });

        botonResolverAcertijo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejadorFichas.resolverAcertijo(jugadores[turnoActual]);
                actualizarFichas(); // Redibujar las fichas después de resolver el acertijo
            }
        });

        this.setVisible(true);
    }

    private void determinarOrden(int resultadoDado) {
        if (resultadoDado > 3) {
            turnoActual = 0;
        } else {
            turnoActual = 1;
        }
        etiquetaTurno.setText("Turno de: " + jugadores[turnoActual].getNombre());
        areaAcertijo.append("El orden de los turnos ha sido determinado.\n");
    }

    public void moverFicha(Jugador jugador, int nuevaPosicion) {
        int posicionActual = jugador.getPosicion();

        // Eliminar la ficha del jugador de la casilla actual (si está en una casilla)
        if (posicionActual > 0) {
            Casilla casillaActual = casillas[posicionActual - 1];
            casillaActual.eliminarFicha(jugador.getFicha());
        }

        // Mover al jugador a la nueva posición
        jugador.setPosicion(nuevaPosicion);

        // Agregar la ficha del jugador a la nueva casilla
        Casilla nuevaCasilla = casillas[nuevaPosicion - 1];
        nuevaCasilla.agregarFicha(jugador.getFicha());

        // Mostrar mensaje en el área de texto
        mostrarMensaje(jugador.getNombre() + " avanzó a la posición " + nuevaPosicion);
    }

    public void actualizarFichas() {
        // Limpiar todas las fichas del tablero
        for (Casilla casilla : casillas) {
            casilla.limpiarFichas();
        }

        // Volver a colocar las fichas en sus posiciones actuales
        for (Jugador jugador : jugadores) {
            int posicion = jugador.getPosicion();
            if (posicion > 0) {
                Casilla casilla = casillas[posicion - 1];
                casilla.agregarFicha(jugador.getFicha());
            }
        }
    }

    public void siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.length;
        etiquetaTurno.setText("Turno de: " + jugadores[turnoActual].getNombre());
        actualizarFichas(); // Redibujar las fichas en cada turno
    }

    public void mostrarAcertijo(String pregunta) {
        areaAcertijo.setText("Acertijo: " + pregunta + "\n");
    }

    public String obtenerRespuesta() {
        return JOptionPane.showInputDialog(this, "Resuelve el acertijo:", "Acertijo", JOptionPane.QUESTION_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        areaAcertijo.append(mensaje + "\n");
    }
}