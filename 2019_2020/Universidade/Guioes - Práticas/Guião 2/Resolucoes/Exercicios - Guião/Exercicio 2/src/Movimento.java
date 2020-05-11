public class Movimento {
    private String tipo;
    private float quantia;

    public Movimento(){
        this.tipo = "Não definido";
        this.quantia = 0;
    }

    public String getTipoMovimento(){
        return this.tipo;
    }

    public float getMontante(){
        return this.quantia;
    }

    public void setTipoMovimento(String type){
        this.tipo = type;
    }

    public void setMontante(float valor){
        this.quantia = valor;
    }
}
