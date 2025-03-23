package ito.app.busquedadeltesoro;

public class Acertijo {
    private String pregunta;
    private String respuesta;

    public Acertijo(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta.toLowerCase();
    }

    public String getPregunta() {
        return pregunta;
    }

    public boolean verificarRespuesta(String respuestaJugador) {
        return respuesta.equalsIgnoreCase(respuestaJugador.trim());
    }
}