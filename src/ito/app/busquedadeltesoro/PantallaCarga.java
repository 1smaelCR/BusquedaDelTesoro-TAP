package ito.app.busquedadeltesoro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PantallaCarga extends JFrame {
    private JPanel fondoPantCarga = new JPanel();
    private ImageIcon imgFondo = new ImageIcon("pantallacarga/pantallacarga.jpg");
    private JLabel fondo = new JLabel(imgFondo);
    private JProgressBar barraCarga;
    
    public PantallaCarga() {
        setUndecorated(true); // Eliminar bordes
        setSize(1366,768); // Tamaño de la pantalla de carga
        setLocationRelativeTo(null);
        
        fondo.setLayout(new BorderLayout());
        Image fondoEscalado = imgFondo.getImage().getScaledInstance(
            getWidth(), 
            getHeight(), 
            Image.SCALE_SMOOTH
        );
        fondo.setIcon(new ImageIcon(fondoEscalado));
        
        barraCarga = new JProgressBar(0, 100);
        barraCarga.setForeground(new Color(0, 100, 255, 150));
        barraCarga.setBackground(new Color(200, 230, 255));
        barraCarga.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        fondo.add(barraCarga, BorderLayout.SOUTH);
        
        setContentPane(fondo);
        
        Timer tiempoCarga = new Timer(50, new ActionListener() {
            int progreso = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                progreso++;
                barraCarga.setValue(progreso);
                if (progreso >= 100) {
                    ((Timer) e.getSource()).stop();
                    dispose(); // Cerrar la pantalla de carga
                }
            }
        });
        tiempoCarga.start();
    }
}
