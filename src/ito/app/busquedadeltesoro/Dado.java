package ito.app.busquedadeltesoro;

import javax.swing.*;
import java.util.Random;

public class Dado extends JLabel {
    private Random random;
    private ImageIcon[] carasDado;

    public Dado() {
        random = new Random();
        carasDado = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            carasDado[i] = new ImageIcon("images/dado" + (i + 1) + ".png");
        }
        this.setIcon(carasDado[0]); // Cara inicial del dado
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public int lanzar() {
        int resultado = random.nextInt(6) + 1;
        this.setIcon(carasDado[resultado - 1]); // Actualizar la imagen del dado
        return resultado;
    }
}