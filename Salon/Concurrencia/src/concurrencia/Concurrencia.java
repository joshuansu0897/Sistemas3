package concurrencia;

public class Concurrencia implements Runnable {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Concurrencia());

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Run2...");
            }
        });

        Thread thread3 = new Thread(new TaskAsync());

        thread.start();
        thread2.start();
        thread3.start();

    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Run...");
        }
    }

    private static class TaskAsync implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Run3...");
            }
        }

    }

}
