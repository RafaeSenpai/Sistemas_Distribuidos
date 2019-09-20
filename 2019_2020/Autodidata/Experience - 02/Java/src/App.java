import java.util.*;
class Processor extends Thread { /**A thread Processor vai correr o codigo run()*/

    private volatile boolean running = true;
    private int count;
    public void run() {
        count = 0;
        while(running){
            System.out.println("Hello "+ count);
            count++;
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        running = false;
    }
}

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Processor proc = new Processor();

        proc.start();

        System.out.println("Press return to stop ...");
        scanner.nextLine();

        proc.shutdown();
        
        System.out.println("Thread desligada!\n");
    }
}