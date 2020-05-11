import java.util.ArrayList;
import java.util.List;

public class Conta {
    public int numConta;
    float saldo;
    private ArrayList<Movimento> listaMovimentos = new ArrayList<>();


    public Conta(Integer n){
        this.numConta = n;
        this.saldo = 0;

    }


    public void setMovimento(float valor, String tipo) {
        Movimento newMov = new Movimento();
        newMov.setMontante(valor);

        if (tipo.equals("Debito") && this.saldo-valor>=0) {
            newMov.setTipoMovimento("Debito");
            listaMovimentos.add(newMov);
            this.saldo -= valor;
        } else if(tipo.equals("Credito")){
            newMov.setTipoMovimento("Credito");
            listaMovimentos.add(newMov);
            this.saldo += valor;
        } else{
            System.out.println("Saldo insuficiente!");
        }
    }


    public int getNumConta(){
        return this.numConta;
    }


    public float getSaldo(){
        return this.saldo;
    }


    public List<Movimento> getMovimentos(){
        return new ArrayList<>(this.listaMovimentos);
    }
}
