public class App{
    private int count = 0;

    public synchronized void increment(){
        count++;
    }

    public static void main(String[] args) {
        App app = new App();
        app.doWork();
    }

    public void doWork(){
        Thread linhaExecucao_1 = new Thread(new Runnable(){
            public void run(){
                
                for(int i=0; i<10000; i++){
                    increment();
                }
            }
        });

        Thread linhaExecucao_2 = new Thread(new Runnable(){
            public void run(){
                for(int i=0; i<10000; i++){
                    increment();
                }
            }
        });
        linhaExecucao_1.start();
        linhaExecucao_2.start();

        try {
            linhaExecucao_1.join();
            linhaExecucao_2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Contagem: " + count);
    }
}