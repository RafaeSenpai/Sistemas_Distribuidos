public class Counter {
    public int contador;/**<<--- contador tem que ser publio caso contrario nao consegue ser alterado pelas threads que o acedem*/
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

    public void increment(){
        this.contador++;
    }
}
