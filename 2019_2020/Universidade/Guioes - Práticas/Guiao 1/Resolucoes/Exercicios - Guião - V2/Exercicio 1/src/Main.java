import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i;
        int numThreads;
        int valMax;

        System.out.println("Quantas threads pretende gerar?");
        numThreads = sc.nextInt();

        System.out.println("Até que valor deve ser incrementado o contador?");
        valMax = sc.nextInt();


       Thread[] arrayThreads = new Thread[numThreads];

       for(i=0; i<numThreads; i++){
        arrayThreads[i] = new Thread(new Counter(valMax));
       }

        for(i=0; i<numThreads; i++){
            arrayThreads[i].start();
        }

        for(i=0; i<numThreads; i++){
            try {
                arrayThreads[i].join();
            }catch(InterruptedException ie){
                System.out.println("Erro!");
            }
        }

    }
}
