package practica1.hilos;

public class ImPar implements Runnable {

    private int Min;
    private int Max;

    private int Count;

    public ImPar() {
        this.Min = 0;
        this.Max = 100;
    }

    public ImPar(int Min, int Max) {
        this.Min = Min;
        this.Max = Max;
    }

    public int getMin() {
        return Min;
    }

    public int getCount() {
        return Count;
    }

    public void setMin(int Min) {
        this.Min = Min;
    }

    public int getMax() {
        return Max;
    }

    public void setMax(int Max) {
        this.Max = Max;
    }

    @Override
    public void run() {
        for (int i = this.Min; i <= this.Max; i++) {
            if (isImpar(i)) {
                Count++;
            }
        }

        System.out.println(Thread.currentThread().getName() + ": " + this.Count);
    }

    private boolean isImpar(int n) {
        return n % 2 != 0;
    }

}
