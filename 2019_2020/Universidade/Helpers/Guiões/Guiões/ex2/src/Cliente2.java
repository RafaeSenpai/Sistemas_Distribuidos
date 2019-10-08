public class Cliente2 implements Runnable{
    private Banco banco;

    /**
     * Método construtor
     *
     * @param banco
     */
    public Cliente2(Banco banco){
        this.banco = banco;
    }

    public void run(){
        Thread thread = Thread.currentThread();
        System.out.println("Thread " + thread.getId());

        for(int i = 0; i < 1000; i++){
            banco.debito(0, 5);
        }
    }
}
