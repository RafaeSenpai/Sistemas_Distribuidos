package aula2ex1;

public class Counter{

    public int counter;

    public  Counter(){
        this.counter = 0;

        //Metodo syncronised
        public synchronized void increment(){
            this.counter++;
        }

        //bloco synchronised
        public void increment2(){
            synchronized (this){
                this.counter++;
                Thread.correntThread().getName();
            }
        }

        /*FALTA AQUI ALGUM CODIGO*/
    }
}