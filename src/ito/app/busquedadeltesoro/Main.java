package ito.app.busquedadeltesoro;

public class Main {
    public static void main(String[] args) {
        PantallaCarga pantalla = new PantallaCarga();
        pantalla.setVisible(true);

        MenuTesoro menu = new MenuTesoro();
        menu.setVisible(false); // No mostrar el menú inicialmente

        // Listener para manejar la transición entre ventanas
        pantalla.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                menu.setVisible(true);
                menu.iniciarMusicaFondo();
            }
        });
    }
}

