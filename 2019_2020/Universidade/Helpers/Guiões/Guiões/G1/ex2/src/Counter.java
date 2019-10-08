public class Counter {
    private int counter;

    public Counter(int c){
        this.counter = c;
    }

    public int getCounter(){
        return this.counter;
    }
    
    public void increment(){
        this.counter++;
    }
}
