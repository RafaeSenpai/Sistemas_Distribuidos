class Runner extends Thread {

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

public class App {
    public static void main(String[] args) {
        Runner runner1 = new Runner(); /* declarar uma instancia de Runner */
        runner1.start(); /** Para correr a instancia acima criada */

        Runner runner2 = new Runner();
        runner2.start();

    }
}