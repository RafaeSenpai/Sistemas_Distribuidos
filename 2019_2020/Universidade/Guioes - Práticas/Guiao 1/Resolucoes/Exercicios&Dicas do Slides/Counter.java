public class Counter extends Exe2Slide1{
    public int contador;

    public void increment(){
        contador++;
    }

    public int getCounter(){
        return contador;
    }

    /*Supostamente representa o metodo exigido no enunciado, cujo o seu nome no mesmo é Counter. Pois ao que percebi este deverá devolver uma copia do objecto counter*/
    public Object clone() {
        return this;
    }
}