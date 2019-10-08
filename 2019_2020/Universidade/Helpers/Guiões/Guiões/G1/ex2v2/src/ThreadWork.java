public class ThreadWork implements Runnable{
    private Counter counter;

    public ThreadWork(Counter c){
        this.counter = c;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++)
            counter.counter++;
    }
}
