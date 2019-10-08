public class Main {

    Thread[] arrT = new Thread[10];
    Counter ct = new Counter();
    int i;

    for(i=0; i<10; i++){
        arrT[i] = new Thread(new ThreadTrabalho(ct));
    }


    for(i=0; i<10; i++){
        arrT[i].start();
    }

    for(int x=0; x<10;x++){
        try {
            arrT[x].join();
        }catch (InterruptedException e) {}
            System.out.println("Fim");
    }


}
