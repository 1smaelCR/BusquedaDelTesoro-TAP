package ito.app.busquedadeltesoro;

import java.util.Random;

public class ManejadorFichas {
    private TableroJuego tablero;
    private Acertijo[] acertijos;
    private Random random;

    public ManejadorFichas(TableroJuego tablero) {
        this.tablero = tablero;
        this.acertijos = GeneradorAcertijos.generarAcertijos();
        this.random = new Random();
    }

    public void resolverAcertijo(Jugador jugador) {
        Acertijo acertijo = acertijos[random.nextInt(acertijos.length)];
        tablero.mostrarAcertijo(acertijo.getPregunta());
        String respuesta = tablero.obtenerRespuesta();

        if (acertijo.verificarRespuesta(respuesta)) {
            int pasos = random.nextInt(5) + 1; // Avanza entre 1 y 5 casillas
            tablero.mostrarMensaje("¡Correcto! Avanzas " + pasos + " casillas.");
            moverFicha(jugador, pasos);
        } else {
            tablero.mostrarMensaje("Incorrecto. Pierdes tu turno.");
        }
        tablero.siguienteTurno();
    }

    private void moverFicha(Jugador jugador, int pasos) {
        int nuevaPosicion = jugador.getPosicion() + pasos;
        if (nuevaPosicion > 100) {
            nuevaPosicion = 100;
        }
        tablero.moverFicha(jugador, nuevaPosicion);
        tablero.mostrarMensaje(jugador.getNombre() + " avanzó a la posición " + nuevaPosicion);
    }
}