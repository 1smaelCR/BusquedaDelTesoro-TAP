package ito.app.busquedadeltesoro;

import javax.swing.*;
import java.util.Random;

public class Dado extends JLabel {
    private Random random;
    private ImageIcon[] carasDado;

    public Dado() {
        random = new Random();
        carasDado = new ImageIcon[6];
        carasDado[0] = new ImageIcon("images/dado1.png");
        carasDado[1] = new ImageIcon("images/dado2.jpg");
        carasDado[2] = new ImageIcon("images/dado3.png");
        carasDado[3] = new ImageIcon("images/dado4.png");
        carasDado[4] = new ImageIcon("images/dado5.png");
        carasDado[5] = new ImageIcon("images/dado6.png");
        this.setIcon(carasDado[0]);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public int lanzar() {
        int resultado = random.nextInt(6) + 1;
        this.setIcon(carasDado[resultado - 1]);
        return resultado;
    }
}