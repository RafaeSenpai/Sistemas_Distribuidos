public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco(2);

        Cliente1 c1 = new Cliente1(banco);
        Cliente2 c2 = new Cliente2(banco);

        Thread thread1 = new Thread(c1);
        Thread thread2 = new Thread(c2);

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException ie){}

        System.out.println("O valor da conta 0 é de " + banco.consultar(0));
        System.out.println("O valor da conta 1 é de " + banco.consultar(1));
    }
}
