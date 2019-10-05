import java.lang.*;
import java.util.Scanner;

public class Exe1Slide1 implements Runnable {
    int valMax;

    Exe1Slide1(int max){
        valMax=max;
    }

    public void run() {
        for(int i=1; i<=valMax; i++){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numThreads;
        int max;

        System.out.println("Quantas threads deseja criar?");
        numThreads = sc.nextInt();

        System.out.println("Até quanto é que quer que cada thread apresente a contagem?");
        max = sc.nextInt();

        /*Cria e inicializa o numero de threads indicado pelo utilizador*/
        System.out.println("\n\nInicialiando e executando as threads ...");
        for (int i = 0; i < numThreads; i++) {
            (new Thread(new Exe1Slide1(max))).start();
        }

        /*Lista as threads num array */
        Thread th[] = new Thread[numThreads];
        Thread.enumerate(th);


        /*Lista e apresenta o estado corrente de cada uma das threads existentes no array*/
        System.out.println("\n\nListagem das threads e o seu respetivo estado ...");
        for (int i = 0; i < numThreads; i++) {
            System.out.println("Indice do array de threads: " + i + " => Nome da thread: " + th[i].getName() + " ---> " + th[i].getState());
        }


        try {
            /*Faz uma espera ativa por todas as threads*/
            for (int i = 0; i < numThreads; i++) {
                th[i].join();
            }
        } catch (InterruptedException e) {

        }
        System.out.println("Terminou todo o processo!");
    }
}


/*
* NOTE:
* A Java program can not tell the difference between those two states. The thread states that Java knows about are NEW,
* RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, and TERMINATED.

* A thread is NEW before t.start() is called, and it can never go back to NEW afterward. WAITING and TIMED_WAITING both
* mean that the thread is waiting for a notify() call in some other thread. BLOCKED means it is waiting for anything
* else (e.g., to enter a synchronized block), and TERMINATED means it's finished.
* */