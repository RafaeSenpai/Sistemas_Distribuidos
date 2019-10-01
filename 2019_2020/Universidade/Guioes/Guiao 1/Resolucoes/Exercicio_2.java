import java.util.Scanner;

public class Exercicio_2{

    public static void main(String[] args) {
        int counter;
        int maxCountVal;
        Scanner sc = new Scanner(System.in);
        System.out.println("Até quanto deve ser incrementado o contador?\nVALOR:");
        maxCountVal = sc.nextInt();
        System.out.println("*--------------------*");


        /*Thread  - 1*/
        Thread t1 = new Thread( new Runnable() {
            public void run() {
                for (int i = 1; i<=maxCountVal; i++)
                    Counter().increment();
            }
        });

    }
}