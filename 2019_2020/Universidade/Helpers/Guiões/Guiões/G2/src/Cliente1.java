public class Cliente1 implements Runnable{
    private Banco banco;

    public Cliente1(Banco banco){
        this.banco = banco;
    }

    @Override
    public void run() {
        /*
        for(int i = 0; i < 100000; i++)
            this.banco.depositar(0,5.0);*/
        banco.transferir(0,1,1000);
    }
}
