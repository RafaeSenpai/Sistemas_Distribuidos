class Runner1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { 
            System.out.println("Hello" + i);

            try {
                Thread.sleep(100);/* Expessificar o tempo de pausa do programa em milisegundos */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App1 {
    /**
     * Ambos os loops (run) estão a correr ao mesmo tempo, estando cada thread a
     * apresentar mesmos valores
     */
    public static void main(String[] args) {
        Runner1 runner1 = new Runner1(); /* <-- declarar uma instancia de Runner */
        runner1.start(); /** <-- Para correr a instancia acima criada */

        Runner1 runner2 = new Runner1();
        runner2.start();

    }
}