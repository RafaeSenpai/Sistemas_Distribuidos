public class Counter {
    private int contador;
    private int maxVal;

    public Counter(int x){
        this.contador = 0;/*inicialização do contador a zero*/
        this.maxVal = x;/*valor maximo do contador*/
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
