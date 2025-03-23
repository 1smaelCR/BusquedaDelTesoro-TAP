package ito.app.busquedadeltesoro;

public class Main {
    public static void main(String[] args) {
        Jugador[] jugadores = new Jugador[2];
        jugadores[0] = new Jugador("Jugador 1", "images/ficha1.png");
        jugadores[1] = new Jugador("Jugador 2", "images/ficha2.png");

        TableroJuego pantalla = new TableroJuego(jugadores);
    }
}