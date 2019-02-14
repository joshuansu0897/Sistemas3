package Join;

public class Main {

    public static void main(String[] args) {
        ClaseJoin r1 = new ClaseJoin();
        ClaseJoin r2 = new ClaseJoin();
        ClaseJoin r3 = new ClaseJoin();

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        t1.start();
        t2.start();

        try {
//            System.out.println("Antes de 4 seg");
//            Thread.sleep(4000);
//            System.out.println("Despues de 4 seg");
//            t1.join();
//            System.out.println("Despues Join()");

            t1.start();
            t2.start();
            t3.start();

            t1.join();
        } catch (Exception e) {
        }

        System.out.println(Thread.currentThread().getName() + ": Termino");
    }

}
