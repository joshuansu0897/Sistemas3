package Join;

public class ClaseJoin implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 40_000; i++) {
//            try {
//                Thread.sleep(500);
//            } catch (Exception e) {
//            }

            System.out.println(Thread.currentThread().getName() + ": i = " + i);
        }
    }

}
