public class Cliente2 implements Runnable{
    private Banco banco;

    public Cliente2(Banco banco){
        this.banco = banco;
    }

    @Override
    public void run() {
        /*
        for(int i = 0; i < 100000; i++)
            this.banco.levantar(0,5);
         */
        banco.levantar(1,1000);
    }
}
