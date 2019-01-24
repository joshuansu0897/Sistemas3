package concurrencia;

public class ProbandoConcurrencia {

    public static void main(String[] args) {
        Calificacion Martinez = new Calificacion();
        Calificacion Saucedo = new Calificacion();
        Calificacion Lopez = new Calificacion();

        Thread hiloM = new Thread(Martinez);
        Thread hiloS = new Thread(Saucedo);
        Thread hiloL = new Thread(Lopez);

        hiloM.start();
        hiloS.start();
        hiloL.start();
    }
}
