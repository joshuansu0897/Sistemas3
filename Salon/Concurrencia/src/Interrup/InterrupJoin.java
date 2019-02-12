package Interrup;

public class InterrupJoin {

    public static void main(String[] args) {
        ClaseHilo ch = new ClaseHilo(0, 100);
        Thread t1 = new Thread(ch);

        int cont = 0;
        t1.start();

        while (t1.isAlive()) {
            System.out.println("Estoy vivo");
            cont++;

            if (cont % 1000 == 0) {
                t1.interrupt();
            }
        }

        System.out.println("Fueron " + cont + " 'Estoy vivo'");
        System.out.println("Termino");
    }

}
