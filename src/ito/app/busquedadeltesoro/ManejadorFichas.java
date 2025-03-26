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
        int pasos = random.nextInt(5) + 1;
        // Mostrar mensaje único aquí:
        tablero.mostrarMensaje("¡Correcto! " + jugador.getNombre() + " avanza " + pasos + " casillas.");
        tablero.moverFicha(jugador, pasos); // Este ya no mostrará mensaje
    } else {
        tablero.mostrarMensaje("Incorrecto. " + jugador.getNombre() + " pierde turno.");
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