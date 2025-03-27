package ito.app.busquedadeltesoro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuTesoro extends JFrame {
    public static boolean modoFullScreen = true;
    private JPanel panelMenu = new JPanel(new GridBagLayout());
    private JPanel panelNumeroJugadores = new JPanel(new GridBagLayout());
    private Musica musicaFondo = new Musica();
    private ImageIcon imgFondo = new ImageIcon("fonts/fondomenu.jpg");
    private JLabel fondo = new JLabel(imgFondo);
    private JButton btnInicio = new JButton();
    private JButton btnSalir = new JButton();
    private JButton btnConfigVentana = new JButton();
    private JButton btnJ2 = new JButton();
    private JButton btnJ3 = new JButton();
    private JButton btnJ4 = new JButton();
    private JButton btnRegresar = new JButton();

    public MenuTesoro() {
        setTitle("Bob Esponja: La Búsqueda del Tesoro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);

        fondo.setLayout(new BorderLayout());
        Image fondoEscalado = imgFondo.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        fondo.setIcon(new ImageIcon(fondoEscalado));

        setContentPane(fondo);
        panelMenu.setOpaque(false);

        Dimension buttonSize = new Dimension(300, 100);
        Dimension buttonConfigSize = new Dimension(50, 50);

        btnInicio.setPreferredSize(buttonSize);
        btnSalir.setPreferredSize(buttonSize);
        btnConfigVentana.setPreferredSize(buttonConfigSize);
        
        ImageIcon imgInicio = new ImageIcon("images/playbutt.jpg");
        Image imgInicioRedi = imgInicio.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        btnInicio.setIcon(new ImageIcon(imgInicioRedi));
        
        ImageIcon imgSalir = new ImageIcon("images/exitbutt.jpg");
        Image imgSalirRedi = imgSalir.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        btnSalir.setIcon(new ImageIcon(imgSalirRedi));

        ImageIcon imgConfig = new ImageIcon("images/settings.jpg");
        Image imgConfigRedi = imgConfig.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btnConfigVentana.setIcon(new ImageIcon(imgConfigRedi));

        btnJ2.setPreferredSize(buttonSize);
        btnJ3.setPreferredSize(buttonSize);
        btnJ4.setPreferredSize(buttonSize);
        btnRegresar.setPreferredSize(buttonSize);
        
        ImageIcon imgbtnJ2 = new ImageIcon("images/2playbutt.jpg");
        Image imgbtnJ2Redi = imgbtnJ2.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        btnJ2.setIcon(new ImageIcon(imgbtnJ2Redi));
        
        ImageIcon imgbtnJ3 = new ImageIcon("images/3playbutt.jpg");
        Image imgbtnJ3Redi = imgbtnJ3.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        btnJ3.setIcon(new ImageIcon(imgbtnJ3Redi));
        
        ImageIcon imgbtnJ4 = new ImageIcon("images/4playbutt.jpg");
        Image imgbtnJ4Redi = imgbtnJ4.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        btnJ4.setIcon(new ImageIcon(imgbtnJ4Redi));
        
        ImageIcon imgbtnRegresar = new ImageIcon("images/backbutt.jpg");
        Image imgbtnRegresarRedi = imgbtnRegresar.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        btnRegresar.setIcon(new ImageIcon(imgbtnRegresarRedi));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 30, 260);
        gbc.gridx = 0;

        gbc.gridy = 0;
        panelMenu.add(btnInicio, gbc);

        gbc.gridy = 1;
        panelMenu.add(btnSalir, gbc);

        gbc.gridy = 2;
        panelMenu.add(btnConfigVentana, gbc);

        fondo.add(panelMenu, BorderLayout.EAST);

        btnInicio.addActionListener(e -> elegirNumeroJugadores()); 
        btnSalir.addActionListener(e -> salirJuego());
        btnConfigVentana.addActionListener(e -> configuracionVentana());
        btnRegresar.addActionListener(e -> regresarMenuPrincipal());
        btnJ2.addActionListener(e -> iniciarJuego(2));
        btnJ3.addActionListener(e -> iniciarJuego(3));
        btnJ4.addActionListener(e -> iniciarJuego(4));
    }
    
    public void iniciarMusicaFondo() {
        musicaFondo.reproducirMusica("sounds/menuSong.wav"); // Iniciar música de fondo
    }

    private void elegirNumeroJugadores() {
        panelNumeroJugadores.setOpaque(false);
        panelMenu.setVisible(false);
        panelNumeroJugadores.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 30, 260);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelNumeroJugadores.add(btnJ2, gbc);

        gbc.gridy = 1;
        panelNumeroJugadores.add(btnJ3, gbc);

        gbc.gridy = 2;
        panelNumeroJugadores.add(btnJ4, gbc);

        gbc.gridy = 3;
        panelNumeroJugadores.add(btnRegresar, gbc);

        add(panelNumeroJugadores, BorderLayout.EAST);
        panelNumeroJugadores.setVisible(true);
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
        setVisible(false);
        musicaFondo.detenerMusica();
        pantalla.iniciarMusicaFondo();
        
    }

    private void regresarMenuPrincipal() {
        panelNumeroJugadores.setVisible(false);
        panelMenu.setVisible(true);
        revalidate();
        repaint();
    }
    
    ImageIcon miniImg;
    private void configuracionVentana() {
        String[] opcPantalla = {"Pantalla Completa", "Modo Ventana"};
        miniImg = new ImageIcon("images/settingsmini.png");
        int selConfiguracion = JOptionPane.showOptionDialog(this, "Escoga su configuración de pantalla", "Configuración de Pantalla",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, miniImg, opcPantalla, opcPantalla[1]);
        
        if (selConfiguracion == -1) {
            return;
        }
        
        if(selConfiguracion == 0){
            if(modoFullScreen == true){
                JOptionPane.showMessageDialog(null, "¡Ya te encuentras en pantalla completa!", "¡Aviso!", JOptionPane.INFORMATION_MESSAGE, null);
                return;
            } else {
                setVisible(false);
                dispose();
                setUndecorated(true);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                modoFullScreen = true;
            }
        } else {
            if(modoFullScreen == false){
                JOptionPane.showMessageDialog(null, "¡Ya te encuentras en modo ventana!", "¡Aviso!", JOptionPane.INFORMATION_MESSAGE, null);
                return;
            } else {
                setVisible(false);
                dispose();
                setUndecorated(false);
                setExtendedState(JFrame.NORMAL);
                setSize(1176, 664);
                setLocationRelativeTo(null);
                modoFullScreen = false;
            }
        }
        setVisible(true);
        actualizarFondo();
        revalidate();
        repaint();
        
        JOptionPane.showMessageDialog(null, "¡Configuración Aplicada!", "Configuración de Pantalla", JOptionPane.PLAIN_MESSAGE, miniImg);
    }
    
    public void actualizarFondo() {
        int width = getWidth();
        int height = getHeight();
        Image fondoEscalado = imgFondo.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        fondo.setIcon(new ImageIcon(fondoEscalado));
    }

    private void salirJuego() {
        String[] opcSalida = {"Si", "No"};
        int selSalida = JOptionPane.showOptionDialog(this, "¿Estás seguro de querer salir?", "Salir",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcSalida, opcSalida[1]);
        
        if(selSalida == -1){
            return;
        }
        
        if(selSalida == 0){
            musicaFondo.detenerMusica();
            System.exit(0);
        } else {
            return;
        }  
    }
}