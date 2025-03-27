package ito.app.busquedadeltesoro;

public class Main {
    public static void main(String[] args) {
        // Crear y mostrar la pantalla de carga
        PantallaCarga pantalla = new PantallaCarga();
        pantalla.setVisible(true);
        MenuTesoro menu = new MenuTesoro();
        menu.setVisible(false);

        // Listener para abrir el menú tras cerrar la pantalla de carga
        pantalla.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                menu.setVisible(true);
            }
        });
    }
}
