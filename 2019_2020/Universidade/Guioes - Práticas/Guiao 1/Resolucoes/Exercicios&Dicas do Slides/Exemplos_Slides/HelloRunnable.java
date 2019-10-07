/*
* java.lang.Runnable
*   – interface implementada por classes cujas instâncias representam threads;
*   – classes que implementem esta interface têm que implementar o método run();
*
* obs: Instancia é um objeto personalizado que acabou de ser fabricado baseando-se
*      nas especificaçoes de uma classe, neste caso HelloRunnable.
* */
import java.lang.Runnable;

public class HelloRunnable implements Runnable {
    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        // Uma thread a correr HelloRunnable()
        (new Thread(new HelloRunnable())).start();
    }
}