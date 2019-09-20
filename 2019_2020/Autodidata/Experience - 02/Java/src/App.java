class Processor extends Thread { /**A thread Processor vai correr o codigo run()*/

    private volatile boolean running = true;

    public void run() {
        while(running){
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            }
            catch (InteruptedException e) {
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
    }
}