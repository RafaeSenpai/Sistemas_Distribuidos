public class ThreadTrabalho implements Runnable{
    private Counter c;

        public ThreadTrabalho(Counter ct){
            this.c = ct;
        }

        public void run(){
            for(int i=0; i<10;i++){
                c.increment();
            }
        }
}
