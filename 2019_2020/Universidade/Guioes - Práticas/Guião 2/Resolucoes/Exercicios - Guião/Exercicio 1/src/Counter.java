public class Counter {
    private int contador;/**<<--- contador tem que ser publio caso contrario nao consegue ser alterado pelas threads que o acedem*/
    private int maxVal;/*<<--valor maximo que o contador pode atingir*/


    public Counter(int x){
        this.contador = 0;/*inicialização do contador a zero*/
        this.maxVal = x;
    }

    public int getMaxVal(){
        return this.maxVal;
    }

    public int getCounter(){
        return this.contador;
    }

    /**
     * Synchronized permite o incremento concorrente de um contador partilhado, de
     * modo a garantir a execução correcta do programa.*/
    public synchronized void increment(){
        this.contador++;
    }
}
