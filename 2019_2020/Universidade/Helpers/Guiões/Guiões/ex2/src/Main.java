public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco(2);

        Thread t1 = new Thread( new Cliente1(banco) );
        Thread t2 = new Thread( new Cliente2(banco) );

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch(InterruptedException ie){}

        System.out.println("O valor da conta é de " + banco.consulta(0) + " euros");
    }
}
