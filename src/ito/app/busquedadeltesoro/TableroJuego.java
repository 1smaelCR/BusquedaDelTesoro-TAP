package ito.app.busquedadeltesoro;
import javax.swing.*;

//@authors 1smaelCR, LaLoGlitch, M4RC0S17
public class TableroJuego extends JFrame {
    public void Creartablero(){
  // Crear el marco (JFrame)
       // Crear el marco (JFrame)
        //JFrame frame = new JFrame("Tablero Perimetral 10x10");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);

        // Crear el panel con GridLayout
        JPanel panel = new JPanel(new java.awt.GridLayout(10, 10));
        
        // Ruta de ejemplo para las imágenes (reemplázala con tu ruta de imágenes)
        String rutaImagen = "images/tapizCasilla.jpg";
        String rutaImagenInicio="";
        // Rellenar el tablero solo con casillas en el perímetro
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                if (fila == 0 || fila == 9 || columna == 0 || columna == 9) {
                    // Calcular la posición basada en la fila y columna (1 a 100)
                    int posicion = fila * 10 + columna + 1;
                    // Crear casilla con imagen reescalada
                    Casilla casilla = new Casilla(posicion, rutaImagen);
                    panel.add(casilla); // Añadir la casilla al panel
                } else {
                    // Añadir un espacio vacío para el interior
                    panel.add(new JLabel());
                }
            }
        }

        // Añadir el panel al marco
        this.add(panel);
        this.setVisible(true);
    }
    
 
}
