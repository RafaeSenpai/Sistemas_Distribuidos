public class Main {
    public static void main(String[] args) {
        int i;
        Thread[] arrayT = new Thread[10];

        for (i = 0; i < 10; i++) {
            arrayT[i] = new Thread(new exe1g1());
        }
        for (i = 0; i < 10; i++) {
            arrayT[i].start();
        }

        for (i = 0; i < 10; i++) {
            try {
                arrayT[i].join();
            } catch (InterruptedException ie) {
                System.out.println("Thread error");
            }
        }
    }
}
