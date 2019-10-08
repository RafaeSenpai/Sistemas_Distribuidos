import java.lang.Runnable;
import java.lang.Thread;
import java.util.Scanner;
/*
 * Versão mais simplificada do exercicio pedido
 *
 * Subentende-se que se deve criar um qualquer numero (>
 * */
public class preExercicos implements Runnable{


    public class Counter {
        public int counter;

        public Counter(){
            this.counter=0;
        }

        public increment(){
            this.counter++;
        }

        public getCounter(){
            return this.counter;
        }
    }


    public void run() {
        /*Criação de um objecto da class Counter*/
        public Counter myCounter = new Counter;


    }


    public static void main(String[] args) {
        int valor;
        Scanner sc = new Scanner(System.in);

        System.out.println("Até que valor quer que cada thread apresente os numeros?");
        valor = sc.nextInt();

        preExercicos instancia_1 = new preExercicos(valor);
        preExercicos instancia_2 = new preExercicos(valor);
        preExercicos instancia_3 = new preExercicos(valor);


        /*Threads que irão incrementar I vezes o contador do unico objecto da class counter*/
        Thread fioExecucao_1 = new Thread(instancia_1);
        Thread fioExecucao_2 = new Thread(instancia_2);
        Thread fioExecucao_3 = new Thread(instancia_3);


        //Inicialização das threads criadas
        fioExecucao_1.start();
        fioExecucao_2.start();
        fioExecucao_3.start();

        try{
            fioExecucao_1.join();
            fioExecucao_2.join();
            fioExecucao_3.join();
        }
        catch(InterruptedException e){
            System.out.println("Ocorreu um erro!");
        }


        System.out.println(myCounter.)
    }
}