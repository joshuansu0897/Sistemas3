package practica1.hilos;

public class NumerosPrimos implements Runnable {

    private int Min;
    private int Max;

    private int Count;

    public NumerosPrimos() {
        this.Min = 0;
        this.Max = 100;
    }

    public NumerosPrimos(int Min, int Max) {
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
        for (int i = this.Min; i < this.Max; i++) {
            if (isPrimo(i)) {
                Count++;
            }
        }

        System.out.println(Thread.currentThread().getName() + ": " + this.Count);
    }

    private boolean isPrimo(int n) {

        if (n >= 1000 && n <= 2000) {
            return false;
        }

        if (n <= 2) {
            return n > 1;
        }

        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

}
