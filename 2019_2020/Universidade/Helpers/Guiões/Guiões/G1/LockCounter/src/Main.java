public class Main {
    public static void main(String[] args) {
        Counter c = new Counter(0);
        Thread[] threads = new Thread[10];
        int i;

        for(i = 0; i < 10; i++)
            threads[i] = new Thread(new ThreadWork(c));
        for(i = 0; i < 10; i++)
            threads[i].start();
        for(i = 0; i < 10; i++)
            try{
                threads[i].join();
            }catch(InterruptedException ie){}

        System.out.println(c.getCounter());
    }
}
