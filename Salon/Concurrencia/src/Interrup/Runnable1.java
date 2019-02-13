package Interrup;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Runnable1 implements Runnable {

    @Override
    public void run() {
        int count = 0;

        System.out.println(Thread.currentThread().getName() + ": Antes de empezar");

        while (true) {
            System.out.println("count: " + count++);

            if (Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + ": isInterrupted()");

                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    System.out.println("InterruptedException: " + ex.getMessage());
                }

                System.out.println("Despues try-catch");
//                break;
                if (count > 1000) {
                    break;
                }
            }
        }

        System.out.println(Thread.currentThread().getName() + ": Terminando");
    }

}
