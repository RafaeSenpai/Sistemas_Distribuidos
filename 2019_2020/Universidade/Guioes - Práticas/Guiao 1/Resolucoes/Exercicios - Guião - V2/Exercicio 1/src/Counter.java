public class Counter implements Runnable{
    private int valMax;

   Counter(int val){
        valMax = val;
    }
    public void run(){
        for(int i=1; i<=valMax; i++){
            System.out.println(i);
        }
    }
}
