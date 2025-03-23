package ito.app.busquedadeltesoro;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temporizador {
    private int tiempoRestante;
    private Timer timer;

    public Temporizador(int tiempoInicial) {
        this.tiempoRestante = tiempoInicial;
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                if (tiempoRestante <= 0) {
                    ((Timer) e.getSource()).stop();
                    System.out.println("¡Tiempo agotado!");
                }
            }
        });
    }

    public void iniciar() {
        timer.start();
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }
}