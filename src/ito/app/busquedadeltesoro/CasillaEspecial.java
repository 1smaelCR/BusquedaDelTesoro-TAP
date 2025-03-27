/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ito.app.busquedadeltesoro;

/**
 *
 * @author Eduardo
 */
import javax.swing.*;

public class CasillaEspecial extends Casilla {
    private String tipo;  // "avanza" o "retrocede"
    private int valor;    // Número de casillas a mover

    public CasillaEspecial(int posicion, String tipo, int valor, String imagenPath) {
        super(posicion, imagenPath);
        this.tipo = tipo;
        this.valor = valor;
        this.setToolTipText("Casilla Especial: " + tipo.toUpperCase());
    }

    public void aplicarEfecto(Jugador jugador, JTextArea areaAcertijo) {
        int movimiento = this.valor;
        String mensaje = "\n¡Casilla Especial! ";
        
        if (tipo.equals("avanza")) {
            mensaje += "Avanzas " + movimiento + " casillas extra!";
            jugador.setPosicion(Math.min(jugador.getPosicion() + movimiento, 100));
        } else {
            mensaje += "Retrocedes " + movimiento + " casillas!";
            jugador.setPosicion(Math.max(jugador.getPosicion() - movimiento, 1));
        }
        
        areaAcertijo.append(mensaje);
    }

    // Getters (opcionales, pero útiles)
    public String getTipo() { return tipo; }
    public int getValor() { return valor; }
}