public class HelloRunnable3 implements Runnable {

    int n;

    HelloRunnable3(int a) {
        n = a;
    }

    public void set(int b) {
        n = b;
    }
    /*A 1º thread vai imprimir o valor que é passado na main (222) e ainda na mesma thread após imprimir 222 altera esse mesmo valor para 111.
    * O valor 111, alterado pela 1º thread será apresentado ao utilizador pela 2º thread*/
    public void run() {
        Thread principal = Thread.currentThread();
        System.out.println("Valor do objecto thread: " + principal);
        System.out.println("Nome da thread:" + principal.getName());
        System.out.println("ID da thread: " + principal.getId());
        System.out.println("getStakTrace(): "+ principal.getStackTrace());
        System.out.println("getState(): "+ principal.getState());
        System.out.println("Numero de threads ativas deste programa já fora da main => activeCount(): "+ principal.activeCount());
        System.out.println("toString(): " + principal.toString());
        System.out.println(n);
        this.set(111);
    }

    public static void main(String args[]) {
        Thread principal = Thread.currentThread();
        System.out.println("INICIO DA MAIN - Numero de threads ativas deste programa na main => activeCount(): "+ principal.activeCount());
        HelloRunnable3 r = new HelloRunnable3(222);

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        System.out.println("Antes");
        t1.start();
        t2.start();
        System.out.println("MEIO DA MAIN (depois de arrancar as threads)- Numero de threads ativas deste programa na main => activeCount(): "+ principal.activeCount());
        System.out.println("Depois");
        try {
            System.out.println("ANTES DO JOIN - Numero de threads ativas deste programa na main => activeCount(): "+ principal.activeCount());
            t2.join();
            System.out.println("A MEIO DOS JOIN - Numero de threads ativas deste programa na main => activeCount(): "+ principal.activeCount());
            t1.join();
            System.out.println("DEPOIS DO JOIN - Numero de threads ativas deste programa na main => activeCount(): "+ principal.activeCount());
        } catch (InterruptedException e) {
        }
        System.out.println("FINAL DA MAIN - Numero de threads ativas deste programa na main => activeCount(): "+ principal.activeCount());
    }

}