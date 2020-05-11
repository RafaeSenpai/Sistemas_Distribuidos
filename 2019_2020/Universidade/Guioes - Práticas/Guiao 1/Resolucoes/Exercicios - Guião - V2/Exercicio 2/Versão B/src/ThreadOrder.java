/*
 * Indicação dos trabalhos a serem dados a cada thread
 * */

public class ThreadOrder implements Runnable {
    private Counter counter;

    public ThreadOrder(Counter c) {
        this.counter = c;
    }

    @Override
    public void run() {
        int i;

        for (i = 0; i < counter.getMaxVal(); i++) {
            this.counter.contador++; /**Acesso direto á variavel de instancia por parte das threads conforme pedido no exercicio*/
        }
    }
}
