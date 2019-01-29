package tarea1;

public class Fibonacci implements Runnable {

    private int NUM;

    public Fibonacci() {
        this.NUM = 50;
    }

    public Fibonacci(int NUM) {
        this.NUM = NUM;
    }

    public int getNUM() {
        return NUM;
    }

    public void setNUM(int NUM) {
        this.NUM = NUM;
    }

    @Override
    public void run() {
        System.out.println(fibonacci(this.NUM));
    }

    private int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
