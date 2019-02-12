package Interrup;

public class ClaseHilo implements Runnable {

    private int Max;
    private int Min;

    public ClaseHilo(int Min, int Max) {
        this.Max = Max;
        this.Min = Min;
    }

    public ClaseHilo() {
        this.Min = 0;
        this.Max = 10_000;
    }

    public int getMax() {
        return Max;
    }

    public void setMax(int Max) {
        this.Max = Max;
    }

    public int getMin() {
        return Min;
    }

    public void setMin(int Min) {
        this.Min = Min;
    }

    @Override
    public void run() {
        try {
            for (int i = this.Min; i <= this.Max; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Me interrumpieron");
                } else {
                    System.out.println("Nuevooooooooo");
                }
                
                if (i % 100 == 0) {
                    System.out.println("Numero " + i);
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

}
