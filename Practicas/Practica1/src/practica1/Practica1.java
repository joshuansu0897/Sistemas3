package practica1;

import practica1.hilos.*;

public class Practica1 {

    public static void main(String[] args) {
        NumerosPrimos np = new NumerosPrimos(1, 1_000_000);
        Par p = new Par(1, 10_000_000);
        ImPar ip = new ImPar(1, 10_000_000);

        Thread t1 = new Thread(np);
        t1.setName("Numeros Primos");

        Thread t2 = new Thread(p);
        t2.setName("Par");

        Thread t3 = new Thread(ip);
        t3.setName("ImPar");

        t1.start();
        t2.start();
        t3.start();

        while (t1.isAlive() || t2.isAlive() || t3.isAlive()) {
        }

        System.out.println(Thread.currentThread().getName() + ": Termine de ejecutar los programas");
    }

}
