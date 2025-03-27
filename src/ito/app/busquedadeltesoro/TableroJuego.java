package ito.app.busquedadeltesoro;
import static ito.app.busquedadeltesoro.MenuTesoro.modoFullScreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.Timer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class TableroJuego extends JFrame {
    private Musica musicaFondo = new Musica();
    private EfectosSonido dadoSfx = new EfectosSonido();
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
    public boolean juegoTerminado = false;
    
    // Temporizadores
    private Timer temporizadorGlobal;
    private Timer temporizadorTurno;
    private int tiempoRestanteGlobal = 1200; // 20 minutos en segundos
    private int tiempoRestanteTurno = 30; // 30 segundos por turno
    private JLabel etiquetaTiempoGlobal;
    private JLabel etiquetaTiempoTurno;

    public TableroJuego(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.casillas = new Casilla[100];
        this.dado = new Dado();
        this.manejadorFichas = new ManejadorFichas(this);
        inicializarUI();
        inicializarTemporizadores();
        actualizarFichas();
    }

    private void inicializarTemporizadores() {
        // Temporizador global (20 minutos)
        temporizadorGlobal = new Timer(1000, e -> {
            tiempoRestanteGlobal--;
            actualizarTiempoGlobal();
            
            if (tiempoRestanteGlobal <= 0) {
                temporizadorGlobal.stop();
                juegoTerminado = true;
                mostrarMensaje("¡Tiempo agotado! El juego ha terminado.");
                mostrarPantallaFinal();
            }
        });
        
        // Temporizador por turno (30 segundos)
        temporizadorTurno = new Timer(1000, e -> {
            tiempoRestanteTurno--;
            actualizarTiempoTurno();
            
            if (tiempoRestanteTurno <= 0) {
                temporizadorTurno.stop();
                mostrarMensaje("¡Tiempo agotado para este turno!");
                siguienteTurno();
            }
        });
    }

    private void actualizarTiempoGlobal() {
        int minutos = tiempoRestanteGlobal / 60;
        int segundos = tiempoRestanteGlobal % 60;
        etiquetaTiempoGlobal.setText(String.format("Tiempo Total: %02d:%02d", minutos, segundos));
        
        // Cambiar color cuando quede poco tiempo
        if (tiempoRestanteGlobal <= 60) {
            etiquetaTiempoGlobal.setForeground(Color.RED);
        } else if (tiempoRestanteGlobal <= 300) {
            etiquetaTiempoGlobal.setForeground(Color.ORANGE);
        }
    }

    private void actualizarTiempoTurno() {
        etiquetaTiempoTurno.setText("Tiempo Turno: " + tiempoRestanteTurno);
        
        // Cambiar color cuando queden 10 segundos
        if (tiempoRestanteTurno <= 10) {
            etiquetaTiempoTurno.setForeground(Color.RED);
        }
    }
    
    public void iniciarMusicaFondo() {
        musicaFondo.reproducirMusica("sounds/tableroSong.wav"); // Iniciar música de fondo
    }
    
    public void iniciarDadoSfx() {
        dadoSfx.reproducirEfecto("sounds/dadoSound.wav"); // Iniciar música de fondo
    }

    private void iniciarNuevoTurno() {
        tiempoRestanteTurno = 30; // Resetear a 30 segundos
        actualizarTiempoTurno();
        temporizadorTurno.start();
        botonResolverAcertijo.setEnabled(true);
        etiquetaTiempoTurno.setForeground(Color.BLUE);
    }

    private void inicializarUI() {
        setTitle("Bob Esponja: La Búsqueda del Tesoro");
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

        // Panel del tablero
        JPanel panelTablero = crearPanelTablero();
        add(panelTablero, BorderLayout.CENTER);

        // Panel de control
        JPanel panelControl = crearPanelControl();
        add(panelControl, BorderLayout.EAST);

        configurarEventos();
    }

    private JPanel crearPanelTablero() {
    JPanel panel = new JPanel(new GridLayout(10, 10));
    String rutaBase = "images/tapizCasillas.png";
    String rutaInicio = "images/inicio_1.png";
    String rutaTesoro = "images/tesoroFinal.png";
    String rutaAvanza = "images/avanza.png";  // Nueva imagen
    String rutaRetrocede = "images/retrocede.png";  // Nueva imagen

    // Generar posiciones aleatorias para casillas especiales (2-99)
    List<Integer> posiciones = new ArrayList<>();
    for(int i = 2; i < 100; i++) posiciones.add(i);
    Collections.shuffle(posiciones);

    List<Integer> avanzaPosiciones = posiciones.subList(0, 10);
    List<Integer> retrocedePosiciones = posiciones.subList(10, 20);

    for (int fila = 0; fila < 10; fila++) {
        for (int columna = 0; columna < 10; columna++) {
            int posicion = fila * 10 + columna + 1;
            Casilla casilla;
            
            if (posicion == 1) {
                casilla = new CasillaInicioFinal(posicion, rutaInicio);
            } else if (posicion == 100) {
                casilla = new CasillaInicioFinal(posicion, rutaTesoro);
            } else if (avanzaPosiciones.contains(posicion)) {
                int valor = (int)(Math.random() * 10) + 1;
                casilla = new CasillaEspecial(posicion, "avanza", valor, rutaAvanza);
            } else if (retrocedePosiciones.contains(posicion)) {
                int valor = (int)(Math.random() * 10) + 1;
                casilla = new CasillaEspecial(posicion, "retrocede", valor, rutaRetrocede);
            } else {
                casilla = new Casilla(posicion, rutaBase);
            }
            
            casillas[posicion - 1] = casilla;
            //panel.setBackground(Color.cyan);
            panel.add(casilla);
        }
    }
    return panel;
}


    private JPanel crearPanelControl() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));

        // Panel superior con información de tiempo y turno
        JPanel panelSuperior = new JPanel(new GridLayout(3, 1));
        
        // Etiqueta de turno
        etiquetaTurno = new JLabel("Turno: " + jugadores[turnoActual].getNombre(), SwingConstants.CENTER);
        etiquetaTurno.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Etiquetas de tiempo
        etiquetaTiempoGlobal = new JLabel("Tiempo Total: 20:00", SwingConstants.CENTER);
        etiquetaTiempoGlobal.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaTiempoGlobal.setForeground(Color.BLUE);
        
        etiquetaTiempoTurno = new JLabel("Tiempo Turno: 30", SwingConstants.CENTER);
        etiquetaTiempoTurno.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaTiempoTurno.setForeground(Color.BLUE);
        
        panelSuperior.add(etiquetaTurno);
        panelSuperior.add(etiquetaTiempoGlobal);
        panelSuperior.add(etiquetaTiempoTurno);
        panel.add(panelSuperior, BorderLayout.NORTH);

        // Panel del dado
        JPanel panelDado = new JPanel();
        panelDado.setPreferredSize(new Dimension(100, 100)); // Tamaño fijo
        panelDado.add(dado);
        panel.add(panelDado, BorderLayout.CENTER);

        // Área de texto
        areaAcertijo = new JTextArea(10, 25);
        areaAcertijo.setEditable(false);
        areaAcertijo.setFont(new Font("Arial", Font.PLAIN, 14));
        areaAcertijo.setBorder(BorderFactory.createTitledBorder("Eventos del Juego"));
        JScrollPane scroll = new JScrollPane(areaAcertijo);
        panel.add(scroll, BorderLayout.CENTER);
        
        
        // Panel de botones (acciones y salida)
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        botonLanzarDado = crearBoton("Lanzar Dado", Color.BLUE, Color.WHITE);
        botonResolverAcertijo = crearBoton("Resolver Acertijo", Color.GREEN.darker(), Color.WHITE);
        panelBotones.add(botonLanzarDado);
        panelBotones.add(botonResolverAcertijo);

        // Nuevos botones de Menú y Salir
        JPanel panelSalida = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton botonMenu = crearBoton("Menú Principal", new Color(255, 165, 0), Color.WHITE); // Naranja
        
        botonMenu.addActionListener(e -> regresarAlMenu());
        
        panelSalida.add(botonMenu);

        // Contenedor para ambos grupos de botones
        JPanel panelBotonesContainer = new JPanel(new GridLayout(2, 1, 10, 10));
        panelBotonesContainer.add(panelBotones);
        panelBotonesContainer.add(panelSalida);

        panel.add(panelBotonesContainer, BorderLayout.SOUTH);

        return panel;
    }

    private JButton crearBoton(String texto, Color bgColor, Color fgColor) {
        JButton boton = new JButton(texto);
        boton.setBackground(bgColor);
        boton.setForeground(fgColor);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 2),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        
        // Efecto hover
        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(bgColor.brighter());
            }
            public void mouseExited(MouseEvent e) {
                boton.setBackground(bgColor);
            }
        });
        
        return boton;
    }

    private void configurarEventos() {
        botonLanzarDado.addActionListener(e -> {
        if (!ordenDeterminado && !juegoTerminado && !dado.estaAnimando()) {
            // Mostrar panel de animación
            JDialog dialogoDado = new JDialog(this, "Lanzando dado...", false);
            dialogoDado.setLayout(new BorderLayout());
            dialogoDado.setSize(300, 300);
            dialogoDado.setLocationRelativeTo(this);
            
            Dado dadoAnimado = new Dado();
            dialogoDado.add(dadoAnimado, BorderLayout.CENTER);
            
            JLabel instruccion = new JLabel("Lanzando dado...", SwingConstants.CENTER);
            dialogoDado.add(instruccion, BorderLayout.SOUTH);
            
            dialogoDado.setVisible(true);
            iniciarDadoSfx();
            
            // Lanzar el dado
            int resultado = dadoAnimado.lanzar();
            
            // Timer para esperar animación
            Timer timerAnimacion = new Timer(100, ev -> {
                if (!dadoAnimado.estaAnimando()) {
                    ((Timer)ev.getSource()).stop();
                    
                    // Cerrar diálogo y continuar
                    dialogoDado.dispose();
                    areaAcertijo.append("Resultado: " + resultado + "\n");
                    determinarOrden(resultado);
                    ordenDeterminado = true;
                    botonLanzarDado.setEnabled(false);
                    temporizadorGlobal.start(); // Iniciar temporizador global al primer lanzamiento
                    iniciarNuevoTurno();
                    
                }
            });
            timerAnimacion.start();
        }
});
        
    botonResolverAcertijo.addActionListener(e -> {
        if (ordenDeterminado && !juegoTerminado) {
            temporizadorTurno.stop(); // Pausar temporizador de turno
            manejadorFichas.resolverAcertijo(jugadores[turnoActual]);
            actualizarFichas();
        }
    });
}

    private void determinarOrden(int resultadoDado) {
    // Mostrar resultado del primer jugador
    areaAcertijo.append(jugadores[0].getNombre() + " sacó: " + resultadoDado + "\n");
    
    // Lanzar dado para el segundo jugador
    int resultadoDadoJugador2 = dado.lanzar();
    areaAcertijo.append(jugadores[1].getNombre() + " sacó: " + resultadoDadoJugador2 + "\n");
    
    // Determinar quién comienza primero
    if (resultadoDado == resultadoDadoJugador2) {
        // Empate - lanzar nuevamente
        areaAcertijo.append("¡Empate! Lanzando nuevamente...\n");
        determinarOrden(dado.lanzar());
        return;
    }
    
    turnoActual = (resultadoDado > resultadoDadoJugador2) ? 0 : 1;
    actualizarTurno();
    areaAcertijo.append("¡" + jugadores[turnoActual].getNombre() + " comienza primero!\n");
}

    public void moverFicha(Jugador jugador, int pasos) {
        if (juegoTerminado || pasos <= 0) return;
        
        int posicionActual = jugador.getPosicion();
        int nuevaPosicion = Math.min(posicionActual + pasos, 100);
        
        animarMovimiento(jugador, posicionActual, nuevaPosicion);
    }

    public void actualizarFichas() {
        // Limpiar todas las casillas
        for (Casilla casilla : casillas) {
            if (casilla != null) {
                casilla.limpiarFichas();
            }
        }

        // Colocar fichas en sus posiciones actuales
        for (Jugador jugador : jugadores) {
            int pos = jugador.getPosicion();
            if (pos > 0 && pos <= 100) {
                casillas[pos - 1].agregarFicha(jugador.getFicha());
            }
        }
    }

   public void animarMovimiento(Jugador jugador, int desde, int hasta) {
    if (desde == hasta) return;
    
    JLabel ficha = jugador.getFicha();
    Casilla casillaOrigen = casillas[desde - 1];
    Casilla casillaDestino = casillas[hasta - 1];
    
    Point puntoOrigen = casillaOrigen.getLocation();
    Point puntoDestino = casillaDestino.getLocation();
    
    Timer timer = new Timer(30, new ActionListener() {
        private int paso = 0;
        private final int totalPasos = 10;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (paso >= totalPasos) {
                ((Timer)e.getSource()).stop();
                jugador.setPosicion(hasta);
                actualizarFichas();
                
                if (hasta == 100) {
                    // Lógica del acertijo final
                    resolverAcertijoFinal(jugador);
                }
                return;
            }
            
            double progreso = (double)paso / totalPasos;
            int x = (int)(puntoOrigen.x + (puntoDestino.x - puntoOrigen.x) * progreso);
            int y = (int)(puntoOrigen.y + (puntoDestino.y - puntoOrigen.y) * progreso);
            
            ficha.setLocation(x, y);
            
            if (paso < totalPasos / 2) {
                ficha.setLocation(x, y - (int)(10 * Math.sin(Math.PI * progreso)));
            }
            
            paso++;
        }
    });
    
    casillaOrigen.eliminarFicha(ficha);
    getContentPane().add(ficha);
    ficha.setLocation(puntoOrigen);
    timer.start();
}

private void resolverAcertijoFinal(Jugador jugador) {
    // Deshabilitar botones durante el acertijo final
    botonLanzarDado.setEnabled(false);
    botonResolverAcertijo.setEnabled(false);
    
    // Mostrar mensaje especial
    areaAcertijo.setText("¡ACERTIJO FINAL!\n" +
                        jugador.getNombre() + " ha llegado al tesoro.\n" +
                        "Resuelve este acertijo para ganar:");
    
    // Obtener y mostrar acertijo
    Acertijo acertijoFinal = manejadorFichas.obtenerAcertijoAleatorio();
    areaAcertijo.append("\n\n" + acertijoFinal.getPregunta());
    
    // Pedir respuesta
    String respuesta = JOptionPane.showInputDialog(this, 
        "Acertijo final para ganar:\n" + acertijoFinal.getPregunta(), 
        "¡Tesoro encontrado!", 
        JOptionPane.QUESTION_MESSAGE);
    
    if (acertijoFinal.verificarRespuesta(respuesta)) {
        // Respuesta correcta
        juegoTerminado = true;
        mostrarMensaje("¡CORRECTO! " + jugador.getNombre() + " ha ganado el juego!");
        mostrarPantallaFinal();
    } else {
        // Respuesta incorrecta
        mostrarMensaje("¡Incorrecto! " + jugador.getNombre() + " pierde el turno.");
        jugador.setPosicion(95); // Retroceder 5 casillas
        actualizarFichas();
        siguienteTurno();
        
        // Rehabilitar botones para siguiente jugador
        botonResolverAcertijo.setEnabled(true);
    }
}

    private void mostrarPantallaFinal() {
        Arrays.sort(jugadores, (j1, j2) -> Integer.compare(j2.getPosicion(), j1.getPosicion()));
        
        SwingUtilities.invokeLater(() -> {
            new PantallaFinal(jugadores);
            this.dispose();
        });
    }

   public void siguienteTurno() {
    if (juegoTerminado) return;
    
    temporizadorTurno.stop();
    turnoActual = (turnoActual + 1) % jugadores.length;
    actualizarTurno();
    
    // Mostrar mensaje del cambio de turno
    areaAcertijo.append("\nTurno de " + jugadores[turnoActual].getNombre() + "\n");
    
    iniciarNuevoTurno();
}

    private void actualizarTurno() {
        etiquetaTurno.setText("Turno: " + jugadores[turnoActual].getNombre());
        etiquetaTurno.setForeground(Color.BLUE);
        
        Timer timer = new Timer(100, e -> {
            etiquetaTurno.setForeground(Color.BLACK);
            ((Timer)e.getSource()).stop();
        });
        timer.start();
    }

    public void mostrarAcertijo(String pregunta) {
        areaAcertijo.setText("ACERTIJO:\n" + pregunta + "\n\nIngresa tu respuesta:");
    }

    public String obtenerRespuesta() {
        return JOptionPane.showInputDialog(this, "Resuelve el acertijo:", "Turno de " + jugadores[turnoActual].getNombre(), JOptionPane.QUESTION_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        areaAcertijo.append("\n" + mensaje + "\n");
    }
    
    private void regresarAlMenu() {
        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Volver al menú principal? Se perderá el progreso actual.",
            "Confirmar",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
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
            musicaFondo.detenerMusica();
            menu.iniciarMusicaFondo();
        }
    }

}