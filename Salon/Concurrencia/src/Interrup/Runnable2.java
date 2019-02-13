package Interrup;

public class Runnable2 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Antes de empezar");

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": i = " + i);
        }

        System.out.println(Thread.currentThread().getName() + ": Terminando");
    }
}
