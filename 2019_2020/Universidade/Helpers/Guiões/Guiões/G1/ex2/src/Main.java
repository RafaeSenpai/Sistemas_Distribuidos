public class Main {
    public static void main(String[] args) {
        int i;
        Counter c = new Counter(0);
        Thread[] threads = new Thread[10];

        /** Passamos o contador como parametro para as diferentes threads terem acesso ao mesmo objeto **/
         for(i = 0; i < 10; i++)
             threads[i] = new Thread(new ThreadWork(c));
         /** Inicializar as threads **/
         for(i = 0; i < 10; i++)
             threads[i].start();
         /** Esperar com que todas as threads acabem **/
         for(i = 0; i < 10; i++)
            try {
                threads[i].join();
            }catch(InterruptedException ie){}

         System.out.println(c.getCounter());
    }
}
