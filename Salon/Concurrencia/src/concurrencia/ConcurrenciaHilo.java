package concurrencia;

public class ConcurrenciaHilo extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " Ejecutando metodo run desde un Hilo");
        }
    }

    public static void main(String[] args) {
        new ConcurrenciaHilo().start();
    }

}
