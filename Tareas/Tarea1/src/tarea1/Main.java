package tarea1;

public class Main {

    public static void main(String[] args) {
        Thread fibo = new Thread(new Fibonacci(7));

        fibo.start();
    }

}
