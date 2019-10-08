public class Cliente1 implements Runnable {
    private Banco banco;

    /**
     * Método construtor
     *
     * @param banco
     */
    public Cliente1(Banco banco){
        this.banco = banco;
    }

    /**
     * O cliente 1 faz 1000 creditos de 5euros
     */
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("Thread " + thread.getId());

        for (int i = 0; i < 1000; i++){
            banco.credito(0, 5);
        }
    }
}
