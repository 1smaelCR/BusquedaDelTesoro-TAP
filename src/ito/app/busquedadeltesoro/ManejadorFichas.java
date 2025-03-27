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

    public Acertijo obtenerAcertijoAleatorio() {
        return acertijos[random.nextInt(acertijos.length)];
    }

    public void resolverAcertijo(Jugador jugador) {
        if (jugador.getPosicion() == 100) {
            // No resolver acertijos normales en el tesoro
            return;
        }
        
        Acertijo acertijo = obtenerAcertijoAleatorio();
        tablero.mostrarAcertijo(acertijo.getPregunta());
        String respuesta = tablero.obtenerRespuesta();

        if (acertijo.verificarRespuesta(respuesta)) {
            int pasos = random.nextInt(5) + 1;
            tablero.mostrarMensaje("¡Correcto! " + jugador.getNombre() + " avanza " + pasos + " casillas.");
            tablero.moverFicha(jugador, pasos);
        } else {
            tablero.mostrarMensaje("Incorrecto. " + jugador.getNombre() + " pierde turno.");
        }
        tablero.siguienteTurno();
    }

    public void moverFicha(Jugador jugador, int pasos) {
        if (tablero.juegoTerminado || pasos <= 0) return;
        
        int posicionActual = jugador.getPosicion();
        int nuevaPosicion = Math.min(posicionActual + pasos, 100);
        
        tablero.animarMovimiento(jugador, posicionActual, nuevaPosicion);
    }
}