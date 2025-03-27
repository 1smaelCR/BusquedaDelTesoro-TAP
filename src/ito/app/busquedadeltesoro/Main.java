package ito.app.busquedadeltesoro;

public class Main {
    public static void main(String[] args) {
        // Crear la pantalla de carga
        PantallaCarga pantalla = new PantallaCarga();
        pantalla.setVisible(true);

        // Crear el menú pero mantenerlo oculto
        MenuTesoro menu = new MenuTesoro();
        menu.setVisible(false); // No mostrar el menú inicialmente

        // Listener para manejar la transición entre ventanas
        pantalla.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                // Mostrar el menú y reproducir música de fondo
                menu.setVisible(true);
                menu.iniciarMusicaFondo(); // Inicia la música solo cuando el menú es visible
            }
        });
    }
}

