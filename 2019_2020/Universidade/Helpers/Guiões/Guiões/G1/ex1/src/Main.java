public class Main {
    public static void main(String[] args) {
        int i;
        Thread[] threads = new Thread[10];
        /** Todas as threads tem que saber que trabalho fazer **/
        for(i = 0; i < 10; i++)
            threads[i] = new Thread(new ThreadWork());
        /** Dar início ao trabalho das threads **/
        for(i = 0; i < 10; i++)
            threads[i].start();
        /** Esperar que todas as threads acabem (semelhante ao wait() em C) **/
        for(i = 0; i < 10; i++)
            try{
                threads[i].join();
            }catch(InterruptedException ie) {
                System.out.println("Thread error");
            }
    }
}
