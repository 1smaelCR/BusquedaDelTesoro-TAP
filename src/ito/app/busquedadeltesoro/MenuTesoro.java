package ito.app.busquedadeltesoro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuTesoro extends JFrame {
    public static boolean modoFullScreen = true;
    private JPanel panelMenu = new JPanel(new GridBagLayout());
    private JPanel panelNumeroJugadores = new JPanel(new GridBagLayout());
    private JButton btnInicio = new JButton("INICIAR");
    private JButton btnSalir = new JButton("SALIR");
    private JButton btnConfigVentana = new JButton();
    private JButton btnJ2 = new JButton("JUGADORES 2");
    private JButton btnJ3 = new JButton("JUGADORES 3");
    private JButton btnJ4 = new JButton("JUGADORES 4");
    private JButton btnRegresar = new JButton("REGRESAR");

    public MenuTesoro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Bob Esponja: La Búsqueda del Tesoro");
        setLayout(new BorderLayout());
        setVisible(true);
        
        PantallaCarga pantalla = new PantallaCarga();
        pantalla.setVisible(true);
        
        /*setTitle("Caza del Tesoro");
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());*/

        Dimension buttonSize = new Dimension(200, 50);
        Dimension buttonConfigSize = new Dimension(50, 50);
        btnInicio.setPreferredSize(buttonSize);
        btnSalir.setPreferredSize(buttonSize);
        btnConfigVentana.setPreferredSize(buttonConfigSize);
        ImageIcon img = new ImageIcon("images/settings.jpg");
        Image imgRedi = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        img = new ImageIcon(imgRedi);
        btnConfigVentana.setIcon(img);
        btnJ2.setPreferredSize(buttonSize);
        btnJ3.setPreferredSize(buttonSize);
        btnJ4.setPreferredSize(buttonSize);
        btnRegresar.setPreferredSize(buttonSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridy = 1;
        panelMenu.add(btnInicio, gbc);

        gbc.gridy = 2;
        panelMenu.add(btnSalir, gbc);
        
        gbc.gridy = 3;
        panelMenu.add(btnConfigVentana, gbc);

        add(panelMenu, BorderLayout.CENTER);

        btnInicio.addActionListener(e -> elegirNumeroJugadores());
        btnSalir.addActionListener(e -> System.exit(0));
        btnConfigVentana.addActionListener(e -> configuracionVentana());
        btnRegresar.addActionListener(e -> regresarMenuPrincipal());
        btnJ2.addActionListener(e -> iniciarJuego(2));
        btnJ3.addActionListener(e -> iniciarJuego(3));
        btnJ4.addActionListener(e -> iniciarJuego(4));
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
        revalidate();
        repaint();
        
        JOptionPane.showMessageDialog(null, "¡Configuración Aplicada!", "Configuración de Pantalla", JOptionPane.PLAIN_MESSAGE, miniImg);
    }
}