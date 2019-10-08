public class Counter {
    private int counter;

    public Counter(int counter){
        this.counter = counter;
    }

    public synchronized void increment(){
        this.counter++;
    }

    public int getCounter(){
        return this.counter;
    }
}
