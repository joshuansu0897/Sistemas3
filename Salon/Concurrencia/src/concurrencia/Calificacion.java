package concurrencia;

public class Calificacion implements Runnable {

    @Override
    public void run() {
        System.out.println("Ejecutando Calificacion");
    }

}
