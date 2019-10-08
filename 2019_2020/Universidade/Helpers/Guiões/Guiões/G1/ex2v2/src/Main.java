public class Main {
    public static void main(String[] args) {
        int i;
        Counter c = new Counter(0);
        Thread[] threads = new Thread[10];

        for(i = 0; i < 10; i++)
            threads[i] = new Thread(new ThreadWork(c));
        for(i = 0; i < 10; i++)
            threads[i].start();
        for(i = 0; i < 10; i++)
            try{
                threads[i].join();
            }catch(InterruptedException ie){}

        System.out.println(c.counter);
    }
}
