package ito.app.busquedadeltesoro;

public class Main {
    public static void main(String[] args) {
        Jugador[] jugadores = new Jugador[2];
        jugadores[0] = new Jugador("Jugador 1", "images/Ficha1.jpg");
        jugadores[1] = new Jugador("Jugador 2", "images/Ficha2.jpg");

        TableroJuego pantalla = new TableroJuego(jugadores);
    }
}