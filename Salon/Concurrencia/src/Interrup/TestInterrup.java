package Interrup;

public class TestInterrup {

    public static void main(String[] args) {
        Runnable1 r1 = new Runnable1();
        Thread t1 = new Thread(r1);

        t1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        t1.interrupt();

        System.out.println(Thread.currentThread().getName() + ": Terminando");
    }
}
