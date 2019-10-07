import java.lang.Runnable;
import java.lang.Thread;
import java.util.Scanner;
/*
 * Versão mais simplificada do exercicio pedido
 *
 * Subentende-se que se deve criar um qualquer numero (>1) de threads, neste caso serão criadas 3 threads, para gerarem o resultado pretendido
 * */
public class Exe2Slide1_VERCAO2 implements Runnable{
    private int contador=1;
    int valMax;

    Exe2Slide1_VERCAO2(int valor){
        valMax=valor;
    }


    //Instruções do "trabalho" que cada thread vai ter que fazer
    public void run() {
        for(int i=1; i<=valMax; i++){
            System.out.println(contador);
            contador++;
        }
    }


    public static void main(String[] args) {
        int valor;
        Scanner sc = new Scanner(System.in);

        System.out.println("Até que valor quer que cada thread apresente os numeros?");
        valor = sc.nextInt();


        Exe2Slide1_VERCAO2 instancia_1 = new Exe2Slide1_VERCAO2(valor);
        Exe2Slide1_VERCAO2 instancia_2 = new Exe2Slide1_VERCAO2(valor);
        Exe2Slide1_VERCAO2 instancia_3 = new Exe2Slide1_VERCAO2(valor);


        Thread fioExecucao_1 = new Thread(instancia_1);
        Thread fioExecucao_2 = new Thread(instancia_2);
        Thread fioExecucao_3 = new Thread(instancia_3);


        System.out.println("Antes de inicializar as threads");
        //Arrancar as threads criadas
        fioExecucao_1.start();
        fioExecucao_2.start();
        fioExecucao_3.start();

        System.out.println("Depois das threads arrancarem");
        try{
            /*
             * Como funcona o Join()
             * O programa está executando na thread X (pode ser a thread principal do sistema, que é iniciada pelo próprio Java)
             * A thread X inicia uma thread Y, usando algo como:
             *           Thread Y = new Thread(new Runnable () { public void run() { ... } });
             *           Y.start(); // iniciando a thread Y
             *           ...
             *
             * A thread X faz alguma coisa, e então quer esperar a thread Y terminar (morrer). Então a thread X tem de fazer:
             *
             *           ...
             *           System.out.println ("Eu sou a thread X e vou esperar a thread Y terminar (se é que ela já não terminou)");
             *           Y.join(); // <<<<<<<<-------------------------------------isto faz com que a thread X pare e espere até a thread Y terminar.
             *           System.out.println ("Eu sou a thread X e esperei até que a thread Y terminasse.");
             * */
            fioExecucao_1.join();
            fioExecucao_2.join();
            fioExecucao_3.join();
        }
        catch(InterruptedException e){
            System.out.println("Ocorreu um erro!");
        }
    }
}