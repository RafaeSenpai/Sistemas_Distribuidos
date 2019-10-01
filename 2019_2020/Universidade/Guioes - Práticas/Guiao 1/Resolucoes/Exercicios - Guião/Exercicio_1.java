import java.lang.Runnable;
import java.util.Scanner;

public class Exercicio_1 {

    public static void main(String[] args) {
        int maxCountVal;
        Scanner sc = new Scanner(System.in);
        System.out.println("Até quanto deve ser incrementado o contador?\nVALOR:");
        maxCountVal = sc.nextInt();
        System.out.println("*--------------------*");

        /*Thread  - 1*/
        Thread t1 = new Thread( new Runnable() {
            public void run() {
                for (int i = 1; i<=maxCountVal; i++) {
                    System.out.println(i);
                }
            }
        });
        /*Thread  - 2*/
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i<=maxCountVal; i++) {
                    System.out.println(i);
                }
            }
        });

        /*inicia as threads*/
        t1.start();
        t2.start();

        try {
            /*faz a espera de todas as threads terminarem*/
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}