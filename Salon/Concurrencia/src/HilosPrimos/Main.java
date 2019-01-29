package HilosPrimos;

public class Main {

    public static void main(String[] args) {
        Thread primos = new Thread(new NumerosPrimos());
        
        primos.start();
    }

}
